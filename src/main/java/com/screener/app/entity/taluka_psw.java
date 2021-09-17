package com.screener.app.entity;

import java.util.List;

public class taluka_psw {
	
	private List<social_worker> social_worker;
	public List<social_worker> getSocial_worker() {
		return social_worker;
	}
	public void setSocial_worker(List<social_worker> social_worker) {
		this.social_worker = social_worker;
	}
	public List<taluka_master> getTaluka_master() {
		return taluka_master;
	}
	public void setTaluka_master(List<taluka_master> taluka_master) {
		this.taluka_master = taluka_master;
	}
	
	public List<taluka_supervisor> getTaluka_supervisor() {
		return taluka_supervisor;
	}
	public void setTaluka_supervisor(List<taluka_supervisor> taluka_supervisor) {
		this.taluka_supervisor = taluka_supervisor;
	}
	private List<taluka_master> taluka_master;
	private List<taluka_supervisor> taluka_supervisor;
	private List<group_data> group_data;
	private List<supervisor> supervisor;

	 public List<supervisor> getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(List<supervisor> supervisor) {
		this.supervisor = supervisor;
	}
	public taluka_psw() {}
	 
	    public taluka_psw(List<social_worker> social_worker, List<taluka_master> taluka_master) {
	    	
	        this.social_worker = social_worker;
	        this.taluka_master = taluka_master;
	        
	        
	    }
 public taluka_psw(List<social_worker> social_worker, List<taluka_master> taluka_master,List<taluka_supervisor> taluka_supervisor,List<group_data> group_data,List<supervisor> supervisor) {
	    	
	        this.social_worker = social_worker;
	        this.taluka_master = taluka_master;
	        this.taluka_supervisor = taluka_supervisor;
	        this.group_data = group_data;
	        this.supervisor = supervisor;
	        
	        
	    }
public List<group_data> getGroup_data() {
	return group_data;
}
public void setGroup_data(List<group_data> group_data) {
	this.group_data = group_data;
}

}
