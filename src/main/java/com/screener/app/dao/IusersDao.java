package com.screener.app.dao;
import java.util.List;

import javax.validation.Valid;

import  com.screener.app.entity.*;;
public interface IusersDao {
	List<users> getAlllogins(int role_type);
	//List<users> getUser(int users_id);
    //login getloginByName(String name);
    void addlogin(users lg);
    void  updatelogin(login lg,int hosp_id);
    //void updatePatient());
    //void deleteArticle(int articleId);
    String loginExists(String username, String password);
    int loginExists1(String username,String email);
   // String checklogin(login lg);
    String updateFlag(int flag_type, int flag, int hps_id);
    String updateflagByemail(String email);
    String forgotemail(String email);
    void updatepassword(String password,String email);
	int emailExists(String email);
	
	boolean addloginsuper(@Valid taluk_supervisor_obj taluk_supervisor_obj);
	boolean addloginpsw(@Valid psw_supervisor_obj psw_supervisor_obj,int taluka_id);
	
	//void getUser(users usr, int users_id);
	List<users> getUsers(int role);
	List<social_worker> getPsws();
	List<supervisor> getSupervisors();
	List<supervisor> getTalukaSupervisors();
	psw_supervisor_obj getUser(int users_id,String role);
	void  updateUser(users lg,int users_id,int role_type,String status,int taluka_id,int social_worker_id,int supervisor_id);
	//getGroupData
	List<group_data> getGroupData(int supervisor_id);
	
    
} 