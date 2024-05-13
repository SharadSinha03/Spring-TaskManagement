package com.sharad.tmanager.dto;

import com.sharad.tmanager.entity.ProjectEntity;

public class ProjectDTO implements DTO {

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ProjectEntity transform() {
		ProjectEntity projectEntity = new ProjectEntity();
		projectEntity.setId(this.getId());
		projectEntity.setName(this.getName());
		// if(this.get)
		return projectEntity;
	}
}
