package com.project.smartbuy.controller;

import com.project.smartbuy.dto.JwtResponse;
import com.project.smartbuy.dto.LoginRequest;
import com.project.smartbuy.dto.RegisterRequest;
import com.project.smartbuy.model.User;
import com.project.smartbuy.service.UserService;
import com.project.smartbuy.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //User register API
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            userService.register(request.getUsername(), request.getEmail(), request.getPassword());
            return ResponseEntity.ok("Registered!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Login API, return JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean valid = userService.login(request.getUsername(), request.getPassword());
        if (valid) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
