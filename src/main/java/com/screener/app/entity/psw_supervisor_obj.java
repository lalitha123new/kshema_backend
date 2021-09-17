package com.screener.app.entity;


public class psw_supervisor_obj {
	
	   

		private users user;
	   
		private group_data group_data;
		
		
		private social_worker social_worker;
		
		private supervisor supervisor;
	    
        

		
	   
	    
	    
	    public supervisor getSupervisor() {
			return supervisor;
		}
		public void setSupervisor(supervisor supervisor) {
			this.supervisor = supervisor;
		}
		public users getUser() {
			return user;
		}
		public void setUser(users user) {
			this.user = user;
		}
		
		public group_data getGroup_data() {
			return group_data;
		}
		public void setGroup_data(group_data group_data) {
			this.group_data = group_data;
		}
		public social_worker getSocial_worker() {
			return social_worker;
		}
		public void setSocial_worker(social_worker social_worker) {
			this.social_worker = social_worker;
		}

		
		 public psw_supervisor_obj() {}
		 
		    public psw_supervisor_obj(users user, group_data group_data,social_worker social_worker,supervisor supervisor) {
		    	
		        this.user = user;
		        this.group_data = group_data;
		        this.social_worker = social_worker;
		        this.supervisor = supervisor;
		        
		    }
}