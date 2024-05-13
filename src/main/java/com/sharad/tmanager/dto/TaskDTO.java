package com.sharad.tmanager.dto;

import java.sql.Date;

import com.sharad.tmanager.entity.TaskEntity;

public class TaskDTO implements DTO{

	private Long id;

	private String name;
	private String description;
	private Date startdate;
	private Date enddate;
	private String status;
	private String priority;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public TaskEntity transform() {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(this.getId());
		taskEntity.setDescription(this.getDescription());
		taskEntity.setName(this.getName());
		taskEntity.setPriority(this.getPriority());
		taskEntity.setStartdate(this.getStartdate());
		taskEntity.setEnddate(this.getEnddate());
		taskEntity.setStatus(this.getStatus());
		//if(this.get)
		return taskEntity;
	}

}
