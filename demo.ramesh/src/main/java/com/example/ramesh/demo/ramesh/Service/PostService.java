package com.example.ramesh.demo.ramesh.Service;

import java.util.List;

import com.example.ramesh.demo.ramesh.DTOs.PostDto;
import com.example.ramesh.demo.ramesh.DTOs.PostResponse;
import com.example.ramesh.demo.ramesh.Entity.Post;


public interface PostService {

	PostDto createPost(PostDto postDto);

	List<PostDto> getAllPosts();
	
	PostDto getById(Long id);
	
	PostDto update(Long id, PostDto postDto);
	
	String delete(Long id);
	
	PostResponse getPostsPage(int page, int size,String sortBy,String sortDir);
	
	public List<Post> getPostByCategory(Long category_id);

	
}
