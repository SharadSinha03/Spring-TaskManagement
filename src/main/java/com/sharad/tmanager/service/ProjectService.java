package com.sharad.tmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharad.tmanager.dto.ProjectDTO;
import com.sharad.tmanager.entity.ProjectEntity;
import com.sharad.tmanager.repository.ProjectRepo;
import com.sharad.tmanager.repository.TaskRepo;

@Service
public class ProjectService {

	@Autowired
	ProjectRepo projectRepository;

	@Autowired
	TaskRepo taskRepo;

	public List<ProjectEntity> getAllProjectByUser(Long userId) {
		List<ProjectEntity> taskList =  new ArrayList();
		//.findAllByUserId(userId);
		return taskList;
	}

	public List<ProjectEntity> getAllProject() {
		List<ProjectEntity> taskList = projectRepository.findAll();
		return taskList;
	}

	public ProjectEntity saveProject(ProjectDTO projectDTO) {
		ProjectEntity savedTask = projectRepository.save(projectDTO.transform());
		return savedTask;
	}

}
