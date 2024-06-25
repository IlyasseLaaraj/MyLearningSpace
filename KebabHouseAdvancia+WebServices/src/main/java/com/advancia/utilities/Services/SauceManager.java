package com.advancia.utilities.Services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.SauceDao;
import com.advancia.models.dto.kebabComponents.SauceDto;
import com.advancia.models.kebabComponents.Allergen;
import com.advancia.models.kebabComponents.Sauce;

public class SauceManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	SauceDao sauceDao = new SauceDao();

	public List<SauceDto> fetchAllSauces() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		List<SauceDto> listOfSauces = sauceDao.getAllSauces(entityManager).stream().map(SauceDto::new).collect(Collectors.toList());

		return listOfSauces;
	}

	public Sauce fetchSauceById(int id) {

		Sauce sauce =  sauceDao.getSauceBySauceId(id);

		return sauce;
	}

	public Sauce fetchSauceByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Sauce sauce = sauceDao.getSauceBySauceName(entityManager, name);

		return sauce;
	}

	public void addSauce(SauceDto sauceDto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		
		Sauce sauce = new Sauce(sauceDto);
		
		entityManager.persist(sauce);
		
		entityManager.getTransaction().commit();
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
		entityManager.getTransaction().begin();
		
		Sauce sauceToUpdate = entityManager.find(Sauce.class, sauce.getSauceId());
		
		sauceToUpdate.setSauceName(sauce.getSauceName());
		sauceToUpdate.setSauceDescription(sauce.getSauceDescription());
		sauceToUpdate.setAllergens(sauce.getAllergens().stream().map(Allergen::new).collect(Collectors.toList()));
		
		entityManager.persist(sauceToUpdate);
		
		entityManager.getTransaction().commit();
	}
}
