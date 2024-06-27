package com.advancia.utilities.Services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import com.advancia.daos.MeatDao;
import com.advancia.exceptions.InvalidAddException;
import com.advancia.models.dto.kebabComponents.MeatDto;
import com.advancia.models.kebabComponents.Allergen;
import com.advancia.models.kebabComponents.Meat;

public class MeatManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	MeatDao meatDao = new MeatDao();

	public List<MeatDto> fetchAllMeats() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		List<MeatDto> listOfMeats = meatDao.getAllMeats(entityManager).stream().map(MeatDto::new)
				.collect(Collectors.toList());

		entityManager.getTransaction().commit();
		return listOfMeats;
	}

	public Meat fetchMeatById(int id) {

		Meat meat = meatDao.getMeatById(id);
		if (meat == null) {
			throw new EntityNotFoundException("No base found with ID: " + id);
		}
		return meat;
	}

	public Meat fetchMeatByName(String name) {

		Meat meat = meatDao.getMeatByName(name);

		if (meat == null) {
			throw new EntityNotFoundException("No allergen found with Name: " + name);
		}

		return meat;
	}

	public void addMeat(MeatDto meatDto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Meat meat = new Meat(meatDto);

			entityManager.persist(meat);

			entityManager.getTransaction().commit();
		} catch (RollbackException e) {

			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the meat", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while adding the meat", e);
		}
	}

	public void deleteMeat(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Meat meat = entityManager.find(Meat.class, id);

		entityManager.remove(meat);

		entityManager.getTransaction().commit();
	}

	public void updateMeat(MeatDto meat) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
		entityManager.getTransaction().begin();

		Meat meatToUpdate = entityManager.find(Meat.class, meat.getMeatId());

		meatToUpdate.setMeatName(meat.getMeatName());
		meatToUpdate.setMeatDescription(meat.getMeatDescription());
		meatToUpdate.setAllergens(meat.getAllergens().stream().map(Allergen::new).collect(Collectors.toList()));

		entityManager.persist(meatToUpdate);

		entityManager.getTransaction().commit();
	} catch (RollbackException e) {
		entityManager.getTransaction().rollback();
		throw new InvalidAddException("Unexpected error while adding the base", e.getCause().getCause());
	}
}	

}
