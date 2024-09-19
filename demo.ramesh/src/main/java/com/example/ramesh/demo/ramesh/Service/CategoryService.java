package com.example.ramesh.demo.ramesh.Service;

import java.util.List;

import com.example.ramesh.demo.ramesh.Entity.Category;

public interface CategoryService {
	
	public Category save(Category category);
	
	public Category update(Category category,Long id);
	
	public Category getById(Long id);
	
	public List<Category> getAll();
	
	public String deleteById(Long id);
	
	

}
