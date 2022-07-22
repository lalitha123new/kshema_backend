package com.screener.app.services;

import  com.screener.app.entity.*;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public interface IpatientService {
	
	List<patient> getAllpatients(String username);
	
	Logintemp gethospitalbyusername(String username);
	
	Logintemp gethospitalbyhosid(int hos_id);
	
	List<patient> getSort(String sort,String username);
	
	List<patient> getFilter(String filter,String username,String value);
    
	List<patient> getAllpatients();
    // Patient getArticleById(int articleId);
    
	
	
	
    // String checklogin(Patient pat);
     //void updateArticle(Patient pat);
     //void deleteArticle(int id);
	
	List<String> getAlldocs();
    
	List<patient> findByUserid(int sample_id);
	List<patient> findByUserExtId(String extr_id);
	
	patient getpatientBytransId(int extr_id);
    public int getExtridByNpno(int npno);
    public int setSampleId(int extr_id,int sam_id);
    public int getNpnoByExtrid(int extr_id);

	List<login> getAllhospitallist();

	List<login> getHospitallist(int hospital_id);

	int updateImageUrl(String ImgeUrl, int checkImgSrc);

	public String findNpnum(String npnum);
	public int updateSample(patient pat);
	List<lab_test_details> getLabtestDetails();
	lab_test_details labtestMatch(String exlab_specimen, String exlab_biopsy_type, String exlab_fixative);

	   //kshema project
		boolean addpatient(patientObj pat);
		boolean addclinicalVisite(clinical_visits cv);
		boolean addtasks(tasks tasks);
		List<patient_total_obj> getPatientList(int sw_id);
		List<patientObj> getPatientListActive(int sw_id);
		List<patientObj> getPatientDetails(String patient_uuid);
		List<pat_visit_task_notes_uuid> getPatientsData(int  group_data_id);
		
		boolean updatepatient(patientObj pat);

		boolean updatetasks(tasks tasks);

		String getkshemaidsync(String patient_uuid);

		List<task_master> getTaskMasterList();

		boolean addudid(udid_info udid_info);
		

		//@@@ Supervisor @@@//
		List<patient_total_obj> getPatientsList(int api_type_id,int group_id);

		List<task_master> getTodayVisits();

		List<patient_total_obj> getTodaytasks(int api_type_id);

		List<task_master> getUpcomingVisits();

		List<task_master> getUpcomingTasks();

		List<task_master> getOverdueVisits();

		List<task_master> getOverduetasks();

		List<task_master> getCompletedVisits();

		List<task_master> getCompletedTasks();
		List<task_master> getallTasks(String patient_uuid);
		List<udid_info> getPatientUdid(String patient_uuid);
		List<counts> getCounts(String group_data_id,int supervisor_id);
		List<counts> getAllDataCounts();
		List<patientObj> getAllpatientsListActive();
		List<patient_total_obj> getAllPatientList();
		
		
		
		

		List<patient_total_obj> getConsultationHistory(String patient_uuid);

		psw_supervisor_obj getmetaDataLogin(int users_id,String role);
		supervisor_obj getmetaDataLogin1(int users_id,String role);

		 static String getSigningkey(String token,String hmac,String method,String ip,String jsoninput) {
			 	 
			 //String retval = "";

			 final String HMAC_SHA_512_ALGORITHM = "HmacSHA512";

			 final String userKey = token + hmac;

			 try {

			 // get an hmac_sha512 key from the raw key bytes

			 SecretKeySpec signingKey = new SecretKeySpec(userKey.getBytes(), HMAC_SHA_512_ALGORITHM);
			 
			 System.out.println("Check signinkey"+signingKey);
			 
			 // get an hmac_sha512 Mac instance and initialize with the signing key

			 Mac mac = Mac.getInstance(HMAC_SHA_512_ALGORITHM);

			 mac.init(signingKey);

			 // compute the hmac on input data bytes

			 String payload = (method.toUpperCase()).concat(ip).concat(jsoninput);

			 byte[] rawHmac = mac.doFinal(payload.getBytes());
			 System.out.println("Check rawHmac"+rawHmac);

			 // the value of hmac needs to placed in headers under the key api-hmac

			 return Hex.encodeHexString(rawHmac);

			 } catch (Exception e) {

			 throw new RuntimeException(e);

			 }
			 
			
		}
		
		
		
		
	
	
} 