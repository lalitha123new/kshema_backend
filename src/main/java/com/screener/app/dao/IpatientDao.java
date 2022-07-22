package com.screener.app.dao;
import java.util.List;
import  com.screener.app.entity.*;;
public interface IpatientDao {
	List<patient> getAllpatients();
	Logintemp gethospitalbyusername(String username);
	Logintemp gethospitalbyhosid(int hospital_id);
	List<patient> getAllpatients(String username);
	public boolean addclinicalVisite(clinical_visits cv);
    //login getloginByName(String name);
	public boolean addpatient(patientObj pat);
    List<patient> getSort(String sort,String username);
    List<patient> getFilter(String filter,String username,String value);
	
    //void updatePatient());
    //void deleteArticle(int articleId);
    // boolean loginExists(String username, String password);
    // String checklogin(login lg);
    List<String> getAlldocs();
    List<patient> findByUserid(int sample_id);
    List<patient> findByUserExtId(String extr_id);
    
	int findMax();
	patient getpatientBytransId(int extr_id);
	public int getExtridByNpno(int npno);
	public int setSampleId(int extr_id,int sam_id);
	public int getNpnoByExtrid(int extr_id);
	List<login> getAllhospitallist();
	List<login> getHospitallist(int hospital_id);
	//public int toUpdateImageUrl(String str);
	public int toUpdateImageUrl(String str, int checkImgSrc);
	
	public String findNpnum(String npnum);
	
	public int updateSample(patient pat);
	List<lab_test_details> getLabtestDetails();
	lab_test_details getlabtestMatch(String exlab_specimen, String exlab_biopsy_type, String exlab_fixative);
	
	//KshemaProject Methods
	boolean addtasks(tasks tasks);
	List<patient_total_obj> getPatientList(int sw_id);
	List<patientObj> getPatientListActive(int sw_id);
	List<patientObj> getPatientDetails(String patient_uuid);
	List<pat_visit_task_notes_uuid> getPatientsData(int group_data_id);
	//getPatientsData
	boolean updatepatient(patientObj pat);
	boolean updatetasks(tasks tasks);
	String getkshemaidsync(String patient_uuid);
	List<task_master> getTaskMasterList();
	boolean addudid(udid_info udid_info);
	
	
	//@@@@@@@@@@supervisor methods@@@@@@@@@@@@@@@@@@@@@@@@@@
	List<patient_total_obj> getPatientsList(int api_type_id,int group_id);
	void getTodayVisits();
	List<patient_total_obj> getTodaytasks(int api_type_id);
	void getUpcomingTasks();
	void getUpcomingVisits();
	void getOverdueVisits();
	void getOverduetasks();
	void getCompletedVisits();
	void getCompletedTasks();
	List<task_master> getallTasks(String patient_uuid);
	
	
	List<patient_total_obj> getConsultationHistory(String patient_uuid);
	
	psw_supervisor_obj getmetaDataLogin(int users_id,String role);
	supervisor_obj getmetaDataLogin1(int users_id, String role);
	List<udid_info> getPatientUdid(String patient_uuid);
	List<counts> getCounts(String group_data_id,int supervisor_id);
	List<patientObj> getAllpatientsListActive();
	List<patient_total_obj> getAllPatientList();
	
	List<counts> getAllDataCounts();
	
} 