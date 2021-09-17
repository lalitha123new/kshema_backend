package com.screener.app.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "taluka_master")
@EntityListeners(AuditingEntityListener.class)
public class taluka {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taluka_master_id;
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
	private String taluka_name;
	private String taluka_details;
	private String createdAt;
	public boolean addtaluka(@Valid taluka tk) {
		// TODO Auto-generated method stub
		return false;
	}

}
