package com.screener.app.controller;
import java.util.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.screener.app.util.Secured;
import com.screener.app.dao.IkshemaidDao;
import com.screener.app.dao.kshemaidDao;
import com.screener.app.entity.*;
import com.screener.app.services.ItalukaService;

import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


	@RestController

	@CrossOrigin(origins="*",allowedHeaders="*")
	
		
public class talukaController {
		@Autowired
		private ItalukaService ts;
		
		@Autowired
		private IkshemaidDao kshemaidDao;
		
		
		//add new taluka
		@PostMapping("/addtaluka/{supervisor_id}")
		public String addtaluka(@Valid @RequestBody taluka tk, UriComponentsBuilder builder,@PathVariable(value = "supervisor_id") int supervisor_id) {
			
	                boolean flag = ts.addtaluka(tk,supervisor_id);
	                if (flag == false) {
	        	
	                return "already exists ";
	                
	                }
	               return "success";
	              
		}
		
		//get all taluka
		@GetMapping("/getAlltalukas")
		public ResponseEntity<List<taluka>> getAlltalukas() {
			
			System.out.println("hello");
			List<taluka> list = ts.getAlltalukas();
			return new ResponseEntity<List<taluka>>(list, HttpStatus.OK);
		}
		
		//get  taluka details
		@GetMapping("/getTaluka/{taluka_master_id}")
		public ResponseEntity<List<taluka>> getTaluka(@PathVariable(value = "taluka_master_id") int taluka_master_id) {
			
			System.out.println("hello");
			List<taluka> list = ts.getTaluka(taluka_master_id);
			return new ResponseEntity<List<taluka>>(list, HttpStatus.OK);
		}
				
		//to edit user
		@PostMapping("/edittaluka/{taluka_master_id}/{supervisor_id}")
		public String updateUser(  @Valid @RequestBody taluka tk,@PathVariable(value = "taluka_master_id") int taluka_master_id,@PathVariable(value = "supervisor_id") int supervisor_id, UriComponentsBuilder builder) {
	                 ts.updateTaluka(tk,taluka_master_id,supervisor_id);
	                
	                
	               return "success";
	              
		}	
		
		//get all districts
				@GetMapping("/getAllDistricts")
				public ResponseEntity<List<district_master>> getAllDistricts() {
					
					System.out.println("hello");
					List<district_master> list = ts.getAllDistricts();
					return new ResponseEntity<List<district_master>>(list, HttpStatus.OK);
				}
				
				//get all phcs
				@GetMapping("/getAllPhcs")
				public ResponseEntity<List<phc_master>> getAllPhcs() {
					
					System.out.println("hello");
					List<phc_master> list = ts.getAllPhcs();
					return new ResponseEntity<List<phc_master>>(list, HttpStatus.OK);
				}
				
				//get all talukas,supervisor,taluka_supervisors data
				@GetMapping("/getAllTalukaSupervisors")
				public ResponseEntity<List<taluka_supervisor_total>> getAllTalukaSupervisors() {
					
					System.out.println("hello");
					List<taluka_supervisor_total> list = ts.getAllTalukaSupervisors();
					return new ResponseEntity<List<taluka_supervisor_total>>(list, HttpStatus.OK);
				}
				
				//get all talukas,psw data
				@GetMapping("/getAllTalukaPsws")
				public ResponseEntity<List<taluka_psw>> getAllTalukaPsws() {
					
					System.out.println("hello");
					List<taluka_psw> list = ts.getAllTalukaPsws();
					return new ResponseEntity<List<taluka_psw>>(list, HttpStatus.OK);
				}
				
				//get all talukas,psws,taluka supervisors data
				@GetMapping("/getAllTalukaPswsTalukaSupervisors")
				public ResponseEntity<List<taluka_psw>> getAllTalukaPswsTalukaSupervisors() {
					
					System.out.println("hello");
					List<taluka_psw> list = ts.getAllTalukaPswsTalukaSupervisors();
					return new ResponseEntity<List<taluka_psw>>(list, HttpStatus.OK);
				}
		

}
