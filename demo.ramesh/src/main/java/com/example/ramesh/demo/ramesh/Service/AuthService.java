package com.example.ramesh.demo.ramesh.Service;

import com.example.ramesh.demo.ramesh.DTOs.LoginDto;
import com.example.ramesh.demo.ramesh.DTOs.RegisterDto;

public interface AuthService {

	public String login(LoginDto dto);
	
	public String register(RegisterDto dto);
}
