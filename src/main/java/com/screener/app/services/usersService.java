package com.screener.app.services;

import  com.screener.app.entity.*;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.screener.app.dao.*;
@Service
public class usersService implements IUserService {
	@Autowired
	private IusersDao usersDao;
	/*@Override
	public Article getArticleById(int articleId) {
		Article obj = articleDAO.getArticleById(articleId);
		return obj;
	}*/	
	@Override
	public List<users> getAlllogins(int role_type){
		return usersDao.getAlllogins(role_type);
	}
	
	
	
	@Override
	public String checklogin(users usr)
	{
		 if (!usersDao.loginExists(usr.getUser_name(), usr.getPassword()).equals("invalid") ) {
	            return usersDao.loginExists(usr.getUser_name(), usr.getPassword());
         } else {
	           // usersDao.addlogin(lg);
	            return "invalid";
         }
	}
	
	@Override
	public synchronized boolean addlogin(users lg){
                if (!usersDao.loginExists(lg.getUser_name(), lg.getPassword()).equals("invalid")) {
    	            return false;
                } else {
                	
               int cnt=usersDao.emailExists(lg.getEmail());
               if(cnt <= 0) {
            	  usersDao.addlogin(lg);
   	             return true;
               }else {
            	   return false;
               }
    	           
    	            
                }
	}
	
	
	//new code
	
	@Override
	public List<users> getUsers(int role){
		return usersDao.getUsers(role);
	}
	
	@Override
	public List<social_worker> getPsws(){
		return usersDao.getPsws();
	}
	
	@Override
	public List<supervisor> getSupervisors(){
		return usersDao.getSupervisors();
	}
	
	@Override
	public List<supervisor> getTalukaSupervisors(){
		return usersDao.getTalukaSupervisors();
	}
	
	@Override
	public psw_supervisor_obj getUser(int users_id,String role){
		return usersDao.getUser(users_id,role);
	}
	
	@Override
	public synchronized void  updateUser(users lg,int users_id,int role_type,String status,int taluka_id,int social_worker_id,int supervisor_id) {
		
		usersDao.updateUser(lg,users_id,role_type,status,taluka_id,social_worker_id,supervisor_id);
		
	}
	@Override
	public List<group_data> getGroupData(int supervisor_id){
		return usersDao.getGroupData(supervisor_id);
	}

	
	//end of new code
	
	
	/*@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
	}
	@Override
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticle(articleId);
	}*/
	
	@Override
	public synchronized void  updatelogin(login lg,int hosp_id) {
		
		usersDao.updatelogin(lg,hosp_id);
		
	}
	
	@Override
	public String updateFlag(int flag_type, int flag,int hps_id) {
	// TODO Auto-generated method stub

	String str = usersDao.updateFlag(flag_type,flag,hps_id);
	return null;
	}
	
	@Override
	public String updateflagByemail(String email) {
		// TODO Auto-generated method stub
		
		String str = usersDao.updateflagByemail(email);
		return null;
	}
	
	@Override
	public String forgotemail(String email) {
		// TODO Auto-generated method stub
		
		String str = usersDao.forgotemail(email);
		return null;
	}

	@Override
	public void updatepassword(String password, String email) {
		// TODO Auto-generated method stub
		usersDao.updatepassword(password,email);
		
	}

	
	@Override
	public boolean addloginsuper(@Valid taluk_supervisor_obj taluk_supervisor_obj) {
		
		boolean b= usersDao.addloginsuper(taluk_supervisor_obj);
		  
		return b;
	}

	@Override
	public boolean addloginpsw(@Valid psw_supervisor_obj psw_supervisor_obj,int taluka_id) {
		
		boolean b= usersDao.addloginpsw(psw_supervisor_obj,taluka_id);
		  
		return b;
	}
} 






/*public class loginService{
	
	public String check(login lg)
	{
		return "hello";
	}
	//SessionFactory session = HibernateUtil.getSessionFactory();
	  Session session = HibernateUtil.getSessionFactory().openSession(); 
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate.cfg.xml");
        EntityManager em = emf.createEntityManager();
		
		Query query = session.createQuery("select c.id,c.name from patient c");
		List<Object[]> rows = query.list();
	/*String user = lg.getParameter("userid");
    String password = lg.getParameter("password");
    String select = null;
    EntityManagerFactory entityFactory = Persistence
            .createEntityManagerFactory("password");
    EntityManager entityManager = entityFactory.createEntityManager();
    select = "SELECT userid, password FROM login WHERE userName='"
            + user + "' and password='" + password + "'";
    Query query = (Query) entityManager.createQuery(select);
    query.setParameter("userid", user);
    query.setParameter("password", password);
    




    if(((javax.persistence.Query) query).getResultList().size() == 0){
        return "Account does not exist!";
    }else{
        return "Login Success!";
    }
	}
 }*/