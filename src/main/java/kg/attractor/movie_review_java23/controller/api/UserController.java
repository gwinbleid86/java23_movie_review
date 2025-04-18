package kg.attractor.movie_review_java23.controller.api;

import jakarta.validation.Valid;
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
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public HttpStatus createUser(@RequestBody @Valid UserDto userDto) {
        userService.addUser(userDto);
        return HttpStatus.OK;
    }

    @PostMapping("withId")
    public UserDto createAndReturn(@RequestBody @Valid UserDto userDto) {
        return userService.createUserAndReturn(userDto);
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    private ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
//        return ErrorResponse.builder(e, HttpStatus.NO_CONTENT, e.getMessage()).build();
//    }
}
