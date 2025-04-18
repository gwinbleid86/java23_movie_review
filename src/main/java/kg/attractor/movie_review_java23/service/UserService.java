package kg.attractor.movie_review_java23.service;

import kg.attractor.movie_review_java23.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUserByEmail(String email);

    void addUser(UserDto userDto);

    UserDto createUserAndReturn(UserDto userDto);
}
