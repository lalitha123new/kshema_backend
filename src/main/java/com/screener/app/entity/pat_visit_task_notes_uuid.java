package com.screener.app.entity;

import java.util.List;

public class pat_visit_task_notes_uuid {
	
	private List<patientObj> patientObj;
	
	public List<patientObj> getPatientObj() {
		return patientObj;
	}
	public void setPatientObj(List<patientObj> patientObj) {
		this.patientObj = patientObj;
	}
	public List<clinical_visits> getClinical_visits() {
		return clinical_visits;
	}
	public void setClinical_visits(List<clinical_visits> clinical_visits) {
		this.clinical_visits = clinical_visits;
	}
	public List<tasks> getTasks() {
		return tasks;
	}
	public void setTasks(List<tasks> tasks) {
		this.tasks = tasks;
	}
	public List<notes> getNotes() {
		return notes;
	}
	public void setNotes(List<notes> notes) {
		this.notes = notes;
	}
	public List<udid_info> getUdid_info() {
		return udid_info;
	}
	public void setUdid_info(List<udid_info> udid_info) {
		this.udid_info = udid_info;
	}
	private List<clinical_visits> clinical_visits;
	private List<tasks> tasks;
	private List<notes> notes;
	private List<udid_info> udid_info;
	
//	 public pat_visit_task_notes_uuid(List<patientObj> patientObj1) {}
	 
	    public pat_visit_task_notes_uuid(List<patientObj>patientObj, List<clinical_visits> clinicalVisits,List<tasks> tasks,List<notes> notes,List<udid_info> udid_info) {
	    	
	        this.patientObj = patientObj;
	        this.clinical_visits = clinicalVisits;
	        this.tasks = tasks;
	        this.notes = notes;
	        this.udid_info = udid_info;
	        
	    }

}
