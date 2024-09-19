package com.example.ramesh.demo.ramesh.Entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="posts",uniqueConstraints= {@UniqueConstraint(columnNames= {"title"})})
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="title",nullable=false)//title,description,content
	private String title;
	
	@Column(name="description",nullable=false)
	private String description;
	
	@Column(name="content",nullable=false)
	private String content;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY)//orphanRemoval=whenever the parent is removed then the child also removed
	@Builder.Default
	private Set<Comments> comments=new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="category_id",nullable = false)
	@JsonIgnore
	private Category category;

	

	
	
}
