package com.screener.app.entity;


public class taluk_supervisor_obj {
	
	   

		private users user;
	   
		private taluka_supervisor taluka_supervisor;
		
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
		public taluka_supervisor getTaluka_supervisor() {
			return taluka_supervisor;
		}
		public void setTaluka_supervisor(taluka_supervisor taluka_supervisor) {
			this.taluka_supervisor = taluka_supervisor;
		}
		
		
		 public taluk_supervisor_obj() {}
		 
		    public taluk_supervisor_obj(users user, taluka_supervisor taluka_supervisor,supervisor supervisor) {
		    	
		        this.user = user;
		        this.taluka_supervisor = taluka_supervisor;
		        this.supervisor = supervisor;
		        
		    }
}