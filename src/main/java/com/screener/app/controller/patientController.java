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
import com.screener.app.services.IpatientService;

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
	public class patientController {
		@Autowired
		private IpatientService ps;
		
		@Autowired
		private IkshemaidDao kshemaidDao;
		
		

		//kshema patient object save
		@PostMapping("/patient/{meta_data}/")
		public String addpat(@RequestBody patient_total_obj pat_tobj,@PathVariable(value = "meta_data") String meta_data) {
			
			System.out.println("meta_data-  "+meta_data);
			patientObj pat=pat_tobj.getPatientObj();
			clinical_visits cv=pat_tobj.getClinical_visits();
		    tasks tasks = pat_tobj.getTasks();
		    
		    String kshema_id = kshemaidDao.getKshemaid("1", "1", "1");
		    System.out.println("kshema_id  "+kshema_id);
		    
			System.out.println("firstStep1--"+pat);
			           pat.setKshema_id(kshema_id);

	                boolean flag = ps.addpatient(pat);
	                cv.setPatient_uuid(pat.getPatient_uuid());
	                boolean flag1 = ps.addclinicalVisite(cv);
	                
	                boolean flag2 = ps.addtasks(tasks);
	                
	                System.out.println("firstStep2--"+cv);
	               
	                if (flag != true) { 
	                return "false";
	                }
	                return "true";
	                
		}
		
		//add saparete patient object
		@PostMapping("/single_patient/{meta_data}")
		public String addpatone(@RequestBody patientObj pat_tobj,@PathVariable(value = "meta_data") String meta_data) {
			System.out.println("meta_data-  "+meta_data.split("@"));
			String[] meta_data_array = meta_data.split("@");
			System.out.println("meta_data1-  "+meta_data_array[0]);
		    String kshema_id = kshemaidDao.getKshemaid(meta_data_array[0], meta_data_array[1], meta_data_array[2]);
		    System.out.println("kshema_id  "+kshema_id);
		    pat_tobj.setKshema_id(kshema_id);
	                boolean flag = ps.addpatient(pat_tobj);
	               
	                if (flag != true) { 
	                return "false";
	                }
	                return "true";
	                
		}
		
		
		
		//get List of patients
		//@Secured
		@GetMapping("/patientsList/{sw_id}")
		public ResponseEntity<List<patient_total_obj>> getPatientList(@PathVariable(value = "sw_id") int sw_id) {
			
			List<patient_total_obj> patientList = ps.getPatientList(sw_id);
			return new ResponseEntity<List<patient_total_obj>>(patientList, HttpStatus.OK);
		}
		
		//get List of active patients
		//@Secured
		@GetMapping("/patientsListActive/{sw_id}")
		public ResponseEntity<List<patientObj>> getPatientListActive(@PathVariable(value = "sw_id") int sw_id) {
			
			List<patientObj> patientList = ps.getPatientListActive(sw_id);
			return new ResponseEntity<List<patientObj>>(patientList, HttpStatus.OK);
		}
		
		//get particular patient details 
		@GetMapping("/patientdetails/{patient_uuid}")
		public ResponseEntity<List<patientObj>> getPatientDetails(@PathVariable(value = "patient_uuid") String patient_uuid) {
			
			List<patientObj> patientDetails = ps.getPatientDetails(patient_uuid);
			return new ResponseEntity<List<patientObj>>(patientDetails, HttpStatus.OK);
		}
		
		//get all patients data for a particular psw
				@GetMapping("/getpatientsData/{group_data_id}")
				public ResponseEntity<List<pat_visit_task_notes_uuid>> getPatientsData(@PathVariable(value = "group_data_id") int group_data_id) {
					
					List<pat_visit_task_notes_uuid> PatientsData = ps.getPatientsData(group_data_id);
					return new ResponseEntity<List<pat_visit_task_notes_uuid>>(PatientsData, HttpStatus.OK);
				}
		
		
		
		
		@PostMapping("/addVisit")
		public String addVisit(@RequestBody clinical_visits cv) {
			boolean flag = ps.addclinicalVisite(cv);
			 if (flag != true) {
	        	    
	                return "false";
	                }
			  return "true";
		}
		
		@PostMapping("/addTask")
		public String addTask(@RequestBody tasks tasks) {
			
			boolean flag = ps.addtasks(tasks);
			
			 if (flag != true) {
	        	    
	                return "false";
	                }
			  return "true";
		}
		
		
		//keshema edit  patient 
		@PostMapping("/updatePatient")
		public String updatepatient(@RequestBody patientObj pat_tobj) {
			
			
	                boolean flag = ps.updatepatient(pat_tobj);
	        
	               
	                if (flag != true) {
	        	    
	                return "false";
	                
	                }

	                return "true";
	                
		}
		
		//update Task
		@PostMapping("/updateTask")
		public String updateTask(@RequestBody tasks tasks) {
			
			boolean flag = ps.updatetasks(tasks);
			
			 if (flag != true) {
	        	    
	                return "false";
	                
	                }
			  return "true";
		}
		
		
		
		
		
	//update KShema_id on localDB;
		
		@GetMapping("/updateKshemaid/{patient_uuid}")
		public String getkshemaidsync(@PathVariable(value = "patient_uuid") String patient_uuid) {
			 
			String kshema_id = ps.getkshemaidsync(patient_uuid);
			return kshema_id;
		}	
		
	//taskMaster list
		
		@GetMapping("/taskMasterList")
		public ResponseEntity<List<task_master>> getTaskMasterList() {
			
			List<task_master> task_master = ps.getTaskMasterList();
			
			return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
		}
		
		
		//add udid info
		@PostMapping("/addudid")
        public String addudid(@RequestBody udid_info udid_info) {
			
			boolean flag = ps.addudid(udid_info);
			
			 if (flag != true) {
	        	    
	                return "false";
	                }
			  return "true";
		}
	
//****************************************************Supervisor Rest Calls************************************************************//
		//get all patients with latest Clinical consultation follow-up date 
		@GetMapping("/getPatientsList/{api_type_id}/{group_id}")
		public ResponseEntity<List<patient_total_obj>> getPatientsList(@PathVariable(value ="api_type_id") int api_type_id,@PathVariable(value ="group_id") int group_id) {
			
			List<patient_total_obj> patient_total_obj = ps.getPatientsList(api_type_id,group_id);
			
			return new ResponseEntity<List<patient_total_obj>>(patient_total_obj, HttpStatus.OK);
			//return null;
		}
		
		
	   //dashboard view - today visits
		@GetMapping("/getTodayVisits")
		public ResponseEntity<List<patientObj>> getTodayVisits() {
			
			List<task_master> task_master = ps.getTodayVisits();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
		
		//dashboard view - today tasks
		@GetMapping("/getTodaytasks/{api_type_id}")
		public ResponseEntity<List<patient_total_obj>> getTodaytasks(@PathVariable(value ="api_type_id") int api_type_id) {
			
			List<patient_total_obj> patient_total_obj = ps.getTodaytasks(api_type_id);
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return new ResponseEntity<List<patient_total_obj>>(patient_total_obj, HttpStatus.OK);
		}
		
		
	    //dashboard view - upcoming visits
		@GetMapping("/getUpcomingVisits")
		public ResponseEntity<List<patientObj>> getUpcomingVisits() {
			
			List<task_master> task_master = ps.getUpcomingVisits();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
		
		 //dashboard view - upcoming tasks
		@GetMapping("/getUpcomingTasks")
		public ResponseEntity<List<patientObj>> getUpcomingTasks() {
			
			List<task_master> task_master = ps.getUpcomingTasks();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
		//dashboard view - overdue visits
		@GetMapping("/getOverdueVisits")
		public ResponseEntity<List<patientObj>> getOverdueVisits() {
			
			List<task_master> task_master = ps.getOverdueVisits();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
	 //dashboard view - overdue tasks	
		@GetMapping("/getOverduetasks")
		public ResponseEntity<List<patientObj>> getOverduetasks() {
			
			List<task_master> task_master = ps.getOverduetasks();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
		//dashboard view - completedVisits
		@GetMapping("/getCompletedVisits")
		public ResponseEntity<List<patientObj>> getCompletedVisits() {
			
			List<task_master> task_master = ps.getCompletedVisits();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
	
		
	//dashboard view - completed tasks
		@GetMapping("/getCompletedTasks")
		public ResponseEntity<List<patientObj>> getCompletedTasks() {
			
			List<task_master> task_master = ps.getCompletedTasks();
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return null;
		}
		
		//get all the consultation data of a patient and display the same in the history page
		@GetMapping("/getConsultationHistory/{patient_uuid}")
		public ResponseEntity<List<patient_total_obj>> getConsultationHistory(@PathVariable(value = "patient_uuid") String patient_uuid) {
			System.out.println("patient_uuid==   "+patient_uuid);
			List<patient_total_obj> patient_total_obj = ps.getConsultationHistory(patient_uuid);
			
			return new ResponseEntity<List<patient_total_obj>>(patient_total_obj, HttpStatus.OK);
		}
		
		//after login metadata of pswlogin
		@GetMapping("/getmetaDataLogin/{users_id}/{role}")
		public ResponseEntity<psw_supervisor_obj> getmetaDataLogin(@PathVariable(value = "users_id") int users_id,@PathVariable(value = "role") String role) {
			
			psw_supervisor_obj psw_supervisor_obj = ps.getmetaDataLogin(users_id,role);
			
			//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
			return new ResponseEntity<psw_supervisor_obj>(psw_supervisor_obj, HttpStatus.OK);
		}
		
		//after login metadata of pswlogin
				@GetMapping("/getmetaDataLogin1/{users_id}/{role}")
				public ResponseEntity<supervisor_obj> getmetaDataLogin1(@PathVariable(value = "users_id") int users_id,@PathVariable(value = "role") String role) {
					
					supervisor_obj supervisor_obj = ps.getmetaDataLogin1(users_id,role);
					
					//return new ResponseEntity<List<task_master>>(task_master, HttpStatus.OK);
					return new ResponseEntity<supervisor_obj>(supervisor_obj, HttpStatus.OK);
				}
		
		
		
		
		
		
		
		
		
		
		//==============================================================================//
		
		@GetMapping("/patient")
		public ResponseEntity<List<patient>> getAllpats() {
			List<patient> list = ps.getAllpatients();
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		
		@GetMapping("/patient/doctors")
		public List<String> getAlldoc() {
			 System.out.println("hello testing");
			List<String> list = ps.getAlldocs();
			return list;
		}
		
		
		@GetMapping("/patient/{username}")
		public ResponseEntity<List<patient>> getAllpats(@PathVariable(value = "username") String username) {
			List<patient> list = ps.getAllpatients(username);
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		
		
		
		@GetMapping(path="/patienttemp/{sample_id}")
		public ResponseEntity<List<patient>> getpatientById(@PathVariable(value = "sample_id") int sample_id) {
		
		
			List<patient> list = ps.findByUserid(sample_id);
			
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		
		
	   
		
		@GetMapping(path="/hospital/{username}")
		public ResponseEntity<Logintemp> Logintemp(@PathVariable(value = "username") String username) {
			//int sample_id=Integer.valueOf(sample);
			Logintemp list = ps.gethospitalbyusername(username);
			return new ResponseEntity<Logintemp>(list, HttpStatus.OK);
		}
		
		@GetMapping(path="/hospitalDetails/{hospital_id}")
		public ResponseEntity<Logintemp> hosdetails(@PathVariable(value = "hospital_id") int hospital_id) {
			//int sample_id=Integer.valueOf(sample);
			Logintemp list = ps.gethospitalbyhosid(hospital_id);
			
			return new ResponseEntity<Logintemp>(list, HttpStatus.OK);
		}
			
		
		@GetMapping("/patientsort/{sort}/{username}")
		public ResponseEntity<List<patient>> getSort(@PathVariable(value = "sort") String sort,@PathVariable(value = "username") String username) {
			List<patient> list = ps.getSort(sort,username);
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		@RequestMapping(value = "/patientfilter/{filter}/{username}", method = RequestMethod.POST,consumes="text/plain")
		public ResponseEntity<List<patient>> getFilter(@PathVariable(value = "filter") String filter,@PathVariable(value = "username") String username,@Valid @RequestBody String value) {
			List<patient> list = ps.getFilter(filter,username,value);
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		
		//NimhansLab RestCalls
		@GetMapping(path="/patientbyExtId/{extr_id}")
		public ResponseEntity<List<patient>> getpatientByExtId(@PathVariable(value = "extr_id") String extr_id) {
		
		
			List<patient> list = ps.findByUserExtId(extr_id);
			
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		
		      //NimhansLab RestCalls
				@GetMapping(path="/extidByNpnum")
				public String getNpnoByExtr_id(@QueryParam(value = "npnum") String npnum) {
				
				
					
					String Ext_id = ps.findNpnum(npnum);
					
					
					return Ext_id;
				}
				
				//update patienSample
				//update patienSample
				@PostMapping("/updatepatient1")
				public int Updatepat1(@Valid @RequestBody patient pat, UriComponentsBuilder builder) {
					
					
			                int flag = ps.updateSample(pat);
			               
			         
			                return flag;
			                
				}
				
				//Lab Test details controller starts here
				@GetMapping("/labtestDetails")
				public List<lab_test_details> getLabtestDetails() {
					 System.out.println("hello testing");
					List<lab_test_details> testlist = ps.getLabtestDetails();
					return testlist;
				}
				
				@PostMapping(path="/labtestDetailsByMatch")
				public lab_test_details labtestMatch(@Valid @RequestBody lab_test_details lab_test_details2,UriComponentsBuilder builder) {



				lab_test_details lab_test_details1 = ps.labtestMatch(lab_test_details2.getExlab_specimen(),lab_test_details2.getExlab_biopsy_type(),lab_test_details2.getExlab_fixative());


				return lab_test_details1;
				}
				
				
				
				

							
		
	}