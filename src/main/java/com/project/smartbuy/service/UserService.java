package com.project.smartbuy.service;

import com.project.smartbuy.model.User;
import com.project.smartbuy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //User register
    public User register(String username, String email, String rawPassword) {
        //Check if the username or email address already exists
        if (userRepository.findByUsername(username) != null) throw new RuntimeException("Username already exists！");
        if (userRepository.findByEmail(email) != null) throw new RuntimeException("Email already exists！");
        //password encryption
        String encoded= passwordEncoder.encode(rawPassword);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(encoded);
        user.setRole("user");
        user.setStatus("active");
        return userRepository.save(user);
    }

    //Login Verification
    public boolean login (String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        if(user == null ) return false;
        //password verification used passwordEncoder
        return passwordEncoder.matches(rawPassword ,user.getPasswordHash());
    }
}
