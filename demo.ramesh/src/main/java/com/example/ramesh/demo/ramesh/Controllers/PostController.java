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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ramesh.demo.ramesh.DTOs.AppConstants;
import com.example.ramesh.demo.ramesh.DTOs.PostDto;
import com.example.ramesh.demo.ramesh.DTOs.PostResponse;
import com.example.ramesh.demo.ramesh.Entity.Post;
import com.example.ramesh.demo.ramesh.ServiceImp.PostServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;


	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
	{
		return new ResponseEntity<>(postServiceImpl.createPost(postDto),HttpStatus.CREATED);

	}

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<PostDto>> getAllPosts()
	{
		return ResponseEntity.ok(postServiceImpl.getAllPosts());
	}


	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> update(@Valid @RequestBody PostDto postDto,@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(postServiceImpl.update(id, postDto));

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PostDto> getAll(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(postServiceImpl.getById(id));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(postServiceImpl.delete(id));
	}

	@GetMapping("/page")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PostResponse> page(@RequestParam(value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMNBER,required=false)int pageNo,
			@RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.DEFAULT_SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIRECTION,required=false) String sortDir)
	{
		return ResponseEntity.ok(postServiceImpl.getPostsPage(pageNo, pageSize,sortBy,sortDir));
	}
	@GetMapping("/category/{category_id}")
	public ResponseEntity<List<Post>> getPostByCategory(@PathVariable("category_id") Long id)
	{
		return ResponseEntity.ok(postServiceImpl.getPostByCategory(id));
	}
}
