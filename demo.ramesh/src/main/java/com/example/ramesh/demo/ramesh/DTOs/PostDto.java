package com.example.ramesh.demo.ramesh.DTOs;

import java.util.HashSet;
import java.util.Set;

import com.example.ramesh.demo.ramesh.Entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Long id;
	@NotBlank(message="the title should not be Blank.")
	@Size(min=2,message="title can contains atleast 2 characters.")
	private String title;
	@NotBlank(message="the description should not be Blank.")
	@Size(min=2,message="description can contains atleast 2 characters.")
	private String description;
	@NotBlank(message="the content should not be Blank.")
	@Size(min=2,max=50,message="content can contains atleast 2 characters.")
	private String content;
	private Set<CommentsDto> comments=new HashSet<>();
	private Long categoryId;
	private Category category;
}
