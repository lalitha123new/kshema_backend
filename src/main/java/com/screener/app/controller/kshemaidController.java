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
	public class kshemaidController {
		@Autowired
		private IkshemaidGeneratorService kid;
		
		
		@GetMapping("/getKshemaid")
		public String getKshemaid() {
			
			String kshema_id = kid.getKshemaid("1", "1", "1");
			System.out.println("id---"+ kshema_id);
			return kshema_id;
		}
		
		
		 
		
		
		
		
	}