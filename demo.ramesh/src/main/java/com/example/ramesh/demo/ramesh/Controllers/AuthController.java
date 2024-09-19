package com.example.ramesh.demo.ramesh.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ramesh.demo.ramesh.DTOs.JwtAuthResponse;
import com.example.ramesh.demo.ramesh.DTOs.LoginDto;
import com.example.ramesh.demo.ramesh.DTOs.RegisterDto;
import com.example.ramesh.demo.ramesh.ServiceImp.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	@Autowired
	private AuthServiceImpl impl;
	
	@PostMapping(value={"signin","login"})
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto dto)
	{
		String token=impl.login(dto);
		
		JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
	@PostMapping(value ={"register","signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDto dto)
	{
		String response=impl.register(dto);
		
		return ResponseEntity.ok(response);
	}
}
