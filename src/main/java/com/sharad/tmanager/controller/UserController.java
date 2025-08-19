package com.sharad.tmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharad.tmanager.dto.UserDTO;
import com.sharad.tmanager.entity.UserEntity;
import com.sharad.tmanager.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/getUserList")
	public ResponseEntity<?> getTaskListByProject()
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			Long userId = ((UserEntity) authUser).getId();
			System.out.println("username: "+ userId);
			
			return new ResponseEntity<>(userService.getAllUserByProject(userId), HttpStatus.OK);
		}
		return null;
	}
	
	@RequestMapping("/getAllUserList")
	public ResponseEntity<?> getAllTaskList()
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			Long userId = ((UserEntity) authUser).getId();
			System.out.println("username: "+ userId);
			
			return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
		}
		return null;
	}
	
	@PutMapping("/saveUser")
	public ResponseEntity<?> saveTaskList(@RequestBody UserDTO projectDto)
	{
		Object authUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(authUser != null)
		{
			return new ResponseEntity<>(userService.saveUser(projectDto), HttpStatus.OK);
		}
		return null;
	}
}
