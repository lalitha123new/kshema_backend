package com.screener.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.screener.app.entity.*;

@Transactional
@Repository
public class kshemaidDao implements IkshemaidDao {
	@PersistenceContext
	private EntityManager entityManager;

	public int loginExists1(String username, String email) {

		try {

			String hql = "select users_id FROM users as usr WHERE lg.user_name = ?1";
			int count = (int) entityManager.createQuery(hql).setParameter(1, username).getSingleResult();
			System.out.println("count------------------" + count);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getKshemaid(String state_name, String district_name, String taluka_name) {
		
		System.out.println("final kshema id- step to generate ");
		
		String kshema_id="hello";
		
		try {
			
//		String state_name="Karnataka";
//		String taluka_name ="Chitapur";
//		String district_name ="Kalburgi";
		
		if(state_name.isEmpty()) {
		//need to get last serial number from database;
		String hql = "SELECT MAX(kshema_id) FROM patientObj as p";
		@SuppressWarnings("unchecked")
		List<Integer> temp = entityManager.createQuery(hql).getResultList();
		temp.get(0);
		System.out.println("final kshema id"+temp.get(0));
		}
		
		
		String hql1 = "SELECT serial_number FROM kshemabase as p where state =?1 and district =?2 and taluk =?3 ";
		String serial_no1 = (String) entityManager.createQuery(hql1).setParameter(1, state_name).setParameter(2, district_name).setParameter(3, taluka_name).getSingleResult();
		System.out.println("SERAIL NUMBER"+serial_no1);
		//Integer serial_no= 1;
		String value = String.format("%05d",Integer.parseInt(serial_no1));
		//String value = serial_no.toString("D5");
		
		System.out.println(state_name.charAt(0));
		char st=state_name.charAt(0);
		char dt=district_name.charAt(0);
		char tl=taluka_name.charAt(0);
		
		String state_name_str=String.valueOf(st);
		String taluka_name_str=String.valueOf(dt);
		String district_name_str=String.valueOf(tl);
		
		kshema_id = state_name_str+taluka_name_str+district_name_str+value;
		/*ID Number: 

			This will be generated once per person at the first visit, the first letter will be the beginning letter of the State, 
			the second letter will be the beginning letter of the district,
			 the third letter will be the beginning letter of the Taluk,
			  followed by a 5-digit serial number. Some examples are, 
			
			List of taluks: 
			
			1)	Basavakalyana, Bidar: K B B 00001, KBB00002, etc 
			
			2)	Chitapur, Kalburgi: KKC00001, KKC00002, etc 
			
			3)	Sirsi, Uttar Kannada: KUS00001, KUS00002, etc */
		
		
		//increment serial number in kshema_base table
		
		String hql = "UPDATE kshemabase SET "+
				  "serial_number=:serial_number"+
				 
				  " WHERE state =?1 and district =?2 and taluk =?3 ";
		
		 Query query = entityManager.createQuery(hql);
		 System.out.println("CHECK"+ state_name+district_name+taluka_name);
		   query.setParameter("serial_number",Integer.toString(Integer.parseInt(serial_no1)+1));
		   query.setParameter(1, state_name);
		   query.setParameter(2, district_name);
		   query.setParameter(3, taluka_name);
		   
		   int result = query.executeUpdate();
		  
		 
		
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		return kshema_id;
	}

}