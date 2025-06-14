package com.project.smartbuy.controller;

import com.project.smartbuy.model.User;
import com.project.smartbuy.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //User register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    //Login
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        boolean success = userService.login(loginUser.getUsername(), loginUser.getPasswordHash());
        if (success) {
            return "Login successful!";
        }else{
            return "User name or Password incorrect.";}
    }

}
