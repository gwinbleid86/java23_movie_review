package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.model.Authority;
import kg.attractor.movie_review_java23.model.Role;
import kg.attractor.movie_review_java23.model.User;
import kg.attractor.movie_review_java23.repository.RoleRepository;
import kg.attractor.movie_review_java23.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                getAuthorities(user.getRoles())
        );

    }

    private Collection<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Authority> collection = new ArrayList<>();

        for (Role role : roles) {
            privileges.add(role.getRoleName());
            collection.addAll(role.getAuthorities());
        }
        for (Authority authority : collection) {
            privileges.add(authority.getAuthorityName());
        }
        return privileges;
    }

    public void processOAuthPostLogin(String username) {
        var existingUser = userRepository.findByEmail(username);

        if (existingUser.isEmpty()) {
            var user = new User();
            user.setEmail(username);
            user.setPassword(encoder.encode("qwe"));
            user.setRoles(new HashSet<>());
            user.setEnabled(Boolean.TRUE);

            user.addRole(roleRepository.findByRoleName("GUEST"));
            userRepository.saveAndFlush(user);
        }

        UserDetails userDetails = loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
