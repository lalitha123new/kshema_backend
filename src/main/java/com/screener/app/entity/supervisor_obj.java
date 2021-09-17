package com.screener.app.entity;

import java.util.List;

public class supervisor_obj {
	private users user;
	   
	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public List<group_data> getGroup_data() {
		return group_data;
	}

	public void setGroup_data(List<group_data> group_data) {
		this.group_data = group_data;
	}

	public social_worker getSocial_worker() {
		return social_worker;
	}

	public void setSocial_worker(social_worker social_worker) {
		this.social_worker = social_worker;
	}

	public supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(supervisor supervisor) {
		this.supervisor = supervisor;
	}

	private List<group_data> group_data;
	
	
	private social_worker social_worker;
	
	private supervisor supervisor;

}
