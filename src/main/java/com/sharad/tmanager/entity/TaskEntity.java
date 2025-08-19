package com.sharad.tmanager.entity;

import java.sql.Date;

import com.sharad.tmanager.dto.TaskDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class TaskEntity implements com.sharad.tmanager.dto.Entity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private Date startdate;
	private Date enddate;
	private String status;
	private String priority;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "task_assignee", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private UserEntity user;
	
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public TaskDTO transform() {
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(this.getId());
		taskDTO.setDescription(this.getDescription());
		taskDTO.setName(this.getName());
		taskDTO.setPriority(this.getPriority());
		taskDTO.setStartdate(this.getStartdate());
		taskDTO.setEnddate(this.getEnddate());
		taskDTO.setStatus(this.getStatus());
		return taskDTO;
	}
	
}
