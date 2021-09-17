package com.screener.app.services;

import com.screener.app.entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.screener.app.dao.*;

@Service
public class notificationService implements inotificationService {
	@Autowired
	private inotificationDao notificationDao;
	
	
	//done - add notes from supervisor and psw
	@Override
	public int addNotification(notes notification) {
		
	    int r =	notificationDao.addNotification(notification);
		return r;
	}
	
	//get the notes for patient
	@Override
	public List<notes> getNotification(String patient_uuid) {
		
		
		return notificationDao.getNotification(patient_uuid);
	}
	
	//get the unreadnotification for superviosr 
	@Override
	public int getUnreadNotificationCount(int portal_flag) {
		// TODO Auto-generated method stub
		return notificationDao.getUnreadNotificationCount(portal_flag);
	}
	

	//get all notes from supervisor to psw
	@Override
	public List<notes> getallnotificationfromsvr(int psw_id) {
		
		return notificationDao.getallnotificationfromsvr(psw_id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------//

	

	@Override
	public List<pat_notification> getAllNotification(int portal_flag, int hospital_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReadNotification(int notification_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public int getUnreadNotificationCountExt(int portal_flag, int hospital_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<notification> getAllHospitalNotification(int portal_flag) {
		// TODO Auto-generated method stub
		return null;
	}
	
   
	//get all notification list for supervisor
	@Override
	public List<notes> getNotificationlist(int supervisor_id) {
		
		return notificationDao.getNotificationlist(supervisor_id);
	}
	
	
	//get all unread notification list for supervisor
	@Override
	public List<notes> getUnreadnotificationlist(int supervisor_id) {
		
		return notificationDao.getUnreadnotificationlist(supervisor_id);
	}
	
	//get all unread notification list for supervisor
		@Override
		public int updateReadNotification1(String notes_uuid) {
			
			return notificationDao.updateReadNotification1(notes_uuid);
		}

	
	
	
	
} 






