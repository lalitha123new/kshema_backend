package com.screener.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.screener.app.entity.*;
import com.screener.app.services.inotificationService;
@Transactional
@Repository
public class notificationDao implements inotificationDao {
	@PersistenceContext	
	private EntityManager entityManager;
	
	//done
	
	@SuppressWarnings("unchecked")
	@Override
	public int addNotification(notes notification) {
		// TODO Auto-generated method stub
		entityManager.persist(notification);
		return 1;
	}
	
	//
	@SuppressWarnings("unchecked")
	@Override
	public List<notes> getNotification(String patient_uuid) {
		// TODO Auto-generated method stub
		//updateReadFlag
//		 String hql1 = "UPDATE notes set read_flag = :read_flag "  + 
//	               "WHERE patient_uuid = :patient_uuid";
//	   Query query = entityManager.createQuery(hql1);
//	   query.setParameter("read_flag", 0);
//	   query.setParameter("patient_uuid", patient_uuid);
//	   int result = query.executeUpdate();
		
		String hql = "FROM notes as nt WHERE nt.patient_uuid = ?1";
		return (List<notes>) entityManager.createQuery(hql).setParameter(1, patient_uuid).getResultList();
	}

	
	  //to Get count of unread messages for nimhans Lab
		@SuppressWarnings("unchecked")
		@Override
		public int getUnreadNotificationCount(int portal_flag) {
			String hql = "select count(*) FROM notes as nt WHERE nt.read_flag = ?1 ";
			Long lg = (Long) entityManager.createQuery(hql).setParameter(1, portal_flag).getSingleResult();
			int count=lg.intValue();
			
			 return count;
		}
	
	
	
		@SuppressWarnings("unchecked")
		@Override
		public List<notes> getallnotificationfromsvr(int psw_id) {
			try {
				
			String hql = "FROM notes as nt WHERE nt.recipient_user_id = ?1";
			return (List<notes>) entityManager.createQuery(hql).setParameter(1, psw_id).getResultList();
			
			}catch(Exception e) {
				
				System.out.println("error is  at 71@notificationDAo "+e);
				
			}
			return null;
		}
	
	
	
	
  //list of message display on both sides external hospital and nimhans lab
	
	
	

	//to Get count of unread messages for externalHospital
		@SuppressWarnings("unchecked")
		@Override
		public int getUnreadNotificationCountExt(int portal_flag,int hospital_id) {
			String hql = "select count(*) FROM notification as nt WHERE nt.portal_flag = ?1 and nt.read_flag = ?2 and hospitalid = ?3";
			Long lg = (Long) entityManager.createQuery(hql).setParameter(1, portal_flag).setParameter(2,0).setParameter(3,hospital_id).getSingleResult();
			int count=lg.intValue();
			
			 return count;
		}
	
	
	
	
	//to GET all hopital notification details
	@SuppressWarnings("unchecked")
	@Override
	public List<pat_notification> getAllNotification(int portal_flag,int hospital_id) {
		       // TODO Auto-generated method stub
		 
		 String hql1 = "FROM notification as nt WHERE nt.portal_flag = ?1 and hospitalid = ?2 ";
		 List<notification> totalNot = (List<notification>) entityManager.createQuery(hql1).setParameter(1, portal_flag).setParameter(2, hospital_id).getResultList();
		 
		List<pat_notification> allNotification1 =new ArrayList<pat_notification>();
		 for(notification name : totalNot)
		 {
			
			 pat_notification allNotification =new pat_notification();
		     allNotification.setCreated_at(name.getCreated_at());
		     allNotification.setImg_url(name.getImg_url());
		     allNotification.setNotification_id(name.getNotification_id());
		     allNotification.setNotification_msg(name.getNotification_msg());
		     allNotification.setPortal_flag(portal_flag);
		     allNotification.setRead_flag(name.getRead_flag());
		     allNotification.setSample_id(name.getSample_id());
		     allNotification.setNp_num(name.getNp_num());
		     
		     System.out.println("sample_id "+name.getSample_id());
		     
		     String hql2 = "FROM patient WHERE sample_id = ?1 ";
			 patient patientnew = (patient) entityManager.createQuery(hql2).setParameter(1, name.getSample_id()).getSingleResult();

		  
			
		     allNotification.setHosp_ref_no(patientnew.getHosp_ref_no());
		     allNotification.setPat_name(patientnew.getPat_name());;
		     
		     allNotification1.add(allNotification);
		     
		     
		 }
		
		 
		 
		 return allNotification1;
		 
		 
//				String hql = "FROM notification as nt WHERE nt.portal_flag = ?1 and hospitalid = ?2 ";
//				return (List<notification>) entityManager.createQuery(hql).setParameter(1, portal_flag).setParameter(2, hospital_id).getResultList();
	}

	//to update read status both side
	@SuppressWarnings("unchecked")
	@Override
	public int updateReadNotification(int notification_id) {
		int count = ( entityManager.createQuery("UPDATE notification as nt SET nt.read_flag = 1 WHERE nt.notification_id = ?1")).setParameter(1,notification_id).executeUpdate();
		return count;
	}

	


	@SuppressWarnings("unchecked")
	@Override
	public List<notification> getAllHospitalNotification(int portal_flag) {
		
		String hql = "FROM notification as nt WHERE nt.portal_flag = ?1";
		return (List<notification>) entityManager.createQuery(hql).setParameter(1, portal_flag).getResultList();

	}

	//get all notification for supervisor
	@SuppressWarnings("unchecked")
	@Override
	public List<notes> getNotificationlist(int supervisor_id) {
     
		String hql = "FROM notes as nt WHERE nt.recipient_user_id = ?1";
		return entityManager.createQuery(hql).setParameter(1, supervisor_id).getResultList();

	}
	
	
	//get all unread notification for supervisor
	@SuppressWarnings("unchecked")
	@Override
	public List<notes> getUnreadnotificationlist(int supervisor_id) {
     
		String hql = "FROM notes as nt WHERE nt.read_flag=1 and nt.recipient_user_id = ?1 ";
		
		return entityManager.createQuery(hql).setParameter(1, supervisor_id).getResultList();

	}
	
	//get all unread notification for supervisor
		@SuppressWarnings("unchecked")
		@Override
		public int updateReadNotification1(String notes_uuid) {
	     System.out.println("notes_uuid");
	     int count = ( entityManager.createQuery("UPDATE notes as nt SET nt.read_flag = 0 WHERE nt.notes_uuid = ?1")).setParameter(1,notes_uuid).executeUpdate();
			return count;

		}
		
		@Override
		public int toUpdateImageUrl(String img_url,int checkImgSrc) {
			// TODO Auto-generated method stub
			System.out.println("CHECKING FILE UPLOAD"+checkImgSrc);
			if(checkImgSrc == 1) {
				System.out.println("notification image url  "+checkImgSrc);
			String hql1 = "Select max(p.notes_id) FROM notes p ";
			int notes_id = (int) entityManager.createQuery(hql1).getSingleResult();
			
			
				   String hql = "UPDATE notes set images = :img_url "  + 
				               "WHERE notes_id = :notes_id";
				   Query query = entityManager.createQuery(hql);
				   query.setParameter("img_url", img_url);
				   query.setParameter("notes_id", notes_id);
				   int result = query.executeUpdate();
				   
			}else if(checkImgSrc == 2) {
				//sample_image_url
				
				System.out.println("patient image url  "+checkImgSrc);
				String hql1 = "Select max(p.sample_id) FROM patient p ";
				int sample_id = (int) entityManager.createQuery(hql1).getSingleResult();
				
				
					   String hql = "UPDATE patient set sample_image_url = :sample_image_url "  + 
					               "WHERE sample_id = :sample_id";
					   Query query = entityManager.createQuery(hql);
					   query.setParameter("sample_image_url", img_url);
					   query.setParameter("sample_id", sample_id);
					   int result = query.executeUpdate();
				
				
			}else if(checkImgSrc == 3) {
				
				System.out.println("Au patient image url  "+checkImgSrc);
				String hql1 = "Select max(p.au_patient_id) FROM au_patient p ";
				
				int sample_id = (int) entityManager.createQuery(hql1).getSingleResult();
				
					   String hql = "UPDATE au_patient set au_sample_image_url = :au_sample_image_url "  + 
					                "WHERE au_patient_id = :au_patient_id";
					   Query query = entityManager.createQuery(hql);
					   query.setParameter("au_sample_image_url", img_url);
					   query.setParameter("au_patient_id", sample_id);
					   int result = query.executeUpdate();
			} 
		
			return 0;
		}

		//getImage
		@SuppressWarnings("unchecked")
		@Override
		public String getImage(String notes_uuid) {
			
			String hql = " Select images FROM notes as nt WHERE nt.notes_uuid = ?1";
			String result =  (String)entityManager.createQuery(hql).setParameter(1, notes_uuid).getSingleResult();
			return result;
		}
		
	
	
	
} 