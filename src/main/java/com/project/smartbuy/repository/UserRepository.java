package com.project.smartbuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.smartbuy.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
    User findByEmail(String email);
}
