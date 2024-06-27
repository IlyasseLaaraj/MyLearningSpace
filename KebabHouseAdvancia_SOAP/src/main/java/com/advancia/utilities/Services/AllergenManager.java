package com.advancia.utilities.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import com.advancia.daos.AllergenDao;
import com.advancia.exceptions.InvalidAddException;
import com.advancia.models.dto.kebabComponents.AllergenDto;
import com.advancia.models.kebabComponents.Allergen;

public class AllergenManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	AllergenDao allergenDao = new AllergenDao();

	public List<AllergenDto> fetchAllAllergens() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		List<AllergenDto> listOfAllergens = new ArrayList<AllergenDto>();
		try {
			entityManager.getTransaction().begin();
			listOfAllergens = allergenDao.getAllAllergens(entityManager).stream().map(AllergenDto::new)
					.collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error fetching all allergens");
			throw e;
		}
		return listOfAllergens;
	}

	public Allergen fetchAllergenById(int id) {
		Allergen allergen = allergenDao.getAllergenById(id);
		if (allergen == null) {
			throw new EntityNotFoundException("No allergen found with ID: " + id);
		}
		return allergen;
	}

	public Allergen fetchAllergenByName(String name) {

		Allergen allergen = allergenDao.getAllergenByName(name);

		if (allergen == null) {
			throw new EntityNotFoundException("No allergen found with Name: " + name);
		}
		return allergen;
	}

	public void addAllergen(AllergenDto allergenDto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Allergen allergen = new Allergen(allergenDto);
			entityManager.persist(allergen);

			entityManager.getTransaction().commit();
		} catch (RollbackException e) {

			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the allergen", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while adding the allergen", e);
		}
	}

	public void deleteAllergen(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Allergen allergen = entityManager.find(Allergen.class, id);

			
			entityManager.remove(allergen);
				
			entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while updating the allergen", e.getCause().getCause());
		}
	}

	public void updateAllergen(AllergenDto allergen) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Allergen allergenToUpdate = entityManager.find(Allergen.class, allergen.getAllergenId());

			allergenToUpdate.setAllergenName(allergen.getAllergenName());
			allergenToUpdate.setAllergenDescription(allergen.getAllergenDescription());

			entityManager.persist(allergenToUpdate);

			entityManager.getTransaction().commit();

		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while updating the allergen", e.getCause().getCause());
		}
	}
}
