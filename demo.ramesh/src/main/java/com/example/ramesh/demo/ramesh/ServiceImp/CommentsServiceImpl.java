package com.example.ramesh.demo.ramesh.ServiceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.ramesh.demo.ramesh.DTOs.CommentsDto;
import com.example.ramesh.demo.ramesh.Entity.Comments;
import com.example.ramesh.demo.ramesh.Entity.Post;
import com.example.ramesh.demo.ramesh.Exceptions.BlogApiException;
import com.example.ramesh.demo.ramesh.Exceptions.ResourceNotFoundException;
import com.example.ramesh.demo.ramesh.Repository.CommentsRepo;
import com.example.ramesh.demo.ramesh.Repository.PostRepo;
import com.example.ramesh.demo.ramesh.Service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private PostRepo repo;
	@Autowired
	private CommentsRepo crepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentsDto save(CommentsDto dto, Long post_id) {

		Post post=repo.findById(post_id).orElseThrow(() ->new ResourceNotFoundException("post","id",post_id));
		Comments e=this.mapToEntity(dto);
		e.setPost(post);
		crepo.save(e);
		CommentsDto d=this.mapToDto(e);

		return d;
	}

	private Comments mapToEntity(CommentsDto dto)
	{
		Comments comment=mapper.map(dto,Comments.class);
		
//		comment.setName(dto.getName());
//		comment.setEmail(dto.getEmail());
//		comment.setBody(dto.getBody());
		return comment;

	}

	private CommentsDto mapToDto(Comments comment)
	{
		CommentsDto dto=mapper.map(comment, CommentsDto.class);
//		dto.setId(comment.getId());
//		dto.setName(comment.getName());
//		dto.setEmail(comment.getEmail());
//		dto.setBody(comment.getBody());
		return dto;

	}

	@Override
	public List<CommentsDto> getByPostId(Long post_id) {

		List<Comments> comments=crepo.findByPostId(post_id);
		return comments.stream().map(this::mapToDto).collect(Collectors.toList());


	}

	@Override
	public CommentsDto getCommentById(Long Post_id ,Long commentId)
	{
		Post post=repo.findById(Post_id)
				.orElseThrow(()-> new BlogApiException(HttpStatus.NOT_FOUND,"the comment id and post are not eqaul"));
		Comments comment=crepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
		if(!(comment.getPost().getId().equals(post.getId())))
		{
			throw new BlogApiException(HttpStatus.NOT_FOUND,"the comment id and post are not equal");

		}else
		{
			return mapToDto(comment);
		}
	}

	@Override
	public CommentsDto updateCommentById(Long Post_id ,Long commentId,CommentsDto dto)
	{
		Post post=repo.findById(Post_id)
				.orElseThrow(()-> new BlogApiException(HttpStatus.NOT_FOUND,"the comment id and post are not eqaul"));
		Comments comment=crepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
		if(!(comment.getPost().getId().equals(post.getId())))
		{
			throw new BlogApiException(HttpStatus.NOT_FOUND,"the comment id and post are not equal");

		}else
		{
			comment.setName(dto.getName());
			comment.setEmail(dto.getEmail());
			comment.setBody(dto.getBody());
			crepo.save(comment);
			return mapToDto(comment);
		}

	}
	
	@Override
	public String deleteCommentById(Long Post_id ,Long commentId)
	{
		Post post=repo.findById(Post_id)
				.orElseThrow(()-> new BlogApiException(HttpStatus.NOT_FOUND,"the comment id and post are not eqaul"));
		Comments comment=crepo.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));
		if(!(comment.getPost().getId().equals(post.getId())))
		{
			throw new BlogApiException(HttpStatus.NOT_FOUND,"the comment id and post are not equal");
			
		}else
		{
			
			 crepo.deleteById(commentId);
			 
			 return "the  Comment is successfully deleted By Post Id";
		}
	}

	@Override
	public List<CommentsDto> getAllComments() {
		List<Comments> comments=crepo.findAll();
		
		return comments.stream().map(c-> mapToDto(c)).collect(Collectors.toList());
	}


}
