package com.sharad.tmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharad.tmanager.entity.TaskEntity;

public interface TaskRepo extends JpaRepository<TaskEntity, Long>{
	

	public List<TaskEntity> findAllByUserId(Long userId);
}
