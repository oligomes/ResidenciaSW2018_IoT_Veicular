package com.example.demo.authentication;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.authentication.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}