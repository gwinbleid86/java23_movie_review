package kg.attractor.movie_review_java23.service.impl;

import kg.attractor.movie_review_java23.dao.UserDao;
import kg.attractor.movie_review_java23.dto.UserDto;
import kg.attractor.movie_review_java23.exceptions.UserNotFoundException;
import kg.attractor.movie_review_java23.model.User;
import kg.attractor.movie_review_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers() {
        List<User> list = userDao.getUsers();
        return list.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getUserName())
                        .password(e.getPassword())
                        .build())
                .toList();
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getUserName())
                .password(user.getPassword())
                .build();
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getName());
        user.setPassword(userDto.getPassword());

        userDao.create(user);
    }

    @Override
    public int createUserAndReturnId(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getName());
        user.setPassword(userDto.getPassword());

        return userDao.createAndReturnId(user);
    }
}
