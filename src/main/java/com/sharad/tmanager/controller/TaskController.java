package com.sharad.tmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharad.tmanager.dto.TaskDTO;
import com.sharad.tmanager.entity.UserEntity;
import com.sharad.tmanager.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	// autowired service
	@Autowired
	TaskService taskService;
	
	
	@RequestMapping("/getTaskList")
	public ResponseEntity<?> getTaskListByUser()
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			Long userId = ((UserEntity) authUser).getId();
			System.out.println("username: "+ userId);
			
			return new ResponseEntity<>(taskService.getAllTaskByUser(userId), HttpStatus.OK);
		}
		return null;
	}
	
	@RequestMapping("/getAllTaskList")
	public ResponseEntity<?> getAllTaskList()
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			Long userId = ((UserEntity) authUser).getId();
			System.out.println("username: "+ userId);
			
			return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
		}
		return null;
	}
	
	@PutMapping("/saveTask")
	public ResponseEntity<?> saveTaskList(@RequestBody TaskDTO taskDto)
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			return new ResponseEntity<>(taskService.saveTask(taskDto), HttpStatus.OK);
		}
		return null;
	}
	
	@PutMapping("/assignTask")
	public ResponseEntity<?> assignTask(@RequestParam("taskId") Long taskId, @RequestParam("userId") Long userId)
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			return new ResponseEntity<>(taskService.assignTask(taskId, userId), HttpStatus.OK);
		}
		return null;
	}
}
