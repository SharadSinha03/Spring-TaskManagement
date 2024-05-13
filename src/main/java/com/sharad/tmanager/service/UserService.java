package com.sharad.tmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharad.tmanager.dto.UserDTO;
import com.sharad.tmanager.entity.ProjectEntity;
import com.sharad.tmanager.entity.UserEntity;
import com.sharad.tmanager.repository.ProjectRepo;
import com.sharad.tmanager.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	ProjectRepo projectRepository;

	@Autowired
	UserRepo userRepo;

	public List<ProjectEntity> getAllUserByProject(Long projectId) {
		List<ProjectEntity> taskList =  new ArrayList();
		//.findAllByUserId(userId);
		return taskList;
	}

	public List<UserEntity> getAllUser() {
		List<UserEntity> taskList = userRepo.findAll();
		return taskList;
	}

	public UserEntity saveUser(UserDTO userDTO) {
		UserEntity savedTask = userRepo.save(userDTO.transform());
		return savedTask;
	}

}
