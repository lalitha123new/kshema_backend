package com.screener.app.entity;

import java.util.List;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "patient")
@EntityListeners(AuditingEntityListener.class)
public class patientObj {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patient_id;
    private String patient_uuid;
    private int group_data_id;
	private String kshema_id;
	private String name;
	private String demographic_info;
	private String consent_arr;
	private String needs_assessment;
	private String uuid_info;
	private String status;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String createdAt;
	private String images;
	
	
	
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getPatient_uuid() {
		return patient_uuid;
	}
	public void setPatient_uuid(String patient_uuid) {
		this.patient_uuid = patient_uuid;
	}
	
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getGroup_data_id() {
		return group_data_id;
	}
	public void setGroup_data_id(int group_data_id) {
		this.group_data_id = group_data_id;
	}
	public String getKshema_id() {
		return kshema_id;
	}
	public void setKshema_id(String kshema_id) {
		this.kshema_id = kshema_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDemographic_info() {
		return demographic_info;
	}
	public void setDemographic_info(String demographic_info) {
		this.demographic_info = demographic_info;
	}
	public String getConsent_arr() {
		return consent_arr;
	}
	public void setConsent_arr(String consent_arr) {
		this.consent_arr = consent_arr;
	}
	public String getNeeds_assessment() {
		return needs_assessment;
	}
	public void setNeeds_assessment(String needs_assessment) {
		this.needs_assessment = needs_assessment;
	}
	public String getUuid_info() {
		return uuid_info;
	}
	public void setUuid_info(String uuid_info) {
		this.uuid_info = uuid_info;
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
    
    
	
	
}
