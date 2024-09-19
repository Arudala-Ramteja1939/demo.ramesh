package com.example.ramesh.demo.ramesh.Entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user",uniqueConstraints= @UniqueConstraint(columnNames= {"email","userName"}))
public class User  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="user_roles",joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
	inverseJoinColumns=
	@JoinColumn(name="role_id", referencedColumnName="id"))
	private Set<Role> roles;


	
	
	
}
