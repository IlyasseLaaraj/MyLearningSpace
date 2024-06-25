package com.advancia.utilities.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.AllergenDao;
import com.advancia.models.kebabComponents.Allergen;

public class AllergenManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	AllergenDao allergenDao = new AllergenDao();

	public List<Allergen> fetchAllAllergens() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		List<Allergen> listOfAllergens = allergenDao.getAllAllergens(entityManager);

		entityManager.getTransaction().commit();
		return listOfAllergens;
	}

	public Allergen fetchAllergenById(int id) {

		Allergen allergen = allergenDao.getAllergenById(id);

		return allergen;
	}

	public Allergen fetchAllergenByName(String name) {

		Allergen allergen = allergenDao.getAllergenByName(name);

		return allergen;
	}
	
	public void addAllergen(Allergen allergen) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(allergen);
		
		entityManager.getTransaction().commit();
	}
	
	public void deleteAllergen(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		Allergen allergen = entityManager.find(Allergen.class, id);
		
		entityManager.remove(allergen);
		
		entityManager.getTransaction().commit();
	}
	
	public void updateAllergen(Allergen allergen) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Allergen allergenToUpdate = entityManager.find(Allergen.class, allergen.getAllergenId());
		
		allergenToUpdate.setAllergenName(allergen.getAllergenName());
		allergenToUpdate.setAllergenDescription(allergen.getAllergenDescription());
		
		entityManager.persist(allergenToUpdate);
		
		entityManager.getTransaction().commit();
	}
}
