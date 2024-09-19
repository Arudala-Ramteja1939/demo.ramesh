package com.example.ramesh.demo.ramesh.SpringSecurity;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ramesh.demo.ramesh.Entity.User;
import com.example.ramesh.demo.ramesh.Repository.UserRepo;


@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user=repo.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
		.orElseThrow(()-> new UsernameNotFoundException("User not found with userName or Email:"+usernameOrEmail));
		
		Set<GrantedAuthority> authorities=user.getRoles().stream()
				.map((roles) -> new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toSet());
		return new org.springframework.security.core.userdetails.User(user.getUserName(),
				user.getPassword(), 
				authorities);
	}

	
}
