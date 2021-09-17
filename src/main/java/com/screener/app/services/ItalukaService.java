package com.screener.app.services;
import  com.screener.app.entity.*;
import java.util.List;

import javax.validation.Valid;

public interface ItalukaService {
	boolean addtaluka(taluka tk,int super_id);
	List<taluka> getAlltalukas();
	List<taluka> getTaluka(int taluka_master_id);
	List<taluka> updateTaluka(taluka tk,int taluka_master_id,int supervisor_id);
	List<district_master> getAllDistricts();
	List<phc_master> getAllPhcs();
	List<taluka_supervisor_total> getAllTalukaSupervisors();
	List<taluka_psw> getAllTalukaPsws();
	List<taluka_psw> getAllTalukaPswsTalukaSupervisors();
	//getAllTalukaPswsTalukaSupervisors
	
	
	

}

