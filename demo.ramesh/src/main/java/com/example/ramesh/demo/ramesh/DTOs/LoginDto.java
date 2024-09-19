package com.example.ramesh.demo.ramesh.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	private String userNameOrEmail;
	private String password;
}
