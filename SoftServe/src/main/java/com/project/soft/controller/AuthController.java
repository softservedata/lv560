package com.project.soft.controller;

import com.project.soft.entity.User;
import com.project.soft.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(path = "/auth")
@Controller
public class AuthController {

    private final UserService userService;
    protected final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);

        authenticateUserAndSetSession(user, request);

        return "redirect:/home";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {

        String username = user.getUsername();
        String password = user.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticationUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationUser);
    }
}
