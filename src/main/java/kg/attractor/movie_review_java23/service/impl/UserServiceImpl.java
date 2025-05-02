package kg.attractor.movie_review_java23.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review_java23.dto.UserDto;
import kg.attractor.movie_review_java23.exceptions.UserNotFoundException;
import kg.attractor.movie_review_java23.model.User;
import kg.attractor.movie_review_java23.repository.UserRepository;
import kg.attractor.movie_review_java23.service.UserService;
import kg.attractor.movie_review_java23.util.CommonUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final EmailService emailService;

    @Override
    public List<UserDto> getUsers() {
        List<User> list = userRepository.findAll();
        return list.stream()
                .map(e -> UserDto.builder()
                        .email(e.getEmail())
                        .username(e.getUsername())
                        .password(e.getPassword())
                        .build())
                .toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }

    @Override
    public UserDto createUserAndReturn(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        User usr = userRepository.save(user);
        return UserDto.builder()
                .email(usr.getEmail())
                .username(usr.getUsername())
                .password(usr.getPassword())
                .build();
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updatePassword(User user, String password) {
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void makeResetPasswdLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLnk = CommonUtilities.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLnk);
    }
}
