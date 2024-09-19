package com.example.ramesh.demo.ramesh.SpringSecurity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter 
{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;  // Make sure JwtUtils is properly imported and autowired

	@Autowired
	private UserDetailsService userDetailsService;  // Make sure UserDetailsService is properly imported and autowired


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) 
		{
			jwtToken = authorizationHeader.substring(7);
			try {
				username = jwtTokenProvider.extractUsername(jwtToken);
			} catch (Exception e) {
				// Handle token extraction error
				e.printStackTrace();
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (jwtTokenProvider.validateToken(jwtToken, username)) 
			{
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}
