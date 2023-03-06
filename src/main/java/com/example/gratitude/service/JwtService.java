package com.example.gratitude.service;

import java.time.LocalDateTime;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gratitude.model.Jwt;
import com.example.gratitude.model.User;
import com.example.gratitude.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Autowired
	private UserRepository userRepository;

	public Jwt createJWT(String username) {
		User user = userRepository.findByUsername(username).get();
		Jwt token = new Jwt();
		// Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(System.getenv("SIG_KEY")));
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneHourFromNow = now.plusHours(1);
		

		String jws = Jwts.builder()
				.setSubject(username)
				.claim("User_Id", user.getId())
				.setIssuedAt(java.sql.Timestamp.valueOf(now))
				.setIssuer("Gratitude_Java")
				.setExpiration(java.sql.Timestamp.valueOf(oneHourFromNow))
				.signWith(key).compact();
		
		token.setToken(jws);

		return token;
	}
}
