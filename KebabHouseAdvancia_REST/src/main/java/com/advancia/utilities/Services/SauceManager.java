package com.advancia.utilities.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import com.advancia.daos.SauceDao;
import com.advancia.exceptions.InvalidAddException;
import com.advancia.models.dto.kebabComponents.SauceDto;
import com.advancia.models.kebabComponents.Allergen;
import com.advancia.models.kebabComponents.Sauce;

public class SauceManager {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	SauceDao sauceDao = new SauceDao();

	public List<SauceDto> fetchAllSauces() {		
		List<SauceDto> listOfSauces = new ArrayList<SauceDto>(); 
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			listOfSauces = sauceDao.getAllSauces(entityManager).stream().map(SauceDto::new).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error fetching all sauces");
			throw e;
		}
		return listOfSauces;
	}

	public Sauce fetchSauceById(int id) {

		Sauce sauce =  sauceDao.getSauceBySauceId(id);
		if(sauce == null) {
			 throw new EntityNotFoundException("No sauce found with ID: " + id);
		}
		return sauce;
	}

	public Sauce fetchSauceByName(String name) {

		Sauce sauce = sauceDao.getSauceBySauceName(name);
		if (sauce == null) {
	        throw new EntityNotFoundException("No allergen found with Name: " + name);
	    }
		return sauce;
	}

	public void addSauce(SauceDto sauceDto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
		entityManager.getTransaction().begin();

		Sauce sauce = new Sauce(sauceDto);
		
		entityManager.persist(sauce);
		
		entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the sauce", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while adding the sauce", e);
		}
}


	public void deleteSauce(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		Sauce sauce = entityManager.find(Sauce.class, id);
		
		entityManager.remove(sauce);
		
		entityManager.getTransaction().commit();
	}
	
public void updateSauce(SauceDto sauce) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
		entityManager.getTransaction().begin();
		
		Sauce sauceToUpdate = entityManager.find(Sauce.class, sauce.getSauceId());
		
		sauceToUpdate.setSauceName(sauce.getSauceName());
		sauceToUpdate.setSauceDescription(sauce.getSauceDescription());
		sauceToUpdate.setAllergens(sauce.getAllergens().stream().map(Allergen::new).collect(Collectors.toList()));
		
		entityManager.persist(sauceToUpdate);
		
		entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while updating the sauce", e.getCause().getCause());
		}
	}
}
