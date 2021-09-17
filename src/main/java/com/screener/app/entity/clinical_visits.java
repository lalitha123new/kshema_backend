package com.screener.app.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "clinical_visits")
@EntityListeners(AuditingEntityListener.class)
public class clinical_visits {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clinical_visits_id;
	private String patient_uuid;
	private String  clinical_visits_uuid;
	private int social_worker_id;
	private String visit_date;
	private String visit_type;
	private String visit_details;
	private Date followup_date;
	private String prv_visit_uuid;
	private String createdAt;
	
	
	
	public String getPrv_visit_uuid() {
		return prv_visit_uuid;
	}
	public void setPrv_visit_uuid(String prv_visit_uuid) {
		this.prv_visit_uuid = prv_visit_uuid;
	}
	
	
	public String getClinical_visits_uuid() {
		return clinical_visits_uuid;
	}
	public void setClinical_visits_uuid(String clinical_visits_uuid) {
		this.clinical_visits_uuid = clinical_visits_uuid;
	}
	public int getClinical_visits_id() {
		return clinical_visits_id;
	}
	public void setClinical_visits_id(int clinical_visits_id) {
		this.clinical_visits_id = clinical_visits_id;
	}
	public String getPatient_uuid() {
		return patient_uuid;
	}
	public void setPatient_uuid(String patient_uuid) {
		this.patient_uuid = patient_uuid;
	}
	public int getSocial_worker_id() {
		return social_worker_id;
	}
	public void setSocial_worker_id(int social_worker_id) {
		this.social_worker_id = social_worker_id;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getVisit_type() {
		return visit_type;
	}
	public void setVisit_type(String visit_type) {
		this.visit_type = visit_type;
	}
	public String getVisit_details() {
		return visit_details;
	}
	public void setVisit_details(String visit_details) {
		this.visit_details = visit_details;
	}
	public Date getFollowup_date() {
		return followup_date;
	}
	public void setFollowup_date(Date followup_date) {
		this.followup_date = followup_date;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}