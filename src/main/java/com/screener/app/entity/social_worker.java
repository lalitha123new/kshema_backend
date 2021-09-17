package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "social_worker")
@EntityListeners(AuditingEntityListener.class)
public class social_worker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int social_worker_id;
	
	public int getSocial_worker_id() {
		return social_worker_id;
	}
	public void setSocial_worker_id(int social_worker_id) {
		this.social_worker_id = social_worker_id;
	}
	public int getUsers_id() {
		return users_id;
	}
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	private int users_id;
	private String first_name;
	private String last_name;
	private String contact_no;
	private String status;
	private String createdAt;
	private int taluka_id;

	public int getTaluka_id() {
		return taluka_id;
	}
	public void setTaluka_id(int taluka_id) {
		this.taluka_id = taluka_id;
	}
	
	
	
	
	
}