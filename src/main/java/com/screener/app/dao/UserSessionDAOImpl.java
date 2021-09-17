package com.screener.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.screener.app.entity.Logintemp;
import com.screener.app.entity.UserSessionDb;
import com.screener.app.entity.login;
//import com.screener.app.util.HibernateUtilConfig;
import com.screener.app.util.LoadConfig;

public class UserSessionDAOImpl implements UserSessionDAO {
	Logger logger = LoggerFactory.getLogger(UserSessionDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserSessionDb saveSession(String token, String userId) {
		Session ses = null;
		Transaction tx = null;
		UserSessionDb objUserSesDb = new UserSessionDb();
		// get Session
		// ses = ((HibernateEntityManager) entityManager).getSession();

		try {
			// pat.setStatus("Pending");
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());

		} catch (Exception e) {
			System.out.println("patient data not saved");
		}

		// try {
		// objUserSesDb = UserSessionDb.mapUserSesCOToDB(token, userId);
		// logger.info("User Session to be created for -> "+objUserSesDb.getUserId());
		//
		// tx = ses.beginTransaction();
		// ses.saveOrUpdate(objUserSesDb);
		// logger.info("User record created");
		// tx.commit();
		//
		// } catch (Exception e) {
		// logger.error("User record creation problem", e);
		// tx.rollback();
		// objUserSesDb = null;
		//
		// } finally {
		//
		// HibernateUtil.closeSession();
		// }
		return objUserSesDb;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public long getSession(String sessionToken) {
		// Session ses = null;
		// long sesCount =0;
		// get Session

		try {

			String hql = "Select count(*) from UserSessionDb u where u.sessionToken =?1";
			@SuppressWarnings("unchecked")
			List<Integer> sesCount = entityManager.createQuery(hql).setParameter(1, sessionToken).getResultList();
			return sesCount.get(0);
		} catch (Exception e) {

		}
		return 0;

	}

	@Override
	public UserSessionDb getSession(String sesId, String sessionToken) {
		Session ses = null;
		UserSessionDb userSesDomain = null;
		// get Session

		logger.info("Fetching data for session Token =>>>>>> **" + sessionToken + "**");
		try {

			String hql = "FROM UserSessionDb as u where lg.username=?1u.sessionToken = ?1 and u.id = ?2";
			userSesDomain = (UserSessionDb) entityManager.createQuery(hql).setParameter(1, sessionToken)
					.setParameter(2, Integer.parseInt(sesId)).getSingleResult();

			// return lt;
			//
			//
			//
			// ses = HibernateUtil.getSession();
			//
			// TypedQuery<UserSessionDb> query = ses.createQuery("Select u from
			// UserSessionDb u where u.sessionToken = :sessionToken and u.id = :sesId",
			// UserSessionDb.class);
			// query.setParameter("sessionToken", sessionToken);
			// query.setParameter("sesId", Integer.parseInt(sesId));
			// userSesDomain = query.getSingleResult();
		} catch (Exception e) {

		}

		return userSesDomain;
	}

	@SuppressWarnings({ "null", "unused" })
	@Override
	public boolean updateSession(String userId, String sessionToken) {
		Session ses = null;
		Transaction tx = null;
		UserSessionDb userSesDomain = null;
		int result = 0;
		// get Session

		try {

			String hql = "update UserSessionDb u set u.status = :status, u.logoutAt = :logoutAt where u.sessionToken = :sessionToken AND u.userId = :userId";
			Query query = entityManager.createQuery(hql);
			query.setParameter("sessionToken", sessionToken);
			query.setParameter("status", LoadConfig.getConfigValue("INACTIVE_SESSION_STATUS"));
			query.setParameter("userId", userId);
			query.setParameter("logoutAt", new Date());
			result = query.executeUpdate();
			if (result != 0) {

				return true;
			} else {

				return false;
			}
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public boolean updateUserSession(String sesId, String userId, String sessionToken) {

		int result = 0;
		// get Session

		logger.info("Fetching data for session Token =>>>>>> **" + sessionToken + "**");
		try {

			Date expiryDate = new Date(
					System.currentTimeMillis() + Long.parseLong(LoadConfig.getConfigValue("JWT_EXPIRY_TIMEOUT")));

			Query query = entityManager.createQuery(
					"update UserSessionDb u set u.expiryAt = :expiryAt where u.id = :sesId and u.sessionToken = :sessionToken and u.userId = :userId and u.status = 'ACTIVE'");
			query.setParameter("expiryAt", expiryDate);
			query.setParameter("userId", userId);
			query.setParameter("sessionToken", sessionToken);
			query.setParameter("sesId", Integer.parseInt(sesId));
			result = query.executeUpdate();

			if (result != 0) {

				return true;
			} else {

				return false;
			}
		} catch (Exception e) {
			System.out.println("ID not found ::");

			return false;
		}
	}
}
