package kg.attractor.movie_review_java23.config;

import kg.attractor.movie_review_java23.service.impl.AuthUserDetailsService;
import kg.attractor.movie_review_java23.service.impl.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthUserDetailsService userDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())
//                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/reviews/**").fullyAuthenticated()
//                        .requestMatchers("/movies/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll())
//                .oauth2Login(oauth -> oauth
//                        .loginPage("/auth/login")
//                        .userInfoEndpoint(userConfig -> userConfig
//                                .userService(customOAuth2UserService))
//                        .successHandler((request, response, authentication) -> {
//                            var oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//                            userDetailsService.processOAuthPostLogin(oauthUser.getAttribute("email"));
//                            response.sendRedirect("/");
//                        }))
        ;

        return http.build();
    }
}
