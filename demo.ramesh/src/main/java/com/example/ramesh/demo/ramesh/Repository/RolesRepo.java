package com.example.ramesh.demo.ramesh.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ramesh.demo.ramesh.Entity.Role;

public interface RolesRepo extends JpaRepository<Role,Long> {

	Optional<Role> findByName(String name);
	
	
}
