package com.sharad.tmanager.config;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.sharad.tmanager.entity.UserEntity;
import com.sharad.tmanager.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<UserEntity> users = userRepository.findByEmail(username);
		if (users.get() == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		// sharadTaskManagement
	//	CustomPasswordEncoder.setCurrentSalt(Base64.getEncoder().encodeToString(BCrypt.gensalt().getBytes()));
		return (UserDetails) users.get();
	}

}