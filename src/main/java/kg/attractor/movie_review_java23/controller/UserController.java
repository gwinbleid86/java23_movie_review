package kg.attractor.movie_review_java23.controller;

import kg.attractor.movie_review_java23.dto.UserDto;
import kg.attractor.movie_review_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public HttpStatus createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return HttpStatus.OK;
    }

    @PostMapping("withId")
    public int createAndReturnId(@RequestBody UserDto userDto) {
        return userService.createUserAndReturnId(userDto);
    }
}
