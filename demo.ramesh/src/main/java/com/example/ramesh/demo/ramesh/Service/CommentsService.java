package com.example.ramesh.demo.ramesh.Service;

import java.util.List;

import com.example.ramesh.demo.ramesh.DTOs.CommentsDto;

public interface CommentsService {

	CommentsDto save(CommentsDto dto,Long post_id);
	
	List<CommentsDto> getByPostId(Long post_id);
	
	CommentsDto getCommentById(Long post_id,Long commentId);
	
	CommentsDto updateCommentById(Long Post_id ,Long commentId,CommentsDto dto);

	String deleteCommentById(Long Post_id, Long commentId);
	
	List<CommentsDto> getAllComments();
 }
