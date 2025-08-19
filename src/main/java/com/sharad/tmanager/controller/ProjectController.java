package com.sharad.tmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharad.tmanager.dto.ProjectDTO;
import com.sharad.tmanager.entity.UserEntity;
import com.sharad.tmanager.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;

	@RequestMapping("/getProjectList")
	public ResponseEntity<?> getTaskListByUser()
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			Long userId = ((UserEntity) authUser).getId();
			System.out.println("username: "+ userId);
			
			return new ResponseEntity<>(projectService.getAllProjectByUser(userId), HttpStatus.OK);
		}
		return null;
	}
	
	@RequestMapping("/getAllProjectList")
	public ResponseEntity<?> getAllTaskList()
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			Long userId = ((UserEntity) authUser).getId();
			System.out.println("username: "+ userId);
			
			return new ResponseEntity<>(projectService.getAllProject(), HttpStatus.OK);
		}
		return null;
	}
	
	@PutMapping("/saveProject")
	public ResponseEntity<?> saveTaskList(@RequestBody ProjectDTO projectDto)
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			return new ResponseEntity<>(projectService.saveProject(projectDto), HttpStatus.OK);
		}
		return null;
	}
}
