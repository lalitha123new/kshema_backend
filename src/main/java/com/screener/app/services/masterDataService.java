package com.screener.app.services;

import  com.screener.app.entity.*;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.screener.app.dao.*;
@Service
public class masterDataService implements ImasterDataService {
	@Autowired
	private ItaskMasterDao taskMasterDao;
	
	

	@Override
	public List<task_master> getTasklist() {
		// TODO Auto-generated method stub
		
		return taskMasterDao.getTasklist();
	}
} 
