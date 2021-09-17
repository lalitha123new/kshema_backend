package com.screener.app.entity;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "kshema_base")
@EntityListeners(AuditingEntityListener.class)
public class kshemabase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int kshema_base	;
	public int getKshema_base() {
		return kshema_base;
	}
	public void setKshema_base(int kshema_base) {
		this.kshema_base = kshema_base;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTaluk() {
		return taluk;
	}
	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	private String state;
	private String district;
	private String taluk;
	private String serial_number;
	
	
	
}
