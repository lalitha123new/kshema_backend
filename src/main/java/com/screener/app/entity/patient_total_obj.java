package com.screener.app.entity;


public class patient_total_obj {
	
	    public clinical_visits getClinical_visits() {
		return clinical_visits;
	}

	public void setClinical_visits(clinical_visits clinical_visits) {
		this.clinical_visits = clinical_visits;
	}

	public patientObj getPatientObj() {
		return patientObj;
	}

	public void setPatientObj(patientObj patientObj) {
		this.patientObj = patientObj;
	}
	
	public tasks getTasks() {
		return tasks;
	}

	public void setTasks(tasks tasks) {
		this.tasks = tasks;
	}

		private clinical_visits clinical_visits;
	    private patientObj patientObj;
	    
	    

		private tasks tasks;

       //public clinical_visits  clinical_visits() {}
       //public patientObj       patientObj() {}
	    public patient_total_obj() {}
	    public patient_total_obj(clinical_visits clinical_visits, patientObj patientObj,tasks tasks) {
	    	
	        this.clinical_visits = clinical_visits;
	        this.patientObj = patientObj;
	        this.tasks = tasks;
	        
	    }

	    public patient_total_obj(clinical_visits clinical_visits, patientObj patientObj) {
	        this.clinical_visits = clinical_visits;
	        this.patientObj = patientObj;
	        
	    }
	    public patient_total_obj(tasks tasks, patientObj patientObj) {
	        this.tasks = tasks;
	        this.patientObj = patientObj;
	        
	    }

	    
}