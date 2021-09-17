package com.screener.app.controller;

	import java.util.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.screener.app.exception.*;
import com.screener.app.entity.*;
import com.screener.app.services.IUserService;
import com.screener.app.services.IloginService;

import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.web.bind.annotation.PathVariable;
//	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


	@RestController
	
	@CrossOrigin(origins="*",allowedHeaders="*")
	public class usersController {
		@Autowired
		private IUserService ls;
		
		
		@GetMapping("/login1/{role_type}")
		public ResponseEntity<List<users>> getAlllogins1(@PathVariable(value = "role_type") int role_type) {
			
			System.out.println("hello");
			List<users> list = ls.getAlllogins(role_type);
			return new ResponseEntity<List<users>>(list, HttpStatus.OK);
		}
		
		
		
		
		
		//Kshema login rest call //need to include JWT 
		@RequestMapping(value = "/login1/post", method = RequestMethod.POST)
		public String check(@Valid @RequestBody users usr)
		{	
			//System.out.println("hello1");
			return ls.checklogin(usr);
		
		}
		
		
		@PostMapping("/login1")
		public String addlogin(@Valid @RequestBody users lg, UriComponentsBuilder builder) {
			
			
	                boolean flag = ls.addlogin(lg);
	                
	                System.out.println("+++passwordddddd" + flag);
	                System.out.println("username" + lg.getUser_name());
	                if (flag == false) {
	        	
	                return "already exists ";
	                
	                }
	               // HttpHeaders headers = new HttpHeaders();
	               // headers.setLocation(builder.path("/login/{id}").buildAndExpand(lg.getHospitalid()).toUri());
	               return "success";
	              
		}
		
		@PostMapping("/addsupervisor")
		public String addsupervisor(@Valid @RequestBody taluk_supervisor_obj taluk_supervisor_obj) {
			
			
	                boolean flag = ls.addloginsuper(taluk_supervisor_obj);
	                
	              
	                if (flag == false) {
	        	
	                return "already exists ";
	                
	                }
	               return "success";
	              
		}
		//add social worker
		@PostMapping("/addpsw/{taluka_id}")
		public String addpsw(@Valid @RequestBody psw_supervisor_obj psw_supervisor_obj,@PathVariable(value = "taluka_id") int taluka_id) {
			
			
	                boolean flag = ls.addloginpsw(psw_supervisor_obj,taluka_id);
	                
	              
	                if (flag == false) {
	        	
	                return "already exists ";
	                
	                }
	               return "success";
	              
		}
		
		
		
		
		//new code
		//to all user data
		@GetMapping("/getUsers/{role}")

		public ResponseEntity<List<users>> getUsers(@PathVariable(value = "role") int role) {
			
			List<users> list = ls.getAlllogins(role);
			return new ResponseEntity<List<users>>(list, HttpStatus.OK);
		}
			
		
		
		//to all psw data
		@GetMapping("/getPsws")

		public ResponseEntity<List<social_worker>> getPsws() {
			
			List<social_worker> list = ls.getPsws();
			return new ResponseEntity<List<social_worker>>(list, HttpStatus.OK);
		}
		
		//to all supervisor data
				@GetMapping("/getSupervisors")

				public ResponseEntity<List<supervisor>> getSupervisors() {
					
					List<supervisor> list = ls.getSupervisors();
					return new ResponseEntity<List<supervisor>>(list, HttpStatus.OK);
				}
		
		
		
		
		//to get individual user data
		@GetMapping("/getUser/{users_id}/{role}")

		public ResponseEntity<psw_supervisor_obj> getUser(@PathVariable(value = "users_id") int users_id,@PathVariable(value = "role") String role) {
			
			psw_supervisor_obj psw_supervisor_obj = ls.getUser(users_id,role);
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return new ResponseEntity<psw_supervisor_obj>(psw_supervisor_obj, HttpStatus.OK);
		}
				
		
		//to edit user
		@PostMapping("/editUser/{users_id}/{role_type}/{status}/{taluka_id}/{social_worker_id}/{supervisor_id}")
		public String updateUser(@Valid @RequestBody users lg,@PathVariable(value = "users_id") int users_id,@PathVariable(value = "role_type") int role_type,@PathVariable(value = "status") String status,@PathVariable(value = "taluka_id") int taluka_id,@PathVariable(value = "social_worker_id") int social_worker_id,@PathVariable(value = "supervisor_id") int supervisor_id, UriComponentsBuilder builder) {
         ls.updateUser(lg,users_id,role_type,status,taluka_id,social_worker_id,supervisor_id);
        
        
       return "success";
	              
		}	
		
		//to all supervisor data
		@GetMapping("/getTalukaSupervisors")

		public ResponseEntity<List<supervisor>> getTalukaSupervisors() {
			
			List<supervisor> list = ls.getTalukaSupervisors();
			return new ResponseEntity<List<supervisor>>(list, HttpStatus.OK);
		}
		
		//to group_data
				@GetMapping("/getGroupData/{supervisor_id}")

				public ResponseEntity<List<group_data>> getGroupData(@PathVariable(value = "supervisor_id") int supervisor_id) {
					
					List<group_data> list = ls.getGroupData(supervisor_id);
					return new ResponseEntity<List<group_data>>(list, HttpStatus.OK);
				}
		
		//end of new code
		
		
		
		
		
		
		@PostMapping("/updatelogin1/{hps_id}")
		public String updatelogin(  @Valid @RequestBody login lg,@PathVariable(value = "hps_id") int hps_id, UriComponentsBuilder builder) {
	                 ls.updatelogin(lg,hps_id);
	                
	                
	               return "success";
	              
		}
		
		@GetMapping("/updateflag1/{hps_id}/{flag_type}/{flag}")
		public String updateFlag(@PathVariable(value = "hps_id") int hps_id,@PathVariable(value = "flag_type") int flag_type,@PathVariable(value = "flag") int flag) {

		System.out.println("hello"+hps_id+"  flag_type  "+flag_type+" flag "+flag);
		String str = ls.updateFlag(flag_type,flag,hps_id);
		return "working";
		}
		
		@GetMapping("/updateflagByemail1/{email}")
		public String updateflagByemail(@PathVariable(value = "email") String email) {
			System.out.println("CHECKING ADMIN EMAIL PART"+email);
			String str = ls.updateflagByemail(email);
			return "working";
		}
		
		
		@GetMapping("/forgotemail1/{email}")
		public String forgotemail(@PathVariable(value = "email") String email) {
			String str = ls.forgotemail(email);
			return "working";
		}
		
		//void updatepassword(String password,String email);
		@GetMapping("/updatepwd1/{email}")
		public String updatepassword(@PathVariable(value = "email") String email,@RequestParam String password) {
			 ls.updatepassword(password,email);
			return "updated";
		}
	}