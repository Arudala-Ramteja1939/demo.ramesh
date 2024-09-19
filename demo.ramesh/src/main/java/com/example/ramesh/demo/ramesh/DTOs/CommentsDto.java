package com.example.ramesh.demo.ramesh.DTOs;

import com.example.ramesh.demo.ramesh.Entity.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

	private Long id;
	@NotBlank(message="the name should not be Blank.")
	@Size(min=2,message="name can contains atleast 2 characters.")
	private String name;
	@NotBlank(message="the email should not be Blank.")
	@Size(min=2,message="email can contains atleast 2 characters.")
	private String email;
	@NotBlank(message="the body should not be Blank.")
	@Size(min=2,message="body can contains atleast 2 characters.")
	private String body;
	
	private Post post;
}
