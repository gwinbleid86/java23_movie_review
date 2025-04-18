package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dto.UserDto;
import kg.attractor.movie_review_java23.exceptions.UserNotFoundException;
import kg.attractor.movie_review_java23.model.User;
import kg.attractor.movie_review_java23.repository.UserRepository;
import kg.attractor.movie_review_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
}
