package com.example.demo.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.authentication.User;


public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}