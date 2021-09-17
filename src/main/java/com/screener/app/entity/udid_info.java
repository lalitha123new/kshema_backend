package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "udid_info")
@EntityListeners(AuditingEntityListener.class)
public class udid_info {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int udid_info_id;
	private String createdAt;
	private String udid_info_obj;
	private String udid_uuid;
	

	private String patient_uuid;
	
	
	
	public int getUdid_info_id() {
		return udid_info_id;
	}



	public void setUdid_info_id(int udid_info_id) {
		this.udid_info_id = udid_info_id;
	}



	public String getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getUdid_uuid() {
		return udid_uuid;
	}



	public void setUdid_uuid(String udid_uuid) {
		this.udid_uuid = udid_uuid;
	}



	public String getPatient_uuid() {
		return patient_uuid;
	}



	public void setPatient_uuid(String patient_uuid) {
		this.patient_uuid = patient_uuid;
	}


	



	public String getUdid_info_obj() {
		return udid_info_obj;
	}



	public void setUdid_info_obj(String udid_info_obj) {
		this.udid_info_obj = udid_info_obj;
	}
	
	
	
}