package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "group_data")
@EntityListeners(AuditingEntityListener.class)
public class group_data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int group_data_id;
	
	private int taluka_id;
	private int social_worker_id;
	private int supervisor_id;
	private String createdAt;

	
	public int getGroup_data_id() {
		return group_data_id;
	}
	public void setGroup_data_id(int group_data_id) {
		this.group_data_id = group_data_id;
	}
	public int getTaluka_id() {
		return taluka_id;
	}
	public void setTaluka_id(int taluka_id) {
		this.taluka_id = taluka_id;
	}
	public int getSocial_worker_id() {
		return social_worker_id;
	}
	public void setSocial_worker_id(int social_worker_id) {
		this.social_worker_id = social_worker_id;
	}
	public int getSupervisor_id() {
		return supervisor_id;
	}
	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
}