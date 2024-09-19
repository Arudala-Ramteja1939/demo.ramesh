package com.example.ramesh.demo.ramesh.ServiceImp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ramesh.demo.ramesh.DTOs.LoginDto;
import com.example.ramesh.demo.ramesh.DTOs.RegisterDto;
import com.example.ramesh.demo.ramesh.Entity.Role;
import com.example.ramesh.demo.ramesh.Entity.User;
import com.example.ramesh.demo.ramesh.Exceptions.BlogApiException;
import com.example.ramesh.demo.ramesh.Repository.RolesRepo;
import com.example.ramesh.demo.ramesh.Repository.UserRepo;
import com.example.ramesh.demo.ramesh.Service.AuthService;
import com.example.ramesh.demo.ramesh.SpringSecurity.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService  {

	@Autowired
	private UserRepo repo;
	@Autowired
	private RolesRepo rrepo;
	@Autowired
	private PasswordEncoder  encoder;
	@Autowired
	private AuthenticationManager auth;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String login(LoginDto dto) {
		Authentication authentication=auth.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserNameOrEmail(),
				dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtTokenProvider.generateToken(authentication);
		return token;
	}


	@Override
	public String register(RegisterDto dto) {
		if(repo.existsByUserName(dto.getUsername()))
		{
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"Username is already exist.");
		}
		
		if(repo.existsByEmail(dto.getEmail()))
		{
			throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email is already exist.");
		}
		User user=new User();
		user.setName(dto.getName());
		user.setUserName(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		Set<Role> roles=new HashSet<>();
		Role userRole=rrepo.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		repo.save(user);
		return "Successfully Registered.";
	}
	
	
}
