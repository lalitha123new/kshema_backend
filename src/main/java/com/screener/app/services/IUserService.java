package com.screener.app.services;

import  com.screener.app.entity.*;
import java.util.List;

import javax.validation.Valid;

public interface IUserService {
     List<users> getAlllogins(int role_type);
    
     
    // Patient getArticleById(int articleId);
     boolean addlogin(users lg);
     void  updatelogin(login lg,int hosp_id);
     String checklogin(users usr);
     //void updateArticle(Patient pat);
     //void deleteArticle(int id);
     String updateFlag(int flag_type, int flag, int hps_id);
     String updateflagByemail(String email);
     String forgotemail(String email);
     void updatepassword(String password,String email);
     
     
	boolean addloginsuper(@Valid taluk_supervisor_obj taluk_supervisor_obj);
	
	boolean addloginpsw(@Valid psw_supervisor_obj psw_supervisor_obj,int taluka_id);
	
	//new code
	 //List<users> getUser(int users_id);
	 List<users> getUsers(int role);
	 List<social_worker> getPsws();
	 List<supervisor> getSupervisors();
	 List<supervisor> getTalukaSupervisors();
	 psw_supervisor_obj getUser(int users_id,String role);
	 void  updateUser(@Valid users lg,int users_id,int role_type,String status,int taluka_id,int social_worker_id,int supervisor_id);
	
	 List<group_data> getGroupData(int supervisor_id);
	
	 //end of new code


	
}


	