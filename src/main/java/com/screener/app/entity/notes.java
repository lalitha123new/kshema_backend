package com.screener.app.entity;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.jmx.snmp.Timestamp;

@SuppressWarnings("restriction")
@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
public class notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notes_id; 
	private String notes_uuid; 
	private String notes_message; 
	private int read_flag; 
	private String patient_uuid; 
	private int sender_user_id;
	private int recipient_user_id;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String createdAt;

	
	public int getNotes_id() {
		return notes_id;
	}
	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}
	public String getNotes_uuid() {
		return notes_uuid;
	}
	public void setNotes_uuid(String notes_uuid) {
		this.notes_uuid = notes_uuid;
	}
	public String getNotes_message() {
		return notes_message;
	}
	public void setNotes_message(String notes_message) {
		this.notes_message = notes_message;
	}
	public int getRead_flag() {
		return read_flag;
	}
	public void setRead_flag(int read_flag) {
		this.read_flag = read_flag;
	}
	public String getPatient_uuid() {
		return patient_uuid;
	}
	public void setPatient_uuid(String patient_uuid) {
		this.patient_uuid = patient_uuid;
	}
	
	
	public int getSender_user_id() {
		return sender_user_id;
	}
	public void setSender_user_id(int sender_user_id) {
		this.sender_user_id = sender_user_id;
	}
	public int getRecipient_user_id() {
		return recipient_user_id;
	}
	public void setRecipient_user_id(int recipient_user_id) {
		this.recipient_user_id = recipient_user_id;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}