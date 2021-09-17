package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "phc_master")
@EntityListeners(AuditingEntityListener.class)
public class phc_master {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int phc_id;
	
	public int getPhc_id() {
		return phc_id;
	}
	public void setPhc_id(int phc_id) {
		this.phc_id = phc_id;
	}
	public String getPhc_name() {
		return phc_name;
	}
	public void setPhc_name(String phc_name) {
		this.phc_name = phc_name;
	}
	public String getPhc_details() {
		return phc_details;
	}
	public void setPhc_details(String phc_details) {
		this.phc_details = phc_details;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	private String phc_name;
	private String phc_details;
	private String created_at;
	
	
	
	
}