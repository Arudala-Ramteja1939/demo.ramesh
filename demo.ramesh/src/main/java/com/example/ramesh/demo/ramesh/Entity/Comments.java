package com.example.ramesh.demo.ramesh.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comments",uniqueConstraints= {@UniqueConstraint(columnNames= {"email"})})
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="name",nullable=false)
	private String name;

	@Column(name="email",nullable=false)
	private String email;

	@Column(name="body",nullable=false)
	private String body;

	@ManyToOne
	@JoinColumn(name="post_id",nullable=false)
	@JsonIgnore
	private Post post;


}

