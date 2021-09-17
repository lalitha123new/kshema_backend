package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "task_master")
@EntityListeners(AuditingEntityListener.class)
public class task_master {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int task_master_id;
	private String task_name;
	private String task_details;
	private String createdAt;
	
	public int getTask_master_id() {
		return task_master_id;
	}
	public void setTask_master_id(int task_master_id) {
		this.task_master_id = task_master_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_details() {
		return task_details;
	}
	public void setTask_details(String task_details) {
		this.task_details = task_details;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
	
}