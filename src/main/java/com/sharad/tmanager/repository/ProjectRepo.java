package com.sharad.tmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharad.tmanager.entity.ProjectEntity;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long>{
	

	//public List<ProjectEntity> findAllByUserId(Long userId);
}
