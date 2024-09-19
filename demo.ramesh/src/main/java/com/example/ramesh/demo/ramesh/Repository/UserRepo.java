package com.example.ramesh.demo.ramesh.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ramesh.demo.ramesh.Entity.User;

public interface UserRepo extends JpaRepository<User,Long> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUserNameOrEmail(String userName,String email);
	
	Optional<User> findByUserName(String userName);
	
	Boolean existsByUserName(String username);
	
	Boolean existsByEmail(String email);
}
