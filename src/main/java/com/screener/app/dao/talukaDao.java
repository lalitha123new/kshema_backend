package com.screener.app.dao;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.ITemplateEngine;

import  com.screener.app.entity.*;
@Transactional
@Repository
public class talukaDao implements ItalukaDao {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public boolean addtaluka(taluka tk,int super_id) {
		group_data grop_dta =new group_data();
		entityManager.persist(tk);
		String hql1 = "select MAX(taluka_master_id) FROM  taluka_master";
		List count1 =   entityManager.createQuery(hql1).getResultList();
		int cnt1= (int) count1.get(0);
		
		System.out.println("GROUP CHECK"+cnt1);
		grop_dta.setTaluka_id(cnt1);
		grop_dta.setSupervisor_id(super_id);
		entityManager.persist(grop_dta);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<taluka> getAlltalukas() {
		String hql = "";
		
			hql = "FROM taluka_master as lg";
			
		return (List<taluka>) entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<taluka> getTaluka(int taluka_master_id) {
		String hql = "";
		
			hql = "FROM taluka_master as lg where taluka_master_id=?1";
			
		return (List<taluka>) entityManager.createQuery(hql).setParameter(1, taluka_master_id).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<taluka> updateTaluka(taluka tk,int taluka_master_id,int supervisor_id) {
		String hql = "UPDATE taluka_master SET "+
				  "taluka_name=:taluka_name,taluka_details=:taluka_details"+
				  " WHERE taluka_master_id= "+taluka_master_id;
		String hql1 = "UPDATE group_data SET "+
				  "supervisor_id="+supervisor_id+
				  " WHERE taluka_id= "+taluka_master_id;
		  
				Query query = entityManager.createQuery(hql);
				Query query1 = entityManager.createQuery(hql1);


				 query.setParameter("taluka_name", tk.getTaluka_name());
				 query.setParameter("taluka_details", tk.getTaluka_details());
 
				 	int result = query.executeUpdate();
				 	int result1 = query1.executeUpdate();
				 	return null;
	}
	
	//get all districts
	@SuppressWarnings("unchecked")
	@Override
	public List<district_master> getAllDistricts() {
		String hql = "";
		
			hql = "FROM district_master as lg";
			
		return (List<district_master>) entityManager.createQuery(hql).getResultList();
	}
	
	//get all phcs
	
	@SuppressWarnings("unchecked")
	@Override
	public List<phc_master> getAllPhcs() {
		String hql = "";
		
			hql = "FROM phc_master as lg";
			
		return (List<phc_master>) entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<taluka_supervisor_total> getAllTalukaSupervisors() {
		List<taluka_supervisor_total> returntotaltalukasupervisorObj=  new ArrayList<taluka_supervisor_total>();
		String hql1 = "FROM supervisor as lg1";
		List<supervisor> supervisor1 =(List<supervisor>) entityManager.createQuery(hql1).getResultList();
		
		String hql2 = "FROM taluka as lg2";
		List<taluka> taluka1 =(List<taluka>) entityManager.createQuery(hql2).getResultList();
		String hql3 = "FROM taluka_supervisor as lg3";
		List<taluka_supervisor> taluka_supervisor1 =(List<taluka_supervisor>) entityManager.createQuery(hql3).getResultList();
			
		//return (List<phc_master>) entityManager.createQuery(hql).getResultList();
		taluka_supervisor_total taluk_super=new taluka_supervisor_total(supervisor1,taluka1,taluka_supervisor1);
		returntotaltalukasupervisorObj.add(taluk_super);
		return returntotaltalukasupervisorObj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<taluka_psw> getAllTalukaPsws() {
		List<taluka_psw> returntotaltalukapswObj=  new ArrayList<taluka_psw>();
		String hql1 = "FROM social_worker as lg1";
		List<social_worker> social_worker1 =(List<social_worker>) entityManager.createQuery(hql1).getResultList();
		
		String hql2 = "FROM taluka as lg2";
		List<taluka_master> taluka1 =(List<taluka_master>) entityManager.createQuery(hql2).getResultList();
			
		//return (List<phc_master>) entityManager.createQuery(hql).getResultList();
		taluka_psw taluka_psw=new taluka_psw(social_worker1,taluka1);
		returntotaltalukapswObj.add(taluka_psw);
		return returntotaltalukapswObj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<taluka_psw> getAllTalukaPswsTalukaSupervisors() {
		List<taluka_psw> returntotaltalukapswObj=  new ArrayList<taluka_psw>();
		String hql1 = "FROM social_worker as lg1";
		List<social_worker> social_worker1 =(List<social_worker>) entityManager.createQuery(hql1).getResultList();
		
		String hql2 = "FROM taluka as lg2";
		List<taluka_master> taluka1 =(List<taluka_master>) entityManager.createQuery(hql2).getResultList();
		
		String hql3 = "FROM taluka_supervisor as lg3";
		List<taluka_supervisor> taluka_supervisor1 =(List<taluka_supervisor>) entityManager.createQuery(hql3).getResultList();
		String hql4 = "FROM group_data as lg4";
		List<group_data> group_data1 =(List<group_data>) entityManager.createQuery(hql4).getResultList();
		String hql5 = "FROM supervisor as lg5";
		List<supervisor> supervisor1 =(List<supervisor>) entityManager.createQuery(hql5).getResultList();
			
		//return (List<phc_master>) entityManager.createQuery(hql).getResultList();
		taluka_psw taluka_psw=new taluka_psw(social_worker1,taluka1,taluka_supervisor1,group_data1,supervisor1);
		returntotaltalukapswObj.add(taluka_psw);
		return returntotaltalukapswObj;
	}
	
	
}
