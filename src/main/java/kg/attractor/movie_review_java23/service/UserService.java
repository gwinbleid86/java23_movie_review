package kg.attractor.movie_review_java23.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.movie_review_java23.dto.UserDto;
import kg.attractor.movie_review_java23.model.User;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserByEmail(String email);

    void addUser(UserDto userDto);

    UserDto createUserAndReturn(UserDto userDto);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String password);

    void makeResetPasswdLnk(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;
}
