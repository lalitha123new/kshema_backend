package com.screener.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.screener.app.entity.*;

@Transactional
@Repository
public class taskMasterDao implements ItaskMasterDao {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<task_master> getTasklist() {
		// TODO Auto-generated method stub
		
		String hql = "FROM task_master as tm";
		return (List<task_master>) entityManager.createQuery(hql).getResultList();
		
	}

}