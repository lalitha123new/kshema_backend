package com.screener.app.entity;

import java.sql.Date;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tasks_id;
	private int task_type;
	private String creation_date;
	private Date task_due_date;
	private String task_details;
	private String status;
	private String update_date;
	private String prev_record_uuuid;
	private String tasks_uuid;
	private String patient_uuid;
	private int origin_record_id;
	private String createdAt;
	
	
	public int getTasks_id() {
		return tasks_id;
	}
	public void setTasks_id(int tasks_id) {
		this.tasks_id = tasks_id;
	}
	public int getTask_type() {
		return task_type;
	}
	public void setTask_type(int task_type) {
		this.task_type = task_type;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public Date getTask_due_date() {
		return task_due_date;
	}
	public void setTask_due_date(Date task_due_date) {
		this.task_due_date = task_due_date;
	}
	public String getTask_details() {
		return task_details;
	}
	public void setTask_details(String task_details) {
		this.task_details = task_details;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getPrev_record_uuuid() {
		return prev_record_uuuid;
	}
	public void setPrev_record_uuuid(String prev_record_uuuid) {
		this.prev_record_uuuid = prev_record_uuuid;
	}
	public String getTasks_uuid() {
		return tasks_uuid;
	}
	public void setTasks_uuid(String tasks_uuid) {
		this.tasks_uuid = tasks_uuid;
	}
	public String getPatient_uuid() {
		return patient_uuid;
	}
	public void setPatient_uuid(String patient_uuid) {
		this.patient_uuid = patient_uuid;
	}
	public int getOrigin_record_id() {
		return origin_record_id;
	}
	public void setOrigin_record_id(int origin_record_id) {
		this.origin_record_id = origin_record_id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
	
	
}