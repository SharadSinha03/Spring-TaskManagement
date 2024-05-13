package com.sharad.tmanager.dto;

import java.util.List;

import com.sharad.tmanager.entity.UserEntity;

public class UserDTO implements DTO {

	private String name;
	private String email;
	private String password;
	private ProjectDTO projects;
	private List<RoleDTO> role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProjectDTO getProjects() {
		return projects;
	}

	public void setProjects(ProjectDTO projects) {
		this.projects = projects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleDTO> getRole() {
		return role;
	}

	public void setRole(List<RoleDTO> role) {
		this.role = role;
	}

	@Override
	public UserEntity transform() {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(this.getName());
		userEntity.setEmail(this.getEmail());
		userEntity.setPassword(this.getPassword());
		userEntity.setProjects(this.getProjects().transform());
		return null;
	}

}
