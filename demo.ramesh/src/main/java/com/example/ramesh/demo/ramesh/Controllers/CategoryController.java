package com.example.ramesh.demo.ramesh.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ramesh.demo.ramesh.Entity.Category;
import com.example.ramesh.demo.ramesh.ServiceImp.CategoryImpl;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryImpl categoryImpl;

	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> save( @RequestBody Category category)
	{
		return new ResponseEntity<>(categoryImpl.save(category),HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<Category>> getAll()
	{
		return ResponseEntity.ok(categoryImpl.getAll());
	}

	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> update( @RequestBody Category category,@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(categoryImpl.update( category,id));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(categoryImpl.getById(id));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(categoryImpl.deleteById(id));
	}
	
	
}
