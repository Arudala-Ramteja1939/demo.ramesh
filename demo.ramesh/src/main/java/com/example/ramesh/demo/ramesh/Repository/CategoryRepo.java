package com.example.ramesh.demo.ramesh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ramesh.demo.ramesh.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Long>{

	
}
