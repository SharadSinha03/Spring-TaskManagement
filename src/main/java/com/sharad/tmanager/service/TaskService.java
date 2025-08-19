package com.sharad.tmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharad.tmanager.dto.TaskDTO;
import com.sharad.tmanager.entity.TaskEntity;
import com.sharad.tmanager.entity.UserEntity;
import com.sharad.tmanager.repository.TaskRepo;
import com.sharad.tmanager.repository.UserRepo;

@Service
public class TaskService {

	@Autowired
	TaskRepo taskRepository;
	
	@Autowired
	UserRepo userRepository;
	
	public List<TaskEntity> getAllTaskByUser(Long userId)
	{
		List<TaskEntity> taskList = taskRepository.findAllByUserId(userId);
		return taskList;
	}
	
	public List<TaskEntity> getAllTask()
	{
		List<TaskEntity> taskList = taskRepository.findAll();
		return taskList;
	}
	
	public TaskEntity saveTask(TaskDTO taskDto)
	{
		TaskEntity savedTask = taskRepository.save(taskDto.transform());
		return savedTask;
	}
	
	public boolean assignTask(Long taskId, Long userId)
	{
		Optional<UserEntity> user = userRepository.findById(userId);
		if(user != null)
		{
			Optional<TaskEntity> getObject = taskRepository.findById(taskId);
			TaskEntity saveObject = getObject.get();
			saveObject.setUser(user.get());
			TaskEntity savedTask = taskRepository.save(saveObject);
			return true;
		}
		return false;
	}
}
