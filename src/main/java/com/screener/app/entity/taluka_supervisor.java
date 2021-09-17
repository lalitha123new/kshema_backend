package com.screener.app.entity;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "taluka_supervisor")
@EntityListeners(AuditingEntityListener.class)
public class taluka_supervisor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taluka_supervisor_id;
	
	private int taluka_id;
	private int supervisor_id;
	private String createdAt;
	
	public int getTaluka_id() {
		return taluka_id;
	}
	
	public void setTaluka_id(int taluka_id) {
		this.taluka_id = taluka_id;
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