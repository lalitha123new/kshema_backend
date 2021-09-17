package com.screener.app.dao;

import com.screener.app.entity.UserSessionDb;

public interface UserSessionDAO {
	
	public UserSessionDb saveSession(String token, String userId);
	public long getSession(String sessionToken);
	public boolean updateSession(String userId, String token);
	public UserSessionDb getSession(String sesId, String sessionToken);
	boolean updateUserSession(String sesId, String userId, String sessionToken);
}
