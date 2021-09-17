package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "district_master")
@EntityListeners(AuditingEntityListener.class)
public class district_master {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int district_master_id;
	
	
	private String district_name;
	private String district_details;
	private String created_at;
	
	public int getDistrict_master_id() {
		return district_master_id;
	}
	public void setDistrict_master_id(int district_master_id) {
		this.district_master_id = district_master_id;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getDistrict_details() {
		return district_details;
	}
	public void setDistrict_details(String district_details) {
		this.district_details = district_details;
	}
	public String getCreatedAt() {
		return created_at;
	}
	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}
	
	
	
}