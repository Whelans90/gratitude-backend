package com.example.gratitude.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gratitude.model.Jwt;
import com.example.gratitude.model.User;
import com.example.gratitude.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Autowired
	private UserRepository userRepository;

	SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(System.getenv("SIG_KEY")));

	public Jwt createJWT(String username) {
		User user = userRepository.findByUsername(username).get();
		Jwt token = new Jwt();
		// Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneHourFromNow = now.minusHours(2);

		String jws = Jwts.builder().setSubject(username).claim("User_Id", user.getId())
				.setIssuedAt(java.sql.Timestamp.valueOf(now)).setIssuer("Gratitude_Java")
				.setExpiration(java.sql.Timestamp.valueOf(oneHourFromNow)).signWith(key).compact();

		token.setToken(jws);

		return token;
	}

	public boolean validateJWT(Jwt token) {
		Jws<Claims> jws;
		try {
			jws = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token.getToken());
			
			Date expiration = jws.getBody().getExpiration();
			
			Date now = new Date();
			
			if(expiration.compareTo(now)>0) {
			return true;
			}
			
			return false;
		}
		
		catch (JwtException ex) {
			return false;
		}
	}
}
