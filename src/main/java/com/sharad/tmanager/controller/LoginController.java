package com.sharad.tmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharad.tmanager.config.JWTService;
import com.sharad.tmanager.dto.AuthResponse;
import com.sharad.tmanager.dto.LoginDTO;
import com.sharad.tmanager.entity.UserEntity;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
		String token = null;

		AuthResponse authResponse = new AuthResponse();
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
			UserEntity users = (UserEntity) auth.getPrincipal();
			if (auth.isAuthenticated()) {
				System.out.println("USER AUTHENTICATED");
				token = jwtService.generateToken(loginDTO.getEmail(), users);
				for( GrantedAuthority auths : users.getAuthorities())
				{
					System.out.println("ROLE:: "+auths.getAuthority());	
					authResponse.setRole(auths.getAuthority());
				}
				System.out.println("TOKEN ");
				authResponse.setToken(token);
				authResponse.setMessage("Successful");
				return new ResponseEntity<>(authResponse, HttpStatus.OK);
			}
		} catch (UsernameNotFoundException | BadCredentialsException e) {
			authResponse.setToken(null);
			authResponse.setMessage("Bad Credentials.");
			e.printStackTrace();
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			authResponse.setToken(null);
			authResponse.setMessage("Internal Server Error.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password)
	{
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
