package com.project.smartbuy.service;

import com.project.smartbuy.model.User;
import com.project.smartbuy.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //User register
    public User register(User user) {
        //password encryption
        String encodeedPassword= passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(encodeedPassword);
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
