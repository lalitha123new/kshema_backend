package com.screener.app.services;

import  com.screener.app.entity.*;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.screener.app.dao.*;
@Service
public class kshemaidGeneratorService implements IkshemaidGeneratorService {
	@Autowired
	private IkshemaidDao kshemaidDao;
	
	@Override
	public String getKshemaid(String state_name, String taluka_name, String district_name) {
		
		return kshemaidDao.getKshemaid( state_name,  taluka_name,  district_name);
		
	}
} 
