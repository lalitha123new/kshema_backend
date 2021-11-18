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
import com.screener.app.services.inotificationService;
import com.screener.app.entity.*;
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
//	import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


	@RestController
	
	@CrossOrigin(origins="*",allowedHeaders="*")
	public class notificationController {
		@Autowired
		private inotificationService ns;
		
		
		//addNotification
		@PostMapping("/addNotification")
		public String addNotification(@Valid @RequestBody notes notificationObj, UriComponentsBuilder builder) {
			
                int flag = ns.addNotification(notificationObj);
                return "true";
	                
		}
		
		//getNotification
		@GetMapping("/getnotification/{patient_uuid}")
		public List<notes> getNotification(@PathVariable String patient_uuid) {
			
			List<notes> notification1 = ns.getNotification(patient_uuid);
			//return new ResponseEntity<List<login>>(list, HttpStatus.OK);
			return notification1;
		}
		
		
	    //getAllNotesforSupervisor
		@GetMapping("/getnotificationlist/{supervisor_id}")
		public List<notes> getNotification1(@PathVariable int supervisor_id) {
			
			List<notes> notification1 = ns.getNotificationlist(supervisor_id);
			
			return notification1;
		}
		
		//getAllUnreadNotesforSupervisor
				@GetMapping("/getUnreadnotificationlist/{supervisor_id}")
				public List<notes> getUnreadnotificationlist(@PathVariable int supervisor_id) {
					
					List<notes> notification1 = ns.getUnreadnotificationlist(supervisor_id);
					
					return notification1;
				}
		//update notificationstatus of supervisor notes		
		@GetMapping("/updateReadNotification1/{notes_uuid}")
		public int updateReadNotification1(@PathVariable String notes_uuid) {
			System.out.println("CHECK");
			int notification1 = ns.updateReadNotification1(notes_uuid);
			return notification1;
		}
		
		
		
		//getNotification Count
		@GetMapping("/notificationscount/{portal_flag}")
		public int getUnreadNotificationCount(@PathVariable(value = "portal_flag") int portal_flag) {
			
			int notification1 = ns.getUnreadNotificationCount(portal_flag);
			//return new ResponseEntity<List<login>>(list, HttpStatus.OK);getUnreadNotificationCountExt
			return notification1;
		}
		
		
		@GetMapping("/getallnotificationfromsvr/{psw_id}")
		public List<notes> getallnotificationfromsvr(@PathVariable(value = "psw_id") int psw_id) {
			
			List<notes> notes1 = ns.getallnotificationfromsvr(psw_id);
			//return new ResponseEntity<List<login>>(list, HttpStatus.OK);
			return notes1;
		}
		
		@GetMapping("/getImage/{notes_uuid}")
  		public String getImage(@PathVariable(value = "notes_uuid") String notes_uuid) {
  			
  			System.out.println("hello");
  			String file1 = ns.getImage(notes_uuid);
  			

  	       
			return file1;
  		}
		
		
		
		@GetMapping("/allnotification/{portal_flag}/{hospital_id}")
		public List<pat_notification> getAllNotification(@PathVariable(value = "portal_flag") int portal_flag, @PathVariable(value = "hospital_id") int hospital_id) {
			
			List<pat_notification> notification1 = ns.getAllNotification(portal_flag,hospital_id);
			//return new ResponseEntity<List<login>>(list, HttpStatus.OK);
			return notification1;
		}
		
		
		
		
		@GetMapping("/notificationscount/{portal_flag}/{hospital_id}")
		public int getUnreadNotificationCount(@PathVariable(value = "portal_flag") int portal_flag,@PathVariable(value = "hospital_id") int hospital_id) {
			
			int notification1 = ns.getUnreadNotificationCountExt(portal_flag,hospital_id);
			//return new ResponseEntity<List<login>>(list, HttpStatus.OK);
			return notification1;
		}
		
		
		@GetMapping("/updateReadNotification/{notification_id}")
		public int updateReadNotification(@PathVariable(value = "notification_id") int notification_id) {
			
			int notification1 = ns.updateReadNotification(notification_id);
			return notification1;
		}
		
	
		
		@GetMapping("/allnotification/{portal_flag}")
		public List<notification> getAllNotification1(@PathVariable(value = "portal_flag") int portal_flag) {
			
			List<notification> notification1 = ns.getAllHospitalNotification(portal_flag);
			//return new ResponseEntity<List<login>>(list, HttpStatus.OK);
			return notification1;
		}
		
		
		
	
	}