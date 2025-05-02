package kg.attractor.movie_review_java23.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.movie_review_java23.dto.UserDto;
import kg.attractor.movie_review_java23.exceptions.UserNotFoundException;
import kg.attractor.movie_review_java23.model.User;
import kg.attractor.movie_review_java23.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.addUser(userDto);
            return "redirect:/";
        }
        model.addAttribute("userDto", userDto);

        return "auth/register";
    }

    @GetMapping("forgot_password")
    public String showForgotPassword() {
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswdLnk(request);
            model.addAttribute("message", "Password reset link has been sent");
        } catch (UserNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Invalid token");
        }
        return "auth/reset_password_form";
    }

    @PostMapping("reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully updated your password");
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Invalid token");
        }
        return "auth/message";
    }
}
