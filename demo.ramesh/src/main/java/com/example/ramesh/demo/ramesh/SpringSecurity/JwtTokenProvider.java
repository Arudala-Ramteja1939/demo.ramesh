package com.example.ramesh.demo.ramesh.SpringSecurity;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${ap.jwt-expiration-milliseconds}")
	private long jwtExpirationMilliseconds;


	//generate JWT Token

	public String generateToken(Authentication authentication)
	{
		String username=authentication.getName();

		Date currentDate=new Date();

		Date expireDate=new Date(currentDate.getTime()+ jwtExpirationMilliseconds);

		String token=Jwts.builder()
				.setSubject(username)
				.setIssuedAt(currentDate)
				.setExpiration(expireDate)
				.signWith(secretKey(),SignatureAlgorithm.HS256).compact();
		return token;

	}

	private Key secretKey()
	{
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	// Check if the JWT token is expired
	public boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

	// Validate the JWT token
	public boolean validateToken(String token, String username) {
		return (username.equals(extractUsername(token)) && !isTokenExpired(token));
	}

	// Extract all claims from the JWT token
	
	private Claims extractClaims(String token) {
		return Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
	}


	// Optionally, a method to get the JWT token from the request header
	public String getTokenFromHeader(String header) {
		if (header != null && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return null;
	}
}

