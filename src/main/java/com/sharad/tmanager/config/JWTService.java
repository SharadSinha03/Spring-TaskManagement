package com.sharad.tmanager.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sharad.tmanager.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

	private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

	private String secretKey = "MySecretKey";
	private String salt = "sharadTaskMangament";

	public String generateToken(String username, UserEntity userObject) {
		HashMap<String, Object> claims = new HashMap<>();
		return generateToken(claims, username);
	}

	private String generateToken(HashMap<String, Object> claims, String username) {

		logger.info("Session Time : {} ms", (1000 * 60 * 480));

		return Jwts.builder().setClaims(claims).
				setSubject(username)
				//setSubject(AES256Encryption.encrypt(username, secretKey, salt))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 480))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

	}

	private Key getSignKey() {
		return Keys.hmacShaKeyFor("$2a$12$d5N2ldynnmygqj6bUae3p.DokqaYBD2NdkRUr8M1ml8YG4dEuk6ie".getBytes());
	}

	public String extractUserName(String jwt) {

		return extractClaim(jwt, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		if (claims != null)
			return claimResolver.apply(claims);
		else
			return null;
	}

	public Claims extractAllClaims(String token) {
		try {
			
			return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isTokenValid(String token, String userName) {

		final String user = extractUserName(token);
		return (user.equals(userName) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);
	}
	
}