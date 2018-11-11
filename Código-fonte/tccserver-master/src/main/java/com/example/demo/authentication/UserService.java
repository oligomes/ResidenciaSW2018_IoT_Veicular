package com.example.demo.authentication;

import com.example.demo.authentication.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
