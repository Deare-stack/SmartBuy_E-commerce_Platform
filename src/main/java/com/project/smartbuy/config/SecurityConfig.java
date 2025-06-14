package com.project.smartbuy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
@org.springframework.context.annotation.Configuration
//Allow login and register without authentication for testing purposes that testing on postman.
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())// Disable CSRF for testing with Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/products",
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()// Allow anyone to register and login
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
