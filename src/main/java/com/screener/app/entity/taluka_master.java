package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "taluka_master")
@EntityListeners(AuditingEntityListener.class)
public class taluka_master {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taluka_master_id;
	
	
	
	private String taluka_name;
	private String taluka_details;
	private String createdAt;
	
	public int getTaluka_master_id() {
		return taluka_master_id;
	}
	public void setTaluka_master_id(int taluka_master_id) {
		this.taluka_master_id = taluka_master_id;
	}
	public String getTaluka_name() {
		return taluka_name;
	}
	public void setTaluka_name(String taluka_name) {
		this.taluka_name = taluka_name;
	}
	public String getTaluka_details() {
		return taluka_details;
	}
	public void setTaluka_details(String taluka_details) {
		this.taluka_details = taluka_details;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
}