package com.screener.app.services;

import com.screener.app.entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.screener.app.dao.*;

@Service
public class patientService implements IpatientService {
	@Autowired
	private IpatientDao patientDao;
	
	@Override
	public List<patient> getAllpatients(){
		return patientDao.getAllpatients();
	}
	
	@Override
	public List<String> getAlldocs(){
		return patientDao.getAlldocs();
	}
	
	@Override
	public Logintemp gethospitalbyusername(String username){
		return patientDao.gethospitalbyusername(username);
	}
	
	@Override
	public Logintemp gethospitalbyhosid(int hospital_id){
		return patientDao.gethospitalbyhosid(hospital_id);
	}
	
	public List<patient> getSort(String sort,String username)
	{
		return patientDao.getSort(sort,username);
	}
	
	public List<patient> getFilter(String filter,String username,String value)
	{
		return patientDao.getFilter(filter,username,value);
	}
	
	@Override
	public synchronized boolean addpatient(patientObj pat){
		//--Here we can generate kshema_id
		//String ext_id = pat.getExtr_id()+(patientDao.findMax()+1); 
		//pat.setTransaction_id(ext_id);
         
		
		patientDao.addpatient(pat);
        return true;
	}
	
	@Override
	public List<patient> getAllpatients(String username){
		return patientDao.getAllpatients(username);
	}
	
	@Override
	public List<patient> findByUserid(int sample_id) {
		return patientDao.findByUserid(sample_id);
	}
	
	@Override
	public int getExtridByNpno(int npno){
		return patientDao.getExtridByNpno(npno);
	}
	
	public int getNpnoByExtrid(int extr_id)
	{
		return patientDao.getNpnoByExtrid(extr_id);
	}
	
	public int setSampleId(int extr_id,int sam_id) {
		return patientDao.setSampleId(extr_id,sam_id);
	}
	
	@Override
	 public patient getpatientBytransId(int extr_id)
	 {
		System.out.println("yest coming");
		 return patientDao.getpatientBytransId(extr_id);
	 }

	@Override
	public List<patient> findByUserExtId(String extr_id) {
		// TODO Auto-generated method stub
		
		return patientDao.findByUserExtId(extr_id);
		//return null;
	}

	
	//toget all hospitals
	@Override
	public List<login> getAllhospitallist() {
		// TODO Auto-generated method stub
		 return patientDao.getAllhospitallist();
	}

	@Override
	public List<login> getHospitallist(int hospital_id) {
		// TODO Auto-generated method stub
		
		return patientDao.getHospitallist(hospital_id);
		
	}
	
	//  to updateImageURL
	
	@Override
	public int updateImageUrl(String ImgeUrl,int checkImgSrc) {
		// TODO Auto-generated method stub
		
		return patientDao.toUpdateImageUrl(ImgeUrl,checkImgSrc);
		
	}
	
	
	public String findNpnum(String npnum) {
		
		return patientDao.findNpnum(npnum);
		
	}
	
	public int updateSample(patient pat) {

		return patientDao.updateSample(pat);

		}
	
	@Override
	public List<lab_test_details> getLabtestDetails() {

	return patientDao.getLabtestDetails();

	}
	
	@Override
	public lab_test_details labtestMatch(String exlab_specimen, String exlab_biopsy_type, String exlab_fixative) {
	// TODO Auto-generated method stub
	return patientDao.getlabtestMatch(exlab_specimen,exlab_biopsy_type,exlab_fixative);

	}

	
	//kshema details
	@Override
	public boolean addclinicalVisite(clinical_visits cv) {
		// TODO Auto-generated method stub
		
		return patientDao.addclinicalVisite(cv);
	}

	@Override
	public boolean addtasks(tasks tasks) {
		// TODO Auto-generated method stub
		return patientDao.addtasks(tasks);
	}

	@Override
	public List<patient_total_obj> getPatientList(int sw_id) {
		
		return  patientDao.getPatientList(sw_id);
	}

	@Override
	public List<patientObj> getPatientListActive(int sw_id) {
		
		return  patientDao.getPatientListActive(sw_id);
	}

	@Override
	public List<patientObj> getPatientDetails(String patient_uuid) {
		
		return  patientDao.getPatientDetails(patient_uuid);
	}
	
	@Override
	public List<pat_visit_task_notes_uuid> getPatientsData(int group_data_id) {
		
		return  patientDao.getPatientsData(group_data_id);
	}
	

	@Override
	public boolean updatepatient(patientObj pat) {
		
	        
			
	        return patientDao.updatepatient(pat);
	}

	@Override
	public boolean updatetasks(tasks tasks) {
		
		return patientDao.updatetasks(tasks);
	}

	@Override
	public String getkshemaidsync(String patient_uuid) {
		
		return patientDao.getkshemaidsync(patient_uuid);
	}

	@Override
	public List<task_master> getTaskMasterList() {
		// TODO Auto-generated method stub
		return patientDao.getTaskMasterList();
	}

	@Override
	public boolean addudid(udid_info udid_info) {
		
		return patientDao.addudid(udid_info);
	}
	
	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@supervisor methods

	@Override
	public List<patient_total_obj> getPatientsList(int api_type_id,int group_id) {
		// TODO Auto-generated method stub
		
		 
		return patientDao.getPatientsList(api_type_id,group_id);
	}

	@Override
	public List<task_master> getTodayVisits() {
		// TODO Auto-generated method stub
		patientDao.getTodayVisits();
		return null;
	}

	@Override
	public List<patient_total_obj> getTodaytasks(int api_type_id) {
		// TODO Auto-generated method stub
		return patientDao.getTodaytasks(api_type_id);
		
	}

	@Override
	public List<task_master> getUpcomingVisits() {
		// TODO Auto-generated method stub
		patientDao.getUpcomingVisits();
		return null;
	}

	@Override
	public List<task_master> getUpcomingTasks() {
		// TODO Auto-generated method stub
		patientDao.getUpcomingTasks();
		return null;
	}

	@Override
	public List<task_master> getOverdueVisits() {
		// TODO Auto-generated method stub
		patientDao.getOverdueVisits();
		return null;
	}

	@Override
	public List<task_master> getOverduetasks() {
		// TODO Auto-generated method stub
		patientDao.getOverduetasks();
		return null;
	}

	@Override
	public List<task_master> getCompletedVisits() {
		// TODO Auto-generated method stub
		patientDao.getCompletedVisits();
		return null;
	}

	@Override
	public List<task_master> getCompletedTasks() {
		// TODO Auto-generated method stub
		patientDao.getCompletedTasks();
		return null;
	}

	@Override
	public List<patient_total_obj> getConsultationHistory(String patient_uuid) {
		
		List<patient_total_obj> poj=patientDao.getConsultationHistory(patient_uuid);
		
		return poj;
	}

	@Override
	public psw_supervisor_obj getmetaDataLogin(int users_id,String role) {
		
		psw_supervisor_obj pso=patientDao.getmetaDataLogin(users_id,role);
		
		return pso;
	}
	
	@Override
	public supervisor_obj getmetaDataLogin1(int users_id,String role) {
		
		supervisor_obj pso=patientDao.getmetaDataLogin1(users_id,role);
		
		return pso;
	}
		
} 






