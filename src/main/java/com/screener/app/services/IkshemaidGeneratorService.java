package com.screener.app.services;

import  com.screener.app.entity.*;
import java.util.List;

public interface IkshemaidGeneratorService {
	
     //String getKshemaid(int flag_type, int flag, int hps_id);

	String getKshemaid(String state_name, String taluka_name, String district_name);
     
} 