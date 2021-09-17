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
	public class adminController {
		@Autowired
		private IpatientService ps;
		
		@Autowired
		private IkshemaidDao kshemaidDao;
		
		
		@GetMapping("/addTaluka")
		public ResponseEntity<List<patient>> getAllpats() {
			List<patient> list = ps.getAllpatients();
			return new ResponseEntity<List<patient>>(list, HttpStatus.OK);
		}
		
		@GetMapping("/getSigningkey")
		public String getSigningkey() {
			
			String token="afa088ee05a4c69c4ace5a085b19160e";

			String hmac="buXLsE2844i4ki/nP3zJHYC1QbMlDrc1q+NL3JajeSlX7IoJsnQmo1GWlTuM7BDf";

			String method="POST";

			//narasimham ip
			//String ip="117.247.31.140";
			//lalitha ip
			String ip="117.254.36.251";
			//nitesh ip
			//String ip="15.206.2.116";
			


			String jsoninput="{\n" +

			"\"source_identifier\":\"10004\",\n" +

			"\"patient_identifier_type\":\"uhid\",\n" +

			"\"patient_identifier\":\"20200000063\"\n" +

			"}";
			
			String  key = IpatientService.getSigningkey(token, hmac, method, ip, jsoninput);
			return key;
		}
							
		
	}