package com.screener.app.dao;

import java.util.List;

import com.screener.app.entity.district_master;
import com.screener.app.entity.phc_master;
import com.screener.app.entity.taluka;
import com.screener.app.entity.taluka_psw;
import com.screener.app.entity.taluka_supervisor_total;
import com.screener.app.entity.users;

public interface ItalukaDao {

	boolean addtaluka(taluka tk,int super_id);
	List<taluka> getAlltalukas();
	List<taluka> getTaluka(int taluka_master_id);
	List<taluka> updateTaluka(taluka tk,int taluka_master_id,int supervisor_id);
	List<district_master> getAllDistricts();
	List<phc_master> getAllPhcs();
	List<taluka_supervisor_total> getAllTalukaSupervisors();
	List<taluka_psw> getAllTalukaPsws();
	List<taluka_psw> getAllTalukaPswsTalukaSupervisors();
	
	
	
	

}
