package com.advancia.utilities.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import com.advancia.daos.BaseDao;
import com.advancia.exceptions.InvalidAddException;
import com.advancia.models.dto.kebabComponents.BaseDto;
import com.advancia.models.kebabComponents.Allergen;
import com.advancia.models.kebabComponents.Base;

public class BaseManager {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	BaseDao baseDao = new BaseDao();

	public List<BaseDto> fetchAllBases() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		List<BaseDto> listOfBases = new ArrayList<BaseDto>();
		try {
			entityManager.getTransaction().begin();
			listOfBases = baseDao.getAllBases(entityManager).stream().map(BaseDto::new).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error fetching all allergens");
			throw e;
		}
		return listOfBases;
	}

	public Base fetchBaseById(int id) {

		Base base = baseDao.getBaseById(id);
		if (base == null) {
			throw new EntityNotFoundException("No base found with ID: " + id);
		}
		return base;
	}

	public Base fetchBaseByName(String name) {
		Base base = baseDao.getBaseByName(name);
		if (base == null) {
			throw new EntityNotFoundException("No allergen found with Name: " + name);
		}
		return base;
	}

	public void addBase(BaseDto baseDto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Base base = new Base(baseDto);

			entityManager.persist(base);

			entityManager.getTransaction().commit();
		} catch (RollbackException e) {

			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the base", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while adding the base", e);
		}
	}

	public void deleteBase(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();

		try {
			entityManager.getTransaction().begin();

			Base base = entityManager.find(Base.class, id);
			if (base != null) {

				entityManager.remove(base);

				entityManager.getTransaction().commit();
			}
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while updating the allergen", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while updating the allergen", e);
		}
	}

	public void updateBase(BaseDto base) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Base baseToUpdate = entityManager.find(Base.class, base.getBaseId());

			baseToUpdate.setBaseName(base.getBaseName());
			baseToUpdate.setBaseDescription(base.getBaseDescription());
			baseToUpdate.setAllergens(base.getAllergens().stream().map(Allergen::new).collect(Collectors.toList()));

			entityManager.persist(baseToUpdate);

			entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while updating the base", e.getCause().getCause());
		}
	}
}
