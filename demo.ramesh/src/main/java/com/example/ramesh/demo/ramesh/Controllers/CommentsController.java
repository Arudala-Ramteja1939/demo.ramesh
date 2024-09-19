package com.example.ramesh.demo.ramesh.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ramesh.demo.ramesh.DTOs.CommentsDto;
import com.example.ramesh.demo.ramesh.ServiceImp.CommentsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	@Autowired
	private CommentsServiceImpl impl;
	
	@PostMapping("/{post_id}")
	public ResponseEntity<CommentsDto> save(@Valid @RequestBody CommentsDto dto,@PathVariable Long post_id)
	{
		
		return new ResponseEntity<>(impl.save(dto, post_id),HttpStatus.CREATED);
		
	}
	@GetMapping("/{post_id}")
	public ResponseEntity<List<CommentsDto>> getByPostId(@PathVariable Long post_id)
	{
		return ResponseEntity.ok(impl.getByPostId(post_id));
		
	}
	
	@GetMapping("/{post_id}/{id}")
	public ResponseEntity<CommentsDto> get(@PathVariable Long post_id,@PathVariable Long id)
	{
		return ResponseEntity.ok(impl.getCommentById(post_id,id));
		
	}
	
	@PutMapping("/{post_id}/{id}")
	public ResponseEntity<CommentsDto> update(@PathVariable Long post_id ,@PathVariable Long id,@Valid @RequestBody CommentsDto dto)
	{
		return new ResponseEntity<>(impl.updateCommentById(post_id, id, dto),HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{post_id}/{id}")
	public ResponseEntity<String> deleteCommetByPostId(@PathVariable Long post_id,@PathVariable Long id)
	{
		return ResponseEntity.ok(impl.deleteCommentById(post_id, id));
		
	}
	
	@GetMapping
	public ResponseEntity<List<CommentsDto>> getAllComments()
	{
		return ResponseEntity.ok(impl.getAllComments());
		
	}
}
