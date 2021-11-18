package com.screener.app.services;

import  com.screener.app.entity.*;
import java.util.List;

public interface inotificationService { 
	
	
	//done
	int addNotification(notes notification);
	List<notes> getNotification(String patient_uuid);
	int getUnreadNotificationCount(int portal_flag);
	String getImage(String notes_uuid);
	
	//not done
	
	
	
	
	List<pat_notification> getAllNotification(int portal_flag,int hospital_id);
	
	int updateReadNotification(int notification_id);
	
	
	int getUnreadNotificationCountExt(int portal_flag,int hospital_id);

	List<notification> getAllHospitalNotification(int portal_flag);
	
	List<notes> getallnotificationfromsvr(int psw_id);
	
	List<notes> getNotificationlist(int supervisor_id);
	List<notes> getUnreadnotificationlist(int supervisor_id);
	int updateReadNotification1(String notes_uuid);
	int updateImageUrl(String ImgeUrl, int checkImgSrc);
	
} 