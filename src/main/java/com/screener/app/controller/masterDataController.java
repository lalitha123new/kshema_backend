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
import com.screener.app.services.IkshemaidGeneratorService;
import com.screener.app.services.IloginService;
import com.screener.app.services.ImasterDataService;

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
	public class masterDataController {
		@Autowired
		private ImasterDataService mds;
		
		
		@GetMapping("/getTasklist")
		public  ResponseEntity<List<task_master>> getTasklist() {
			
			List<task_master> task_masterdata = mds.getTasklist();
			return new ResponseEntity<List<task_master>>(task_masterdata, HttpStatus.OK);
		}
		
		
		 
		
		
		
		
	}