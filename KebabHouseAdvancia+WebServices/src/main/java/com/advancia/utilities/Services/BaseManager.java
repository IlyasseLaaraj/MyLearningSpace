package com.advancia.utilities.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.BaseDao;
import com.advancia.models.kebabComponents.Base;

public class BaseManager {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	BaseDao baseDao = new BaseDao();
	
	public List<Base> fetchAllBases(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Base> listOfBases = baseDao.getAllBases(entityManager);
		
		entityManager.getTransaction().commit();
		return listOfBases;
	}

	public Base fetchBaseById(int id) {
		 
		Base base = baseDao.getBaseById(id);
		
		return base;
	}
	
	public Base fetchBaseByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Base base = baseDao.getBaseByName(entityManager, name);
		
		entityManager.getTransaction().commit();
		return base;
	}
	
	public void addBase(Base base) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(base);
		
		entityManager.getTransaction().commit();
	}
	
	public void deleteBase(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		Base base = entityManager.find(Base.class, id);
		
		entityManager.remove(base);
		
		entityManager.getTransaction().commit();
	}
	
	public void updateBase(Base base) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Base baseToUpdate = entityManager.find(Base.class, base.getBaseId());
		
		baseToUpdate.setBaseName(base.getBaseName());
		baseToUpdate.setBaseDescription(base.getBaseDescription());
		baseToUpdate.setAllergens(base.getAllergens());
		
		entityManager.persist(baseToUpdate);
		
		entityManager.getTransaction().commit();
	}
	
}
