package com.advancia.utilities.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.MeatDao;
import com.advancia.models.kebabComponents.Meat;

public class MeatManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	MeatDao meatDao = new MeatDao();
	
	public List<Meat> fetchAllMeats() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		List<Meat> listOfMeats = meatDao.getAllMeats(entityManager);

		entityManager.getTransaction().commit();
		return listOfMeats;
	}
	
	public Meat fetchMeatById(int id) {
		
		Meat meat = meatDao.getMeatById(id);
		
		return meat;
	}
	
	public Meat fetchMeatByName(String name) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Meat meat = meatDao.getMeatByName(entityManager, name);
		
		entityManager.getTransaction().commit();
		return meat;
	}
	
	public void addMeat(Meat meat) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(meat);
		
		entityManager.getTransaction().commit();
	}


	public void deleteMeat(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		Meat meat = entityManager.find(Meat.class, id);
		
		entityManager.remove(meat);
		
		entityManager.getTransaction().commit();
	}
	
	public void updateMeat(Meat meat) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Meat meatToUpdate = entityManager.find(Meat.class, meat.getMeatId());
		
		meatToUpdate.setMeatName(meat.getMeatName());
		meatToUpdate.setMeatDescription(meat.getMeatDescription());
		meatToUpdate.setAllergens(meat.getAllergens());
		
		entityManager.persist(meatToUpdate);
		
		entityManager.getTransaction().commit();
	}
	
}
