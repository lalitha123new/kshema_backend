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
public class usersDao implements IusersDao {
	@PersistenceContext	
	private EntityManager entityManager;
	//private passencryption ps=new passencryption("bhavisjbfweauioe");
	private SHA256 hash = new SHA256();
	
	@Autowired
	private emailDao emailDao;
	
	/*@Override
	public login getArticleById(int ) {
		return entityManager.find(Article.class, articleId);
	}*/
	@SuppressWarnings("unchecked")
	@Override
	public List<users> getAlllogins(int role_type) {
		String hql = "";
		if(role_type==1) {
			
			hql = "FROM users as lg";
			
		}else if(role_type==2){
			
			hql = "FROM users as lg where role="+"social worker";
		}else if(role_type==3){
			
			hql = "FROM users as lg where role="+"superviser";
		}
		
		
		return (List<users>) entityManager.createQuery(hql).getResultList();
	}	
	
	
		
	
	//kshema store user data
	@Override
	public void addlogin(users lg) {
		
		try {
			
			
			String hashpass = hash.hash(lg.getPassword());
			lg.setPassword(hash.hash(lg.getPassword()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		entityManager.persist(lg);
	}
	
	
	//new code
	//get kshema user data to display in the edit page
		@SuppressWarnings("unchecked")
//		@Override
//		public List<users> getUser1(int users_id) {
//			String hql = "";
//			//if(users_id==1) {
//				
//				hql = "FROM users as lg where users_id=?1";
//				
//				
//			//}
//			
//			return (List<users>) entityManager.createQuery(hql).setParameter(1, users_id).getResultList();
//		}
		
		
		
		@Override
		public List<users> getUsers(int role) {
			// TODO Auto-generated method stub
			
			String hql = "";
			if(role==1) {
				
				hql = "FROM users as lg";
				
			}else if(role==2){
				
				hql = "FROM users as lg where role="+"psw";
			}else if(role==3){
				
				hql = "FROM users as lg where role="+"superviser";
			}
			
			return (List<users>) entityManager.createQuery(hql).getResultList();
		}
		
		
		
		public List<social_worker> getPsws() {
			// TODO Auto-generated method stub
			
			String hql = "";
				
				hql = "FROM social_worker as lg";
				
			return (List<social_worker>) entityManager.createQuery(hql).getResultList();
		}
		
		public List<supervisor> getSupervisors() {
			// TODO Auto-generated method stub
			
			String hql = "";
				
				hql = "FROM supervisor as lg";
			
                
               
			return (List<supervisor>) entityManager.createQuery(hql).getResultList();
		}
		
		public List<supervisor> getTalukaSupervisors() {
			// TODO Auto-generated method stub
			
			String hql = "";
				
				hql = "FROM taluka_supervisor as lg";
				
				
				
			return (List<supervisor>) entityManager.createQuery(hql).getResultList();
		}
		
		
		//get kshema user data to display in the edit page
		@Override
		public psw_supervisor_obj getUser(int users_id,String role) {
			// TODO Auto-generated method stub
			
			psw_supervisor_obj objpsw=new psw_supervisor_obj();
			
			social_worker lg1 =null;
			group_data lg2 =null;
			supervisor lg3=null;
			
			String hql = "FROM users as usr WHERE usr.users_id = ?1";
			List<users> results = entityManager.createQuery(hql).setParameter(1, users_id).getResultList();
			users lg = results.get(0);
			if(role.equals("supervisor")) {
				
				     lg1 =null;
					
					 String hql1 = "FROM supervisor as usr WHERE usr.users_id = ?1";
						List<supervisor> results1 = entityManager.createQuery(hql1).setParameter(1, users_id).getResultList();
						 lg3 = results1.get(0);
						

					 
			}else {
				lg3=null;
			String hql1 = "FROM social_worker as usr WHERE usr.users_id = ?1";
			List<social_worker> results1 = entityManager.createQuery(hql1).setParameter(1, users_id).getResultList();
			 lg1 = results1.get(0);
				

			}
		
			objpsw.setSocial_worker(lg1);
			objpsw.setUser(lg);
			objpsw.setSupervisor(lg3);
			
			return objpsw;
		}
		
		
		//save edited kshema user details
		@Override
		public void updateUser(users lg,int user_id,int role_type,String status,int taluka_id,int social_worker_id,int supervisor_id) {
				
					  String hql = "UPDATE users SET "+
							  "user_name=:user_name,first_name=:first_name,last_name= :last_name,"+
							  "contact_no=:contact_no,email=:email"+
							 
							  " WHERE users_id= "+user_id;
					  
					  
			   Query query = entityManager.createQuery(hql);
			  
			   query.setParameter("user_name", lg.getUser_name());
			   query.setParameter("first_name", lg.getFirst_name());
			   query.setParameter("last_name", lg.getLast_name());
			   query.setParameter("contact_no", lg.getContact_no());
			   query.setParameter("email", lg.getEmail());
			   if(role_type==1) {
				 
				   String hql1 = "UPDATE social_worker SET "+
							  "first_name=:first_name,last_name= :last_name,"+
							  "contact_no=:contact_no,status=?4,taluka_id=?5"+
							 
							  " WHERE users_id= "+user_id;
				   
				   String hql2 = "UPDATE group_data SET "+
							  "taluka_id=?1"+
							 
							  " WHERE social_worker_id=?2";
				   
				   Query query1 = entityManager.createQuery(hql1);
				   Query query2 = entityManager.createQuery(hql2);

				   query1.setParameter("first_name", lg.getFirst_name());
				   query1.setParameter("last_name", lg.getLast_name());
				   query1.setParameter("contact_no", lg.getContact_no());
				   query1.setParameter(4, status);
				   query1.setParameter(5, taluka_id);
				   
				   query2.setParameter(1, taluka_id);
				   query2.setParameter(2, social_worker_id);
				   
				   int result = query1.executeUpdate();
				   int result1 = query2.executeUpdate();
				 
					
				}else if(role_type==2){
					System.out.println(lg.getFirst_name());
					 String hql1 = "UPDATE supervisor SET "+
							  "first_name=:first_name,last_name= :last_name,"+
							  "contact_no=:contact_no,status=?4"+
							 
							  " WHERE users_id= "+user_id;
					 
					 
					 String hql2 = "UPDATE taluka_supervisor SET "+
							  "taluka_id=?1"+
							  " WHERE supervisor_id=?2";
					 
					 Query query1 = entityManager.createQuery(hql1);
					 Query query2 = entityManager.createQuery(hql2);

					   query1.setParameter("first_name", lg.getFirst_name());
					   query1.setParameter("last_name", lg.getLast_name());
					   query1.setParameter("contact_no", lg.getContact_no());
					   query1.setParameter(4, status);
					   
					   query2.setParameter(1, taluka_id);
					   query2.setParameter(2, supervisor_id);
					   int result = query1.executeUpdate();
					   int result1 = query2.executeUpdate();
				}
			  
			   int result = query.executeUpdate();
			
		}
		
	
		public List<group_data> getGroupData(int supervisor_id) {
			// TODO Auto-generated method stub
			List<group_data> groupobj=  new ArrayList<group_data>();
			String hql = "";
				
				hql = "FROM group_data as lg WHERE supervisor_id=?1";
			
				
               
			//return (List<group_data>) entityManager.createQuery(hql).setParameter(1, supervisor_id).getResultList();
				List<group_data> groupobj1 =  (List<group_data>) entityManager.createQuery(hql).setParameter(1, supervisor_id).getResultList();
				groupobj.addAll(groupobj1);
				return groupobj;
		}
	//end of new code
		
		
		
		
	/*@Override
	public void updateArticle(Article article) {
		Article artcl = getArticleById(article.getArticleId());
		artcl.setTitle(article.getTitle());
		artcl.setCategory(article.getCategory());
		entityManager.flush();
	}
	@Override
	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
	}*/
	
	@Override
	public String loginExists(String username, String password) {
		String hashpass;
		try {
			hashpass = hash.hash(password);
			String hql = "FROM users as usr WHERE usr.user_name = ?1 and usr.password = ?2";
			List<users> results = entityManager.createQuery(hql).setParameter(1, username).setParameter(2, hashpass).getResultList();
			if(results.isEmpty()) {
				
				return "invalid";
				
			}else {
				
				for (users rs : results) 
				{ 
					System.out.println(rs.getUsers_id()+"@@###"+rs.getRole());
					return rs.getUsers_id()+"@@###"+rs.getRole();
				}
				
				
				
				
			 // return Integer.parseInt(results.get(0).toString());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return "invalid";
			
		}
		return "invalid";
	}
	
	//email exits
	@Override
	public int emailExists(String email) {
		
		
		try {
		 
			String hql = "select count(users_id) FROM users as usr WHERE usr.email = ?1";
			Long count = (Long) entityManager.createQuery(hql).setParameter(1, email).getSingleResult();
			String count1 =count.toString();
			System.out.println(count1);
			if(count1.equals(0)) {
				
				return 0;
				
			}else {
				
			    return Integer.parseInt(count1);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public void updatelogin(login lg,int hos_id) {
		  try {
		System.out.println("pwd-- "+lg.getPassword());
			  if(lg.getPassword().equals("")) {
				  
				  System.out.println("equils-- "+lg.getPassword()); 
				  String hql = "UPDATE login SET "+
						  "nameofhospital=:nameofhospital,contact= :contact,"+
						  "email=:email,"+
						  "username=:username,hospital_address=:hospital_address,"+
						  "hospitalType=:hospitalType" +
						  " WHERE hospitalid= "+hos_id;
			 System.out.println(hql);
		   Query query = entityManager.createQuery(hql);
		
		
		   query.setParameter("nameofhospital", lg.getNameofhospital());
		   query.setParameter("hospitalType", lg.getHospitalType());
		   query.setParameter("username", lg.getUsername());
		   query.setParameter("contact", lg.getContact());
		   query.setParameter("email", lg.getEmail());
		   query.setParameter("hospital_address",lg.getHospital_address());
		   //query.setParameter("hospitalid", hos_id);
		   int result = query.executeUpdate();
		  
			 
		
			  }else {
				  
				  String hql = "UPDATE login SET "+
						  "nameofhospital=:nameofhospital,contact= :contact,"+
						  "email=:email,password=:password,"+
						  "username=:username,hospital_address=:hospital_address,"+
						  "hospitalType=:hospitalType" +
						  " WHERE hospitalid= "+hos_id;
				  
				  System.out.println("NOTequils-- "+lg.getPassword()); 
		   Query query = entityManager.createQuery(hql);
		 
			String hashpass = hash.hash(lg.getPassword());
		
		   query.setParameter("nameofhospital", lg.getNameofhospital());
		   query.setParameter("hospitalType", lg.getHospitalType());
		   query.setParameter("username", lg.getUsername());
		   query.setParameter("contact", lg.getContact());
		   query.setParameter("email", lg.getEmail());
		   query.setParameter("password", hashpass);
		   query.setParameter("hospital_address",lg.getHospital_address());
		   //query.setParameter("hospitalid", hos_id);
		   int result = query.executeUpdate();
				  
				  
			  }
	   
	   
	   
		  } catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public String updateflagByemail(String email) {
		int count =0;
		try {
			System.out.println("email------"+email);
			verfiyEmail(email,3);
			String hql = "select users_id FROM users as lg WHERE lg.email = ?1";
			 count =  (int) entityManager.createQuery(hql).setParameter(1, email).getSingleResult();
			//System.out.println("count is "+count);
			 updateFlag(1,2,count);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return email;
	}

	@Override
	public String updateFlag(int flag_type, int flag,int hps_id) {
		
		 String hql ="";
		 Query query =null;
		 //if flag type 1 verify email flag
		if(flag_type == 1) {
			
			  hql = "UPDATE login SET "+
				      "verify_email_flag=:verify_email_flag" +
				      " WHERE hospitalid= "+hps_id;
			   query = entityManager.createQuery(hql);
			  query.setParameter("verify_email_flag", flag);
			  
//			  try {
//				verfiyEmail("narasimham1231@gmail.com");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//if flag type 2 review_flag flag	  
		}else if(flag_type == 2) {
			
			hql = "UPDATE login SET "+
				      "review_flag=:review_flag" +
				      " WHERE hospitalid= "+hps_id;
			  query = entityManager.createQuery(hql);
			  query.setParameter("review_flag", flag);
			    String hql1 = "select email FROM login as lg WHERE lg.hospitalid = ?1";
				String email1  =  (String) entityManager.createQuery(hql1).setParameter(1, hps_id).getSingleResult();
				System.out.println("email--"+email1);
			  
			  try {
				verfiyEmail(email1,2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		}
		
  int result = query.executeUpdate();
		
		
		return null;
	}
	
	
	public int verfiyEmail(String email,int mail_flag) throws  UnsupportedEncodingException {
		
  if(mail_flag == 2) {
	  
	    String m_to = email;
		String m_subject = "Registration for External Lab Tracker";
		//String emaillink = "\n<a href='Push.d.nimhans@gmail.com'>Push.d.nimhans@gmail.com</a>";
		String encoded = new String(Base64.getEncoder().encode(m_to.getBytes()));
		
		// String encoded = email;
		String m_text = "<div>Hello,</div><div>Your registration is approved.</div><div>Please use the submitted credentials for the login.</div><br>"
				+ "<a href= https://labservices.nimhans.ac.in/>External lab services"
				+"</a>";
		// String m_text = "some text";
		emailDao.sendEmail(email, m_subject, m_text);
		
  }else if(mail_flag == 1) {
	  
		String m_to = email;
		String m_subject = "E-mail verification for External Lab Tracker";
		//String emaillink = "\n<a href='Push.d.nimhans@gmail.com'>Push.d.nimhans@gmail.com</a>";
		String encoded = new String(Base64.getEncoder().encode(m_to.getBytes()));
		
		// String encoded = email;
		String m_text = "<div>Hello,</div><div>You have initiated registration  process for Nimhans Lab Tracker .</div><div>Please click on the  link to  complete registration and activate login.<br></div><br>"
				+ "<a href= https://labservices.nimhans.ac.in/#/approve?email=" + encoded + "> Click here to Approve"
				/*+ "<a href= http://localhost:4200/#/approve?email=" + encoded + "> Click here to Approve"*/
				+"</a>";
		// String m_text = "some text";
		emailDao.sendEmail(email, m_subject, m_text);
		
       }else {
    	   
			String m_to = "nimhansneuropathology@gmail.com";
			
    	  // String m_to = "lalitha.vikas@iiitb.ac.in";
   		String m_subject = "New user is waiting for registration approval";
  
   	
   		
   		// String encoded = email;
   		String m_text = "<div>Hello,</div><div>Please approve the registration of the new user as the E-mail verfication is done .<br>User E-mail id is :"+ email +"</div>";
   		// String m_text = "some text";
   		emailDao.sendEmail(m_to, m_subject, m_text);
    	   
       }
		return 0;
	}
	
	@Override
	public String forgotemail(String email) {
		// TODO Auto-generated method stub
		
		String m_to = email;
		String m_subject = "New password link for External Lab Tracker";
		String encoded = new String(Base64.getEncoder().encode(m_to.getBytes()));
		
		// String encoded = email;
		String m_text = "<div>Hello,</div><div>You have initiated process to set new password for Nimhans Lab Tracker .</div><div>Please click on the  link to  complete the set new password process and proceed.<br></div><br>"
				+ "<a href= https://labservices.nimhans.ac.in/#/newpass?email=" + encoded + "> Click Here"
				/*+ "<a href= http://localhost:4200/#/newpass?email=" + encoded + "> Click Here"*/
				+"</a>";
		// String m_text = "some text";
		emailDao.sendEmail(email, m_subject, m_text);
		
		
		return "linkSent";
	}
	
	public void updatepassword(String password,String email) {
		 
				  String hql = "UPDATE login SET password=:password WHERE email= '"+email+"'";
		          Query query = entityManager.createQuery(hql);
		          String hashpass = null;
				try {
					hashpass = hash.hash(password);
					  query.setParameter("password", hash.hash(password));
			          int result = query.executeUpdate();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					//lg.setPassword(hash.hash(lg.getPassword()));
		         
		   
			  }
	
	@Override
	public int loginExists1(String username,String email) {
		
		
		try {
		  
			String hql = "select users_id FROM users as usr WHERE lg.user_name = ?1";
			int count =  (int) entityManager.createQuery(hql).setParameter(1, username).getSingleResult();
			System.out.println("count------------------"+ count);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean addloginsuper(@Valid taluk_supervisor_obj taluk_supervisor_obj) {
		// TODO Auto-generated method stub	
		
		users lg = taluk_supervisor_obj.getUser();
		
		taluka_supervisor ts=taluk_supervisor_obj.getTaluka_supervisor();
		supervisor sup = taluk_supervisor_obj.getSupervisor();
		
              try {
			//userTable
            	  System.out.println("lg--"+lg.getPassword());
            	  System.out.println("lg--"+lg.getEmail());
            	  System.out.println("lg--"+lg.getFirst_name());
			//String hashpass = hash.hash(lg.getPassword());
			lg.setPassword(hash.hash(lg.getPassword()));
			
			//get max id users_id
			String hql = "select MAX(users_id) FROM users";
			List count =   entityManager.createQuery(hql).getResultList();
			
			  
			int cnt= (int) count.get(0); 
			
			//supervisor table
			sup.setUsers_id(cnt+1);
			entityManager.persist(sup);
			
			
			//get supervisor id
			String hql1 = "select MAX(supervisor_id) FROM  supervisor";
			List count1 =   entityManager.createQuery(hql1).getResultList();
			int cnt1= (int) count1.get(0); 
		//mapping table taluka_supervisor 
			//ts.setSupervisor_id(cnt1+1);
			ts.setSupervisor_id(cnt1);
			entityManager.persist(ts);
			
			
			
		
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		entityManager.persist(lg);
		return false;
	}

	@Override
	public boolean addloginpsw(@Valid psw_supervisor_obj psw_supervisor_obj,int taluka_id) {
		
              users lg = psw_supervisor_obj.getUser();
              group_data grop_dta =psw_supervisor_obj.getGroup_data();
		      social_worker psw = psw_supervisor_obj.getSocial_worker();
		
              try {
			//userTable
            	  System.out.println("lg--"+lg.getPassword());
			//String hashpass = hash.hash(lg.getPassword());
			lg.setPassword(hash.hash(lg.getPassword()));
			
			//get max id users_id
			String hql = "select MAX(users_id) FROM users";
			List count =   entityManager.createQuery(hql).getResultList();
			
			  
			int cnt= (int) count.get(0); 
			
			//socialworker table
			psw.setUsers_id(cnt+1);
			System.out.println("check1"+psw);
			entityManager.persist(psw);
			System.out.println("check2");
			
			
			//get supervisor id
//			String hql1 = "select supervisor_id FROM  taluka_supervisor as sr WHERE sr.taluka_id = ?1";
//			List count1 =   entityManager.createQuery(hql1).setParameter(1, psw.getTaluka_id()).getResultList();
//			int cnt1= (int) count1.get(0); 
//			//mapping table taluka_supervisor 
//			System.out.println("777777----"+psw.getSocial_worker_id());
//			grop_dta.setSupervisor_id(cnt1);
//			grop_dta.setSocial_worker_id(psw.getSocial_worker_id());
//			System.out.println(grop_dta);
//			entityManager.persist(grop_dta);	
			String hql1 = "select MAX(social_worker_id) FROM social_worker";
			List count1 =   entityManager.createQuery(hql1).getResultList();
			int cnt1= (int) count1.get(0);
			 String hql2 = "UPDATE group_data SET "+
					  "social_worker_id=:social_worker_id"+
					 
					  " WHERE taluka_id= "+taluka_id;
			  
			 System.out.println("check10"+cnt1);
			 System.out.println("check11"+taluka_id);
	   Query query = entityManager.createQuery(hql2);
	  
	   query.setParameter("social_worker_id", cnt1);
	   int result = query.executeUpdate();
	   
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
             entityManager.persist(lg);
		
		
		
		return false;
	}




//	@Override
//	public void getUser1(users usr, int users_id) {
//		// TODO Auto-generated method stub
//		
//	}
//	
	
	
} 