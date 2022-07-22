package com.screener.app.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import  com.screener.app.entity.*;
@Transactional
@Repository
public class patientDao implements IpatientDao {
	private static final List<clinical_visits> clinical_visits1 = null;

	@PersistenceContext	
	private EntityManager entityManager;	
	
	private kshemaidDao kshemaidDao;
	/*@Override
	public login getArticleById(int ) {
		return entityManager.find(Article.class, articleId);
	}*/
	@SuppressWarnings("unchecked")
	@Override
	public List<patient> getAllpatients() {
		String hql = "FROM patient as pat";
		return (List<patient>) entityManager.createQuery(hql).getResultList();
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAlldocs() {
		//String temp="apollo";
		String hql = "select d.docname FROM doctor d where d.hname='apollo'";
		return (List<String>) entityManager.createQuery(hql).getResultList();
	}
	
	
	//kashema patient table
	@Override
	public boolean addpatient(patientObj pat) {
		try {
			
			
			String hql = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid = ?1";
			Long count = (Long) entityManager.createQuery(hql).setParameter(1, pat.getPatient_uuid()).getSingleResult();
			
			if(count == 0) {
			
			//String kshema_id = kshemaidDao.getKshemaid(0, 0, 0);
			//System.out.println("hell its kshema_id---"+kshema_id);
			//pat.setStatus("Pending");
			//new code
			pat.setStatus("active");
			//end
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			//kshema_id
		
		//pat.setKshema_id(kshema_id);
		
		//System.out.println(kshema_id);
		pat.setCreatedAt(timestamp.toString());
		//System.out.println(kshema_id);
		entityManager.persist(pat);
		return true;
			}else {
				System.out.println("Patient Data Exists");
				return true;
			}
		}catch(Exception e) {
			System.out.println("patient data not saved  "+e);
			return false;
		}
	}
	
	  //kashema clinical_visits table
	
		//@Override
		public void addClinicalVisits(clinical_visits clinicalvisits) {
			try {
				
				String hql = "select count(clinical_visits_id) FROM clinical_visits as c WHERE c.clinical_visits_uuid = ?1";
				Long count = (Long) entityManager.createQuery(hql).setParameter(1, clinicalvisits.getClinical_visits_uuid()).getSingleResult();
				
				if(count == 0) {
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				
				clinicalvisits.setCreatedAt(timestamp.toString());
				entityManager.persist(clinicalvisits);
			}else {
				System.out.println("Clinical Data Exists");
			
			}
			}catch(Exception e) {
				System.out.println(" clinical visit data not saved");
			}
		}
	
		
				
				
				//kashema tasks table
				//@Override
				public void addTasks(tasks tasks) {
					try {
						String hql = "select count(tasks_id) FROM tasks as t WHERE t.tasks_uuid = ?1";
						Long count = (Long) entityManager.createQuery(hql).setParameter(1, tasks.getTasks_uuid()).getSingleResult();
						
						if(count == 0) {
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					
					tasks.setCreatedAt(timestamp.toString());
					entityManager.persist(tasks);
						}else {
							System.out.println("Tasks Data Exists");
						}
				}catch(Exception e) {
					System.out.println("task data not saved ");
				}
				}
				
				//kashema udid_info table
				//@Override
				public void addudid_info(udid_info udid_info) {
					try {
						String hql = "select count(udid_info_id) FROM udid_info as U WHERE U.udid_uuid = ?1";
						Long count = (Long) entityManager.createQuery(hql).setParameter(1, udid_info.getUdid_uuid()).getSingleResult();
						
						if(count == 0) {
					
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					
					udid_info.setCreatedAt(timestamp.toString());
					entityManager.persist(udid_info);
						}else {
							System.out.println("UDID Data Exists");
						}
						}catch(Exception e) {
							System.out.println("task data not saved ");
						}
				}

				//kashema udid_info table
				//@Override
				public void addsupervisor(supervisor supervisor) {
					
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					
					supervisor.setCreatedAt(timestamp.toString());
					entityManager.persist(supervisor);
				}
				
				
				
				
	
	@SuppressWarnings("unchecked")
	@Override
	public List<patient> findByUserid(int sample_id) {
		String hql = "FROM patient p where p.sample_id=?1";
		return (List<patient>) entityManager.createQuery(hql).setParameter(1, sample_id).getResultList();
		//return (List<String>) entityManager.createQuery(hql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<patient> findByUserExtId(String extr_id) {
		String hql = "FROM patient p where p.extr_id=?1";
		return (List<patient>) entityManager.createQuery(hql).setParameter(1, extr_id).getResultList();
		//return (List<String>) entityManager.createQuery(hql).getResultList();
	}
	
	
	@Override
	public int findMax() {
		String hql = "SELECT MAX(p.sample_id) FROM patient as p";
		@SuppressWarnings("unchecked")
		List<Integer> temp = entityManager.createQuery(hql).getResultList();
		return temp.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<patient> getAllpatients(String username) {
		
		
		//getting hospital_id
		int hospital_id=0;
		String hql = "select hospitalid FROM login as lg WHERE lg.username = ?1";
		List results = entityManager.createQuery(hql).setParameter(1, username).getResultList();
		if(results.isEmpty()) {
			
			String hql1 = "select hospitalid FROM login as lg WHERE lg.email = ?1";
			int count1 =  (int) entityManager.createQuery(hql1).setParameter(1, username).getSingleResult();
			hospital_id = count1;
			
		}else {
			
			hospital_id =  Integer.parseInt(results.get(0).toString());
		}
		
		
		List<patient> pat= null;
		String hql2 = "FROM patient as pat where pat.hospitalid= "+hospital_id;
		pat = (List<patient>) entityManager.createQuery(hql2).getResultList();
//		if(pat.isEmpty()) {
//			
//			String hql3 = "FROM patient as pat where pat.email=?1";
//			List<patient> pat1 = (List<patient>) entityManager.createQuery(hql3).setParameter(1,username).getResultList();
//			return pat1;
//			
//		}else {
			return pat;
		//}
		
	}
	
	@Override
	public Logintemp gethospitalbyusername(String username) {
		String hql = "FROM login as lg where lg.username=?1";
		login temp= (login) entityManager.createQuery(hql).setParameter(1,username).getSingleResult();
		
		Logintemp lt=new Logintemp();
		lt.setHospitalname(temp.getNameofhospital());
		lt.setMail(temp.getEmail());
		lt.setPhone(temp.getContact());
		lt.setHospital_address(temp.getHospital_address());
		return lt;

	}
	
	@Override
	public Logintemp gethospitalbyhosid(int hospital_id) {
		String hql = "FROM login as lg where lg.hospitalid=?1";
		login temp= (login) entityManager.createQuery(hql).setParameter(1,hospital_id).getSingleResult();
		
		Logintemp lt=new Logintemp();
		lt.setHospitalname(temp.getNameofhospital());
		lt.setMail(temp.getEmail());
		lt.setPhone(temp.getContact());
		lt.setHospital_address(temp.getHospital_address());
		lt.setHospital_type(temp.getHospitalType());
		return lt;

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<patient> getSort(String sort,String username	)
	{
		String hql = null;
		if(sort.equals("status"))
			hql = "FROM patient p where p.username=?1 order by p.status";
		else if(sort.equals("username"))
			hql = "FROM patient p where p.username=?1 order by p.username";
		else if(sort.equals("specimen"))
			hql = "FROM patient p where p.username=?1 order by p.specimen";
		else if(sort.equals("referred_by"))
			hql = "FROM patient p where p.username=?1 order by p.referred_by";
		else if(sort.equals("npno"))
			hql = "FROM patient p where p.username=?1 order by p.npno";
		else if(sort.equals("pat_name"))
			hql = "FROM patient p where p.username=?1 order by p.pat_name";
		return (List<patient>) entityManager.createQuery(hql).setParameter(1, username).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<patient> getFilter(String filter,String username,String value)
	{
		String hql = null;
		if(filter.equals("status")) 
			hql = "FROM patient p where p.status=?1 and p.username=?2";
		else if(filter.equals("doctor"))
			hql = "FROM patient p where p.referred_by=?1 and p.username=?2";
		else if(filter.equals("specimen"))
			hql = "FROM patient p where p.specimen=?1 and p.username=?2";
		else if(filter.equals("gender"))
			hql = "FROM patient p where p.pat_gender=?1 and p.username=?2";
		else if(filter.equals("Fixative"))
			hql = "FROM patient p where p.fixative=?1 and p.username=?2";
		return (List<patient>) entityManager.createQuery(hql).setParameter(1,value).setParameter(2,username).getResultList();
	}
	
	@Override
	public int getExtridByNpno(int npno)
	{
		String hql = "Select p.extr_id FROM patient p where p.npno=?1";
		return (int) entityManager.createQuery(hql).setParameter(1, npno).getSingleResult();
	}
	
	@Override
	public int getNpnoByExtrid(int extr_id)
	{
		String hql = "Select p.npno FROM patient p where p.extr_id=?1";
		return (int) entityManager.createQuery(hql).setParameter(1, extr_id).getSingleResult();
	}
	
	@Override
	public int setSampleId(int extr_id,int sam_id)
	{
		int count = ( entityManager.createQuery("update Id_Mapping id set id.sam_id=?1 where id.extr_id=?2")).setParameter(1,sam_id).setParameter(2,extr_id).executeUpdate();
		return count;

	}
	
	@Override
	public patient getpatientBytransId(int extr_id)
	{
		String hql = "FROM patient p where p.extr_id=?1";
		return (patient) entityManager.createQuery(hql).setParameter(1, extr_id).getResultList();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<login> getAllhospitallist() {
		// TODO Auto-generated method stub
		
		String hql = "FROM  login p";
	
		return (List<login>) entityManager.createQuery(hql).getResultList();
		
	}
	
	@Override
	public int toUpdateImageUrl(String img_url,int checkImgSrc) {
		// TODO Auto-generated method stub
		System.out.println("CHECKING FILE UPLOAD"+checkImgSrc);
		if(checkImgSrc == 1) {
			System.out.println("notification image url  "+checkImgSrc);
		String hql1 = "Select max(p.notification_id) FROM notification p ";
		int notification_id = (int) entityManager.createQuery(hql1).getSingleResult();
		
		
			   String hql = "UPDATE notification set img_url = :img_url "  + 
			               "WHERE notification_id = :notification_id";
			   Query query = entityManager.createQuery(hql);
			   query.setParameter("img_url", img_url);
			   query.setParameter("notification_id", notification_id);
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
	
	
	//get Update hospital list
	@Override
	public List<login> getHospitallist(int hospital_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String findNpnum(String npnum) {
		
		try {
				
			String external_hos_id = "";
		  // String hql2 = "FROM patient p where p.npno= :npno";
			String hql2 = "FROM patient p WHERE p.npno= '"+npnum+"'";
			
			System.out.println(hql2);
		   Query query = entityManager.createQuery(hql2);
		  // query.setParameter("npno", npnum);
		   List<patient> result = (List<patient>) query.getResultList();
		   
		   System.out.println(result.toString());
	
			for(patient pa:result) {
				external_hos_id=""+pa.getExtr_id();
			}
			
			
			return external_hos_id;
			
		} catch(NoResultException nre) {
			
			System.out.println("exp   "+nre);
		
		}
		
		
		
		return "0";
	}
	
	
	
	//update sample
		public int updateSample(patient pat) {
			
			
		try {
			
			System.out.println("check1");
			System.out.println(pat.getSample_test());
			
		 String hql = "UPDATE patient SET pat_name=:pat_name,pat_age=:pat_age,"+
				" pat_gender=:pat_gender,pat_phno=:pat_phno,pat_email=:pat_email,referred_by=:referred_by,clinic_history=:clinic_history,"+
				" examination=:examination,"+
				" amount=:amount,doctor_email_id=:doctor_email_id,"+
				" doctor_phone_no=:doctor_phone_no,investigation=:investigation,"+
				" previous_biopsy_number=:previous_biopsy_number,sample_test=:sample_test,"+
				" dd_no=:dd_no,diagnosis=:diagnosis,"+
				 "opetative_notes=:opetative_notes,hospitalid=:hospitalid,"+
				 "hosp_ref_no=:hosp_ref_no WHERE sample_id=:sample_id";
	     Query query = entityManager.createQuery(hql);
	     query.setParameter("pat_name", pat.getPat_name());
	     query.setParameter("pat_age", pat.getPat_age());
		 query.setParameter("pat_gender", pat.getPat_gender());
		 
		 query.setParameter("pat_phno", pat.getPat_phno());
		 query.setParameter("pat_email", pat.getPat_email());
		 
	     query.setParameter("referred_by", pat.getReferred_by());
		 query.setParameter("clinic_history", pat.getClinic_history());
	     query.setParameter("examination", pat.getExamination());
		 query.setParameter("amount", pat.getAmount());
	     query.setParameter("doctor_email_id", pat.getDoctor_email_id());
		 query.setParameter("doctor_phone_no", pat.getDoctor_phone_no());
	     query.setParameter("investigation", pat.getInvestigation());
		 query.setParameter("previous_biopsy_number", pat.getPrevious_biopsy_number());
	     query.setParameter("sample_test", pat.getSample_test());
		 query.setParameter("dd_no", pat.getDd_no());
	     query.setParameter("diagnosis", pat.getDiagnosis());
		 query.setParameter("opetative_notes", pat.getOpetative_notes());
	     query.setParameter("hospitalid", pat.getHospitalid());
		 query.setParameter("hosp_ref_no", pat.getHosp_ref_no());
	     query.setParameter("sample_id", pat.getSample_id());
	     int result = query.executeUpdate();
	     return result;
	   } catch(NoResultException nre) {
				
				System.out.println("exp   "+nre);
			
			
		}
		return 0;
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<lab_test_details> getLabtestDetails() {

		String hql = "FROM lab_test_details";
		return (List<lab_test_details>) entityManager.createQuery(hql).getResultList();
		}
	
		@Override
		public lab_test_details getlabtestMatch(String exlab_specimen, String exlab_biopsy_type,
		String exlab_fixative) {
		// TODO Auto-generated method stub
		try {

		System.out.println(exlab_specimen+"--1--"+exlab_biopsy_type+"--2--"+exlab_fixative+"--3--");

		String hql2 = "FROM lab_test_details l WHERE l.exlab_specimen=?1 and l.exlab_biopsy_type=?2 and l.exlab_fixative=?3";

		@SuppressWarnings("unchecked")
		List<lab_test_details> result  = (List<lab_test_details>)entityManager.createQuery(hql2) .setParameter(1, exlab_specimen).setParameter(2, exlab_biopsy_type).setParameter(3, exlab_fixative).getResultList();
		 
		 
		  lab_test_details lab_test_details1=null;
		for(lab_test_details pa:result) {
		lab_test_details1=pa;
		System.out.println(pa);
		}


		return lab_test_details1;

		} catch(NoResultException nre) {

		System.out.println("exp   "+nre);

		}
		return null;


		}
		
		//kshema clinical visite
		@Override
		public boolean addclinicalVisite(clinical_visits cv) {
			// TODO Auto-generated method stub
			try {
			System.out.println("cv data "+cv.getClinical_visits_uuid());
			String hql = "select count(clinical_visits_id) FROM clinical_visits as c WHERE c.clinical_visits_uuid = ?1";
			Long count = (Long) entityManager.createQuery(hql).setParameter(1, cv.getClinical_visits_uuid()).getSingleResult();
			
			if(count == 0) {
			entityManager.persist(cv);
			return true;
			}else {
				System.out.println("Clinical Data Exists");
				return true;
			}
			}catch(Exception e) {
				System.out.println("visit data not saved  "+e);
				return false;
			}
		}
		@Override
		public boolean addtasks(tasks tasks) {
			// TODO Auto-generated method stub
			try {
			String hql = "select count(tasks_id) FROM tasks as t WHERE t.tasks_uuid = ?1";
			Long count = (Long) entityManager.createQuery(hql).setParameter(1, tasks.getTasks_uuid()).getSingleResult();
			
			if(count == 0) {
			entityManager.persist(tasks);
			return true;
			}else {
				System.out.println("Tasks Data Exists");
				return true;	
			}
			}catch(Exception e) {
				System.out.println("tasks data not saved  "+e);
				return false;
			}
		}
		
		
		//new code - get all patients active and inactive
		@SuppressWarnings("unchecked")
		@Override
		public List<patient_total_obj> getPatientList(int sw_id) {
			
			//String hql = "FROM patientObj p where p.group_data_id=?1";
			List<patient_total_obj> returntotalObj=  new ArrayList<patient_total_obj>() ;
			//new code
			//String hql3 = "FROM patientObj p where p.group_data_id=?1";
			String hql3 = "FROM patientObj p";
			//end
			//List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).setParameter(1,sw_id).getResultList();
			List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).getResultList();
			
			for( patientObj patientObj:patientObj1) {
				
				//String hql = "FROM clinical_visits WHERE followup_date=:followup_date and patient_uuid=:patient_uuid order by clinical_visits_id DESC";
				
					//new code
				String hql = "FROM clinical_visits  WHERE patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				//end
			
				List<clinical_visits> clinical_visits =(List<clinical_visits>) entityManager.createQuery(hql).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				for( clinical_visits cv:clinical_visits) {
					String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
					List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
					returntotalObj.add(pat_tol);
				}
				
				}
			return returntotalObj;
			
			
		}
		
		//get active patients onlyt
		@SuppressWarnings("unchecked")
		@Override
		public List<patientObj> getPatientListActive(int sw_id) {
			
			//String hql = "FROM patientObj p where p.group_data_id=?1";
			//new code
			String hql = "FROM patientObj p where p.group_data_id=?1 and status = 'active'";
			//end
			return (List<patientObj>) entityManager.createQuery(hql).setParameter(1, sw_id).getResultList();
			

		}
		//end
		
		@SuppressWarnings("unchecked")
		@Override
		public List<patientObj> getPatientDetails(String patient_uuid) {
			String hql = "FROM patientObj p where p.patient_uuid=?1";
			return (List<patientObj>) entityManager.createQuery(hql).setParameter(1, patient_uuid).getResultList();
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<pat_visit_task_notes_uuid> getPatientsData(int group_data_id) {
			List<pat_visit_task_notes_uuid> returntopat_visit_task_notes_uuidobj=  new ArrayList<pat_visit_task_notes_uuid>();
			//String hql1 = "FROM patientObj p where p.group_data_id=:group_data_id";
			String hql1 = "FROM patientObj p where p.group_data_id=:group_data_id";
			List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql1).setParameter("group_data_id",group_data_id).getResultList();
			//List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql1).getResultList();
			System.out.println(patientObj1.get(0).getConsent_arr());
			List<clinical_visits> clinical_visitsobj=  new ArrayList<clinical_visits>();
			List<tasks> tasksobj=  new ArrayList<tasks>();
			List<notes> notesobj=  new ArrayList<notes>();
			List<udid_info> udid_infoobj=  new ArrayList<udid_info>();
			for( patientObj patientObj:patientObj1) {
				
			 
				
				
				String hql2 = "FROM clinical_visits WHERE patient_uuid=:patient_uuid  GROUP BY patient_uuid ";
				
				List<clinical_visits> clinical_visits1 =(List<clinical_visits>) entityManager.createQuery(hql2).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				List<clinical_visits> temp=  new ArrayList<clinical_visits>();
				for( clinical_visits cv:clinical_visits1) {
					String hql2a = "FROM clinical_visits WHERE patient_uuid=:patient_uuid";
					 temp = entityManager.createQuery(hql2a).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					
				}
				clinical_visitsobj.addAll(temp);
				//clinical_visitsobj.addAll(clinical_visits1);
				
				String hql3 = "FROM tasks WHERE patient_uuid=:patient_uuid  GROUP BY patient_uuid ";
				
				List<tasks> tasks1 =(List<tasks>) entityManager.createQuery(hql3).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				List<tasks> temp1=  new ArrayList<tasks>();
				for( tasks cv:tasks1) {
					String hql2b = "FROM tasks WHERE patient_uuid=:patient_uuid";
					 temp1 = entityManager.createQuery(hql2b).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					
				}
				tasksobj.addAll(temp1);
				
				String hql4 = "FROM notes WHERE patient_uuid=:patient_uuid  GROUP BY patient_uuid ";
				
				List<notes> notes1 =(List<notes>) entityManager.createQuery(hql4).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				List<notes> temp2=  new ArrayList<notes>();
				for( notes cv:notes1) {
					String hql2c = "FROM notes WHERE patient_uuid=:patient_uuid";
					 temp2 = entityManager.createQuery(hql2c).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					
				}
				//tasksobj.addAll(temp1);
				notesobj.addAll(temp2);
				
				String hql5 = "FROM udid_info WHERE patient_uuid=:patient_uuid  GROUP BY patient_uuid ";
				
				List<udid_info> udid_info1 =(List<udid_info>) entityManager.createQuery(hql5).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				List<udid_info> temp3=  new ArrayList<udid_info>();
				for( udid_info cv:udid_info1) {
					String hql2d = "FROM udid_info WHERE patient_uuid=:patient_uuid";
					 temp3 = entityManager.createQuery(hql2d).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					
				}
				//udid_infoobj.addAll(udid_info1);
				udid_infoobj.addAll(temp3);
				
				}
			pat_visit_task_notes_uuid pat_visit_task_notes_uuid = new pat_visit_task_notes_uuid(patientObj1,clinical_visitsobj,tasksobj,notesobj,udid_infoobj);
			returntopat_visit_task_notes_uuidobj.add(pat_visit_task_notes_uuid);
			return returntopat_visit_task_notes_uuidobj;
		}
		
		
		
		@Override
		public boolean updatepatient(patientObj pat) {
			// TODO Auto-generated method stub
			
		
		String hql="UPDATE patientObj SET name=:name,demographic_info=:demographic_info,needs_assessment=:needs_assessment,status=:status WHERE patient_uuid=:patient_uuid";
			 Query query = entityManager.createQuery(hql);
			 query.setParameter("name", pat.getName());
			 query.setParameter("demographic_info", pat.getDemographic_info());
			 query.setParameter("needs_assessment", pat.getNeeds_assessment());
			 query.setParameter("status", pat.getStatus());
			 query.setParameter("patient_uuid", pat.getPatient_uuid());
			 int result = query.executeUpdate();
			     
			if(result==1)
				return true;
			
			if(result==0)
				return false;
			
			return false;
				
		}
		
		@Override
		public boolean updatetasks(tasks tasks) {
			
			 String hql="UPDATE tasks SET task_due_date=:task_due_date,patient_uuid=:patient_uuid,update_date=:update_date,status=:status,prev_record_uuuid=:prev_record_uuuid,task_details=:task_details WHERE tasks_uuid=:tasks_uuid";
			 Query query = entityManager.createQuery(hql);
			 query.setParameter("task_due_date", tasks.getTask_due_date());
			 query.setParameter("patient_uuid", tasks.getPatient_uuid());
			 query.setParameter("tasks_uuid", tasks.getTasks_uuid());
			 
			 query.setParameter("update_date", tasks.getUpdate_date());
			 System.out.println("status---- "+tasks.getStatus());
			 query.setParameter("status", tasks.getStatus());
			 query.setParameter("prev_record_uuuid", tasks.getPrev_record_uuuid());
			 query.setParameter("task_details", tasks.getTask_details());
			 
			 //prev_record_uuuid
			 //query.setParameter("patient_uuid", tasks.getTasks_uuid());
			 int result = query.executeUpdate();
			 Boolean response = false;
			if(result==1)
				response =  true;
			else if(result==0)
				response =  true;
			
			return response;	
		}
		
		
		@Override
		public String getkshemaidsync(String patient_uuid) {
			
			String hql = "SELECT  kshema_id FROM patientObj as p WHERE patient_uuid=:patient_uuid";
			
			@SuppressWarnings("unchecked")
			List<Integer> temp = entityManager.createQuery(hql).setParameter("patient_uuid",patient_uuid).getResultList();
			//.setParameter("patient_uuid",patient_uuid)
			temp.get(0);
			System.out.println(temp.get(0));
			return temp.get(0)+"";
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<task_master> getTaskMasterList() {
			
			String hql = "FROM task_master p";
			return (List<task_master>) entityManager.createQuery(hql).getResultList();
		}
		
		@Override
		public boolean addudid(udid_info udid_info) {
			try {
				String hql = "select count(udid_info_id) FROM udid_info as U WHERE U.udid_uuid = ?1";
				Long count = (Long) entityManager.createQuery(hql).setParameter(1, udid_info.getUdid_uuid()).getSingleResult();
				
				if(count == 0) {
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			udid_info.setCreatedAt(timestamp.toString());
			entityManager.persist(udid_info);
			return true;
				}else {
					System.out.println("UDID Data Exists");
					return true;
				}
				}catch(Exception e) {
					System.out.println("udid data not saved ");
					return false;
				}
		}
		
	//@@@@@@@@@@@@@@@@@@@@ Supervisor methods @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//	
		
		@Override
		@SuppressWarnings({ "unchecked", "null" })
		public List<patient_total_obj> getPatientsList(int api_type_id,int group_id) {
			
			System.out.println("api_type_id------"+api_type_id);
			List<patient_total_obj> returntotalObj=  new ArrayList<patient_total_obj>() ;
			
			if(api_type_id == 1) {
					
			//today visit -1
//			Date date = new Date();
//			System.out.println("DATE IS"+date);
//			String dateFormatString = "dd-MM-yyyy"; //23-03-2021
//			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//			String currentDate = dateFormat.format(date);
//			Date date = new Date();
//			String dateFormatString = "yyyy-MM-dd hh:mm:ss"; //23-03-2021
//			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//			String currentDate = dateFormat.format(date);
			//String currentDate = dateFormat.format(date);
				//Date currentDate = new Date();
			
				 Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
			System.out.println("DATE TIME IS"+currentDate);
			

		    
			//String hql3 = "FROM patientObj WHERE group_data_id=:group_id";
		    String hql3 = "FROM patientObj";
			
			//List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).setParameter("group_id",group_id).getResultList();
			List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).getResultList();
			for( patientObj patientObj:patientObj1) {
				
			//String hql = "FROM clinical_visits WHERE followup_date=:followup_date and patient_uuid=:patient_uuid order by clinical_visits_id DESC";
			
				//new code
			String hql = "FROM clinical_visits WHERE followup_date=:followup_date and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
			//end
			System.out.println("query    "+hql);
			List<clinical_visits> clinical_visits =(List<clinical_visits>) entityManager.createQuery(hql).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
			for( clinical_visits cv:clinical_visits) {
				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
				returntotalObj.add(pat_tol);
			}
			
			}
			}else if(api_type_id == 2) {
				//dashboard view - upcoming visits-2
				//SELECT * FROM clinical_visits WHERE followup_date > ?
//				Date date = new Date();
//				String dateFormatString = "dd-MM-yyyy"; //23-03-2021
//				DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//				String currentDate = dateFormat.format(date);
//			    System.out.println("Current date:--- "+currentDate);
//				Date date = new Date();
//				String dateFormatString = "yyyy-MM-dd hh:mm:ss"; //23-03-2021
//				DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//				String currentDate = dateFormat.format(date);
				//Date currentDate = new Date();
				Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
			System.out.println("DATE TIME IS"+currentDate);
		
			   // String hql3 = "FROM patientObj WHERE group_data_id=:group_id";
			    String hql3 = "FROM patientObj";
				
				//List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).setParameter("group_id",group_id).getResultList();
				List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).getResultList();
				for( patientObj patientObj:patientObj1) {
			    
				//String hql = "FROM clinical_visits WHERE followup_date>:followup_date and patient_uuid=:patient_uuid order by clinical_visits_id DESC";
				//new code
				String hql = "FROM clinical_visits WHERE followup_date>:followup_date and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				
				//end
				System.out.println("query    "+hql);
				List<clinical_visits> clinical_visits =(List<clinical_visits>) entityManager.createQuery(hql).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				for( clinical_visits cv:clinical_visits) {
					String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
					List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
					returntotalObj.add(pat_tol);
				}
				
				}
				
			}else if(api_type_id == 3) {
				//dashboard view - overdue visits-3
//				Date date = new Date();
//				String dateFormatString = "dd-MM-yyyy"; //23-03-2021
//				DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//				String currentDate = dateFormat.format(date);
//				Date date = new Date();
//				String dateFormatString = "yyyy-MM-dd hh:mm:ss:sss"; //23-03-2021
//				DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//				String currentDate = dateFormat.format(date);
				//Date currentDate = new Date();
				Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				 System.out.println("DATE TIME IS"+currentDate);
				
                  //String hql3 = "FROM patientObj WHERE group_data_id=:group_id";
                  String hql3 = "FROM patientObj";
				
				//List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).setParameter("group_id",group_id).getResultList();
				List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).getResultList();
				for( patientObj patientObj:patientObj1) {
			    
				//String hql = "FROM clinical_visits WHERE followup_date<:followup_date  and patient_uuid=:patient_uuid order by clinical_visits_id DESC";
				//new code
				String hql = "FROM clinical_visits WHERE followup_date<:followup_date  and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				//end
				System.out.println("query    "+hql);
				List<clinical_visits> clinical_visits =(List<clinical_visits>) entityManager.createQuery(hql).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
				for( clinical_visits cv:clinical_visits) {
					String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
					List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
					patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
					returntotalObj.add(pat_tol);
				}
				}
			}
			
			return returntotalObj;
			
			
		}
		@Override
		public void getTodayVisits() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public List<patient_total_obj> getTodaytasks(int api_type_id) {
			
           List<patient_total_obj> returntotalObj=  new ArrayList<patient_total_obj>() ;
           if(api_type_id==1) {	   
          //today task records
           
//			Date date = new Date();
//			String dateFormatString = "dd-MM-yyyy"; //24-03-2021
//			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//			String currentDate = dateFormat.format(date);
//		    System.out.println("Current date:--- "+currentDate);
        	  // Date currentDate = new Date();
        	   Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				 System.out.println("DATE TIME IS"+currentDate);
			
			
			String hql = "FROM tasks WHERE task_due_date =:task_due_date order by tasks_id DESC";
			System.out.println("date  "+hql);
			List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql).setParameter("task_due_date",currentDate).getResultList();
			for( tasks cv:tasks) {
				
				
				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
				@SuppressWarnings("unchecked")
				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
				returntotalObj.add(pat_tol);
			}
			
           }else if(api_type_id==2) {
        	 //upcoming task records
//        	Date date = new Date();
//   			String dateFormatString = "dd-MM-yyyy"; //24-03-2021
//   			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//   			String currentDate = dateFormat.format(date);
   		    //System.out.println("Current date:--- "+currentDate);
   		 //Date currentDate = new Date();
        	   Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				 System.out.println("DATE TIME IS"+currentDate);
   			
   			
   			//String hql = "FROM tasks WHERE task_due_date >:task_due_date order by tasks_id DESC";
   			//new code
   			String hql = "FROM tasks WHERE task_due_date >:task_due_date and (status = 'pending' OR  status = 'In Progress') order by tasks_id DESC";
   			//end
   			System.out.println("date  "+hql);
   			List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql).setParameter("task_due_date",currentDate).getResultList();
   			for( tasks cv:tasks) {
   				
   				
   				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
   				@SuppressWarnings("unchecked")
   				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
   				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
   				returntotalObj.add(pat_tol);
   			}
        	   
        	   
        	   
        	   
           }else if(api_type_id==3) {
        	   ////overdue task records  
        	   
//        	   Date date = new Date();
//      			String dateFormatString = "dd-MM-yyyy"; //24-03-2021
//      			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//      			String currentDate = dateFormat.format(date);
//      		    System.out.println("Current date:--- "+currentDate);
        	  // Date currentDate = new Date();
        	   Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				 System.out.println("DATE TIME IS"+currentDate);
      			
      			
      			//String hql = "FROM tasks WHERE task_due_date <:task_due_date order by tasks_id DESC";
      		    //new code
      		    String hql = "FROM tasks WHERE task_due_date <:task_due_date and (status = 'pending' OR  status = 'In Progress') order by tasks_id DESC";
      		    //end
      		    
      			System.out.println("date  "+hql);
      			List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql).setParameter("task_due_date",currentDate).getResultList();
      			for( tasks cv:tasks) {
      				
      				
      				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
       				@SuppressWarnings("unchecked")
       				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
       				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
       				returntotalObj.add(pat_tol);
      			}
      			
      			
        	   
           }else if(api_type_id==4) {
        	   ////overdue task records  
        	   
//        	   Date date = new Date();
//      			String dateFormatString = "dd-MM-yyyy"; //24-03-2021
//      			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//      			String currentDate = dateFormat.format(date);
//      		    System.out.println("Current date:--- "+currentDate);
        	  // Date currentDate = new Date();
        	   Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				 System.out.println("DATE TIME IS"+currentDate);
      			
      			
      			//String hql = "FROM tasks WHERE task_due_date <:task_due_date order by tasks_id DESC";
      		    //new code
      		    String hql = "FROM tasks WHERE (status = 'Completed') order by tasks_id DESC";
      		    //end
      		    
      			System.out.println("date  "+hql);
      			List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql).getResultList();
      			for( tasks cv:tasks) {
      				
      				
      				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
      				@SuppressWarnings("unchecked")
      				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
      				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
      				returntotalObj.add(pat_tol);
      			}
           }else if(api_type_id==5) {
        	   ////overdue task records  
        	   
//        	   Date date = new Date();
//      			String dateFormatString = "dd-MM-yyyy"; //24-03-2021
//      			DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
//      			String currentDate = dateFormat.format(date);
//      		    System.out.println("Current date:--- "+currentDate);
        	   Date currentDate = new Date();
      			
      			
      			//String hql = "FROM tasks WHERE task_due_date <:task_due_date order by tasks_id DESC";
      		    //new code
      		    String hql = "FROM tasks WHERE task_due_date =:task_due_date and (status = 'Completed') order by tasks_id DESC";
      		    //end
      		    
      			System.out.println("date  "+hql);
      			List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql).setParameter("task_due_date",currentDate).getResultList();
      			for( tasks cv:tasks) {
      				
      				
      				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
      				@SuppressWarnings("unchecked")
      				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
      				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
      				returntotalObj.add(pat_tol);
      			}
           }
			return returntotalObj;
			
			
		}
		@Override
		public void getUpcomingTasks() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void getUpcomingVisits() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void getOverdueVisits() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void getOverduetasks() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void getCompletedVisits() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void getCompletedTasks() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public List<patient_total_obj> getConsultationHistory(String patient_uuid) {
			
			List<patient_total_obj> returntotalObj=  new ArrayList<patient_total_obj>();
			
			String hql = "FROM clinical_visits WHERE patient_uuid=:patient_uuid";
			System.out.println("query    "+hql);
			List<clinical_visits> clinical_visits =(List<clinical_visits>) entityManager.createQuery(hql).setParameter("patient_uuid",patient_uuid).getResultList();
			String hql2 = "FROM tasks WHERE patient_uuid=:patient_uuid AND task_type = 45";
			
			for( clinical_visits cv:clinical_visits) {
				String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
				List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
				System.out.println("query    "+hql);
				List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql2).setParameter("patient_uuid",patient_uuid).getResultList();
				
				patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0),tasks.get(0));
				returntotalObj.add(pat_tol);
			}
			
			return returntotalObj;
		}
		@Override
		public psw_supervisor_obj getmetaDataLogin(int users_id,String role) {
			// TODO Auto-generated method stub
			
			psw_supervisor_obj objpsw=new psw_supervisor_obj();
			System.out.println("users_id---"+users_id);
			social_worker lg1 =null;
			group_data lg2 =null;
			supervisor lg3=null;
			//users lg = psw_supervisor_obj.getUser();
            //group_data grop_dta =psw_supervisor_obj.getGroup_data();
		    //social_worker psw = psw_supervisor_obj.getSocial_worker();
			String hql = "FROM users as usr WHERE usr.users_id = ?1";
			List<users> results = entityManager.createQuery(hql).setParameter(1, users_id).getResultList();
			users lg = results.get(0);
			if(role.equals("supervisor")) {
				
				     lg1 =null;
					 //lg2 = null;
					 String hql1 = "FROM supervisor as usr WHERE usr.users_id = ?1";
						List<supervisor> results1 = entityManager.createQuery(hql1).setParameter(1, users_id).getResultList();
						 lg3 = results1.get(0);
						
						 String hql2 = "FROM group_data as usr WHERE usr.supervisor_id = ?1";
							List<group_data> results2 = entityManager.createQuery(hql2).setParameter(1, lg3.getSupervisor_id()).getResultList();
							 lg2 = results2.get(0);
					 
			}else {
				//lg3=null;
			String hql1 = "FROM social_worker as usr WHERE usr.users_id = ?1";
			List<social_worker> results1 = entityManager.createQuery(hql1).setParameter(1, users_id).getResultList();
			 lg1 = results1.get(0);
				
				String hql2 = "FROM group_data as usr WHERE usr.social_worker_id = ?1";
				List<group_data> results2 = entityManager.createQuery(hql2).setParameter(1, lg1.getSocial_worker_id()).getResultList();
				 lg2 = results2.get(0);
				 System.out.println(lg2.getSupervisor_id());
				 
				 String hql3 = "FROM supervisor as usr WHERE usr.supervisor_id = ?1";
					List<supervisor> results3 = entityManager.createQuery(hql3).setParameter(1,lg2.getSupervisor_id()).getResultList();
					 lg3 = results3.get(0);
			}
		
			
			//lg.setUser_name("2345");
			//lg.setPassword("4567");
			
			objpsw.setGroup_data(lg2);
			objpsw.setSocial_worker(lg1);
			objpsw.setUser(lg);
			objpsw.setSupervisor(lg3);
			
			
			
			return objpsw;
		}
		
		
		
		@Override
		public supervisor_obj getmetaDataLogin1(int users_id,String role) {
			// TODO Auto-generated method stub
			
			supervisor_obj objpsw=new supervisor_obj();
			
			social_worker lg1 =null;
			group_data lg2 =null;
			supervisor lg3=null;
		
			List<group_data> groupobj=  new ArrayList<group_data>();
			
			
			String hql = "FROM users as usr WHERE usr.users_id = ?1";
			List<users> results = entityManager.createQuery(hql).setParameter(1, users_id).getResultList();
			users lg = results.get(0);
			if(role.equals("supervisor")) {
				
				     lg1 =null;
					 //lg2 = null;
					 String hql1 = "FROM supervisor as usr WHERE usr.users_id = ?1";
						List<supervisor> results1 = entityManager.createQuery(hql1).setParameter(1, users_id).getResultList();
						 lg3 = results1.get(0);
						
						 String hql2 = "FROM group_data as usr WHERE usr.supervisor_id = ?1";
							List<group_data> groupobj1 = entityManager.createQuery(hql2).setParameter(1, lg3.getSupervisor_id()).getResultList();
							// lg2 = results2.get(0);
							
							groupobj.addAll(groupobj1);
							
							
					 
			}
		
			
			
			
			objpsw.setGroup_data(groupobj);
			objpsw.setSocial_worker(lg1);
			objpsw.setUser(lg);
			objpsw.setSupervisor(lg3);
			
			
			
			return objpsw;
		}
		
		@Override
		public List<task_master> getallTasks(String patient_uuid) {
		System.out.println("CHECKING"+patient_uuid);
		List<task_master> taskobj=  new ArrayList<task_master>();
			 String hql1 = "FROM tasks as tsk WHERE tsk.patient_uuid = ?1";
			 List<task_master> taskobj1 =entityManager.createQuery(hql1).setParameter(1, patient_uuid).getResultList();
			 taskobj.addAll(taskobj1);
			 return taskobj;
			
		}
		
		@Override
		public List<udid_info> getPatientUdid(String patient_uuid) {
		
		List<udid_info> udidobj=  new ArrayList<udid_info>();
			 String hql1 = "FROM udid_info as udid WHERE udid.patient_uuid = ?1 and udid_info_id IN ( SELECT MAX(udid_info_id) FROM udid_info GROUP BY patient_uuid )";
			 List<udid_info> udidobj1 =entityManager.createQuery(hql1).setParameter(1, patient_uuid).getResultList();
			 udidobj.addAll(udidobj1);
			 return udidobj1;
			
		}
		
		//get all active patients only for super supervisor
				@SuppressWarnings("unchecked")
				@Override
				public List<patientObj> getAllpatientsListActive() {
					
					//String hql = "FROM patientObj p where p.group_data_id=?1";
					//new code
					String hql = "FROM patientObj p where status = 'active'";
					//end
					return (List<patientObj>) entityManager.createQuery(hql).getResultList();
					

				}
				//end
				
				@SuppressWarnings("unchecked")
				@Override
				public List<patient_total_obj> getAllPatientList() {
					
					//String hql = "FROM patientObj p where p.group_data_id=?1";
					List<patient_total_obj> returntotalObj=  new ArrayList<patient_total_obj>() ;
					//new code
					//String hql3 = "FROM patientObj p where p.group_data_id=?1";
					String hql3 = "FROM patientObj p";
					//end
					//List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).setParameter(1,sw_id).getResultList();
					List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).getResultList();
					
					for( patientObj patientObj:patientObj1) {
						
						//String hql = "FROM clinical_visits WHERE followup_date=:followup_date and patient_uuid=:patient_uuid order by clinical_visits_id DESC";
						
							//new code
						String hql = "FROM clinical_visits  WHERE patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
						//end
					
						List<clinical_visits> clinical_visits =(List<clinical_visits>) entityManager.createQuery(hql).setParameter("patient_uuid",patientObj.getPatient_uuid()).getResultList();
						for( clinical_visits cv:clinical_visits) {
							String hql1 = "FROM patientObj as p WHERE patient_uuid=:patient_uuid";
							List<patientObj> temp = entityManager.createQuery(hql1).setParameter("patient_uuid",cv.getPatient_uuid()).getResultList();
							patient_total_obj pat_tol=new patient_total_obj(cv,temp.get(0));
							returntotalObj.add(pat_tol);
						}
						
						}
					return returntotalObj;
					

				}
				//end
		
		
				@Override
				public List<counts> getCounts(String group_data_id,int supervisor_id) {
				List<counts> countObj = new ArrayList<counts>();
				counts count = new counts();
				 Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				 String groupArray = group_data_id;
				 String[] group_array = groupArray.split("@@");
//				
				String hql1 =null;
				String hql2 =null;
				String hql3 =null;
				String hql4 =null;
				String hql5 =null;
				String hql6 =null;
				String hql7 =null;
				String hql8 =null;
				String hql9 =null;
				String hql10 =  null;
				String hql11 = null;
				String hql12 =  null;
				String hql13 = null;
				Long patient_count = (long) 0;
				Long psw_count = (long) 0;
				Long today_visit_count = (long) 0;
				Long today_task_count = (long) 0;
				Long overdue_vist_count = (long) 0;
				Long overdue_task_count = (long) 0;
				Long notes_count = (long) 0;
				Long today_completed_task = (long) 0;
				
				for(int i=0;i<group_array.length;i++) {
					
//				
				hql2 = "select count(social_worker_id) FROM group_data as p WHERE p.supervisor_id = ?1";
				psw_count = (Long) entityManager.createQuery(hql2).setParameter(1,supervisor_id).getSingleResult();
				
				hql3 = "FROM patientObj as p WHERE p.group_data_id = ?1 GROUP BY patient_uuid";
				List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).setParameter(1,Integer.parseInt(group_array[i])).getResultList();
				
				
				
				for( patientObj patientObj:patientObj1) {
					hql1 = "select count(clinical_visits_id) FROM clinical_visits WHERE patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
					patient_count  = Long.sum(patient_count,((Long) entityManager.createQuery(hql1).setParameter("patient_uuid",patientObj.getPatient_uuid()).getSingleResult()));
					System.out.println("Count is "+patient_count);
					
					
				hql4 = "select count(clinical_visits_uuid) FROM clinical_visits WHERE followup_date=:followup_date and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				today_visit_count= Long.sum(today_visit_count,(Long) entityManager.createQuery(hql4).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getSingleResult());
				
				
				
				hql5 = "select count(clinical_visits_uuid) FROM clinical_visits WHERE followup_date<:followup_date and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				overdue_vist_count= Long.sum(overdue_vist_count,(Long) entityManager.createQuery(hql5).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getSingleResult());
				
				}
				
				hql6 = "FROM tasks WHERE task_due_date =:task_due_date  order by tasks_id DESC";
				
				List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql6).setParameter("task_due_date",currentDate).getResultList();
				for( tasks tk1:tasks) {
					
					
					hql7 = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid=:patient_uuid and p.group_data_id = ?1";
				
					today_task_count =  Long.sum(today_task_count,(Long) entityManager.createQuery(hql7).setParameter("patient_uuid",tk1.getPatient_uuid()).setParameter(1,Integer.parseInt(group_array[i])).getSingleResult());
					
				}
				
				hql8 = "FROM tasks WHERE task_due_date <:task_due_date and (status = 'pending' OR  status = 'In Progress') order by tasks_id DESC";
				
				List<tasks> tasks2 =(List<tasks>) entityManager.createQuery(hql8).setParameter("task_due_date",currentDate).getResultList();
				for( tasks tk2:tasks2) {
					
					
					hql9 = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid=:patient_uuid and p.group_data_id = ?1";
				
					overdue_task_count =  Long.sum(overdue_task_count,(Long) entityManager.createQuery(hql9).setParameter("patient_uuid",tk2.getPatient_uuid()).setParameter(1,Integer.parseInt(group_array[i])).getSingleResult());
					
				}
				
				 hql10 = "FROM tasks WHERE task_due_date =:task_due_date and (status = 'Completed') order by tasks_id DESC";
	      		    //end
	      		    
	      			System.out.println("date  "+hql10);
	      			List<tasks> tasks1 =(List<tasks>) entityManager.createQuery(hql10).setParameter("task_due_date",currentDate).getResultList();
	      			for( tasks tk2:tasks1) {
	      				
	      				
	      				hql11 = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid=:patient_uuid and p.group_data_id=?1";
	      				
	      				today_completed_task= Long.sum(today_completed_task,(Long) entityManager.createQuery(hql11).setParameter("patient_uuid",tk2.getPatient_uuid()).setParameter(1,Integer.parseInt(group_array[i])).getSingleResult());
	      				System.out.println("total Count is "+today_completed_task);
	      			}
				}
				hql12 = " FROM supervisor as p WHERE p.supervisor_id= ?1";
				
				List<supervisor> supervisor =  (List<supervisor>) entityManager.createQuery(hql12).setParameter(1,supervisor_id).getResultList();
				for( supervisor sp:supervisor) {
					System.out.println(sp.getUsers_id());
				hql13 = " select count(notes_id) FROM notes as p WHERE p.recipient_user_id=:users_id and p.read_flag =:read_flag ";
				
				notes_count = Long.sum(notes_count,(Long)entityManager.createQuery(hql13).setParameter("users_id",sp.getUsers_id()).setParameter("read_flag", 1).getSingleResult());
				
				}
				System.out.println("total Count is "+today_completed_task);
					count.setPatient_count(patient_count);
					count.setPsw_count(psw_count);
					System.out.println("Count is "+today_task_count);
					count.setToday_visit_count(today_visit_count);
					count.setToday_task_count(today_task_count);
					count.setOverdue_vist_count(overdue_vist_count);
					count.setOverdue_task_count(overdue_task_count);
					count.setNotes_count(notes_count);
					count.setToday_completed_task(today_completed_task);
					 countObj.add(count);
					 return countObj;
					
				}
				
				
				
				@Override
				public List<counts> getAllDataCounts() {
				List<counts> countObj = new ArrayList<counts>();
				counts count = new counts();
				 Calendar cal = Calendar.getInstance();
				 cal.set(Calendar.HOUR_OF_DAY, 0);
				 cal.set(Calendar.MINUTE, 0);
				 cal.set(Calendar.SECOND, 0);
				 Date currentDate = cal.getTime();
				
//				
				String hql1 =null;
				String hql2 =null;
				String hql3 =null;
				String hql4 =null;
				String hql5 =null;
				String hql6 =null;
				String hql7 =null;
				String hql8 =null;
				String hql9 =null;
				String hql10 =  null;
				String hql11 = null;
				String hql12 =  null;
				String hql13 = null;
				Long patient_count = (long) 0;
				Long psw_count = (long) 0;
				Long today_visit_count = (long) 0;
				Long today_task_count = (long) 0;
				Long overdue_vist_count = (long) 0;
				Long overdue_task_count = (long) 0;
				Long notes_count = (long) 0;
				Long today_completed_task = (long) 0;
				
			
					
//				
				hql2 = "select count(social_worker_id) FROM group_data ";
				psw_count = (Long) entityManager.createQuery(hql2).getSingleResult();
				
				hql3 = "FROM patientObj as p GROUP BY patient_uuid";
				List<patientObj> patientObj1 =(List<patientObj>) entityManager.createQuery(hql3).getResultList();
				
				
				
				for( patientObj patientObj:patientObj1) {
					hql1 = "select count(clinical_visits_id) FROM clinical_visits WHERE patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
					patient_count  = Long.sum(patient_count,((Long) entityManager.createQuery(hql1).setParameter("patient_uuid",patientObj.getPatient_uuid()).getSingleResult()));
					System.out.println("Count is "+patient_count);
					
					
				hql4 = "select count(clinical_visits_uuid) FROM clinical_visits WHERE followup_date=:followup_date and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				today_visit_count= Long.sum(today_visit_count,(Long) entityManager.createQuery(hql4).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getSingleResult());
				
				
				
				hql5 = "select count(clinical_visits_uuid) FROM clinical_visits WHERE followup_date<:followup_date and patient_uuid=:patient_uuid and clinical_visits_id IN ( SELECT MAX(clinical_visits_id) FROM clinical_visits GROUP BY patient_uuid )";
				overdue_vist_count= Long.sum(overdue_vist_count,(Long) entityManager.createQuery(hql5).setParameter("followup_date",currentDate).setParameter("patient_uuid",patientObj.getPatient_uuid()).getSingleResult());
				
				}
				
				hql6 = "FROM tasks WHERE task_due_date =:task_due_date  order by tasks_id DESC";
				
				List<tasks> tasks =(List<tasks>) entityManager.createQuery(hql6).setParameter("task_due_date",currentDate).getResultList();
				for( tasks tk1:tasks) {
					
					
					hql7 = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid=:patient_uuid";
				
					today_task_count =  Long.sum(today_task_count,(Long) entityManager.createQuery(hql7).setParameter("patient_uuid",tk1.getPatient_uuid()).getSingleResult());
					
				}
				
				hql8 = "FROM tasks WHERE task_due_date <:task_due_date and (status = 'pending' OR  status = 'In Progress') order by tasks_id DESC";
				
				List<tasks> tasks2 =(List<tasks>) entityManager.createQuery(hql8).setParameter("task_due_date",currentDate).getResultList();
				for( tasks tk2:tasks2) {
					
					
					hql9 = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid=:patient_uuid ";
				
					overdue_task_count =  Long.sum(overdue_task_count,(Long) entityManager.createQuery(hql9).setParameter("patient_uuid",tk2.getPatient_uuid()).getSingleResult());
					
				}
				
				 hql10 = "FROM tasks WHERE task_due_date =:task_due_date and (status = 'Completed') order by tasks_id DESC";
	      		    //end
	      		    
	      			System.out.println("date  "+hql10);
	      			List<tasks> tasks1 =(List<tasks>) entityManager.createQuery(hql10).setParameter("task_due_date",currentDate).getResultList();
	      			for( tasks tk2:tasks1) {
	      				
	      				
	      				hql11 = "select count(patient_id) FROM patientObj as p WHERE p.patient_uuid=:patient_uuid";
	      				
	      				today_completed_task= Long.sum(today_completed_task,(Long) entityManager.createQuery(hql11).setParameter("patient_uuid",tk2.getPatient_uuid()).getSingleResult());
	      				System.out.println("total Count is "+today_completed_task);
	      			}
				
			
				System.out.println("total Count is "+today_completed_task);
					count.setPatient_count(patient_count);
					count.setPsw_count(psw_count);
					System.out.println("Count is "+today_task_count);
					count.setToday_visit_count(today_visit_count);
					count.setToday_task_count(today_task_count);
					count.setOverdue_vist_count(overdue_vist_count);
					count.setOverdue_task_count(overdue_task_count);
					count.setNotes_count(notes_count);
					count.setToday_completed_task(today_completed_task);
					 countObj.add(count);
					 return countObj;
					
				}
				
			
				

} 