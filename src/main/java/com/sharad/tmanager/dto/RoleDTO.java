package com.sharad.tmanager.dto;

import com.sharad.tmanager.entity.RoleEntity;

public class RoleDTO implements DTO {

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
	public RoleEntity transform() {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(this.getId());
		roleEntity.setName(this.getName());
		// if(this.get)
		return roleEntity;
	}
}
