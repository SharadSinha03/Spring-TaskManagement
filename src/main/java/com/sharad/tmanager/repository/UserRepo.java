package com.sharad.tmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharad.tmanager.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String username);

}
