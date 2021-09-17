package com.screener.app.entity;

import java.util.List;

public class taluka_supervisor_total {
	
	
    public List<supervisor> getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(List<supervisor> supervisor) {
		this.supervisor = supervisor;
	}
	public List<taluka> getTaluka_master() {
		return taluka_master;
	}
	public void setTaluka_master(List<taluka> taluka_master) {
		this.taluka_master = taluka_master;
	}
	public List<taluka_supervisor> getTaluka_supervisor() {
		return taluka_supervisor;
	}
	public void setTaluka_supervisor(List<taluka_supervisor> taluka_supervisor) {
		this.taluka_supervisor = taluka_supervisor;
	}
	
	private List<supervisor> supervisor;
	private List<taluka> taluka_master;
    private List<taluka_supervisor> taluka_supervisor;
    

    public taluka_supervisor_total() {}
	 
    public taluka_supervisor_total(List<supervisor> supervisor1, List<taluka> taluka1,List<taluka_supervisor> taluka_supervisor1) {
    	
        this.supervisor = supervisor1;
        this.taluka_master = taluka1;
        this.taluka_supervisor = taluka_supervisor1;
        
    }
}
