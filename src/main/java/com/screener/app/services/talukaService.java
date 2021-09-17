package com.screener.app.services;

import  com.screener.app.entity.*;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.screener.app.dao.*;
@Service
public class talukaService implements ItalukaService {
	@Autowired
	private ItalukaDao talukaDao;
	@Override
	public synchronized boolean addtaluka(taluka tk,int super_id){
		talukaDao.addtaluka(tk,super_id);
           return true;
	}
	
	@Override
	public List<taluka> getAlltalukas(){
		return talukaDao.getAlltalukas();
	}
	
	@Override
	public List<taluka> getTaluka(int taluka_master_id){
		return talukaDao.getTaluka(taluka_master_id);
	}
	
	@Override
	public List<taluka> updateTaluka(taluka tk,int taluka_master_id,int supervisor_id){
		return talukaDao.updateTaluka(tk,taluka_master_id,supervisor_id);
	}
	
	@Override
	public List<district_master> getAllDistricts(){
		return talukaDao.getAllDistricts();
	}
	@Override
	public List<phc_master> getAllPhcs(){
		return talukaDao.getAllPhcs();
	}
	
	@Override
	public List<taluka_supervisor_total> getAllTalukaSupervisors(){
		return talukaDao.getAllTalukaSupervisors();
	}
	
	@Override
	public List<taluka_psw> getAllTalukaPsws(){
		return talukaDao.getAllTalukaPsws();
	}
	@Override
	public List<taluka_psw> getAllTalukaPswsTalukaSupervisors(){
		return talukaDao.getAllTalukaPswsTalukaSupervisors();
	}
	//getAllTalukaPswsTalukaSupervisors
	
}
