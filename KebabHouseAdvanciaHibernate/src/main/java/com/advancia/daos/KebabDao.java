package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.Kebab;
import com.advancia.models.User;

public class KebabDao {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	private static final Logger log = LoggerFactory.getLogger(KebabDao.class);

	public static void addKebab(Kebab kebab) {
		log.info("Received new request to add new kebab with and name: {}", kebab.getName());
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			entityManager.persist(kebab);
			log.info("Kebab {} correctly added", kebab.getName());

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while adding kebab " + kebab.getName(), e);
		}
	}

	public void deleteKebabByKebabId(int kebabId) {
		log.info("Received new request to delete bases for kebabId {}", kebabId);
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			Kebab kebab = entityManager.find(Kebab.class, kebabId);

			entityManager.remove(kebab);

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting kebabId " + kebabId, e);
		}
	}

	public void updateKebab(Kebab updatedKebab) {

		log.info("Received new request to update kebab for kebabId: {}", updatedKebab.getKebabId());
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			Kebab oldKebab = entityManager.find(Kebab.class, updatedKebab.getKebabId());
			
			System.out.println(oldKebab);
			oldKebab
			.setBase(updatedKebab.getBase())
			.setIngredients(updatedKebab.getIngredients())
			.setKebabId(updatedKebab.getKebabId())
			.setMeat(updatedKebab.getMeat())
			.setName(updatedKebab.getName())
			.setSauces(updatedKebab.getSauces())
			.setUser(updatedKebab.getUser());

			entityManager.persist(oldKebab);

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting kebabId " + updatedKebab.getKebabId(), e);
		}
	}

	public List<Kebab> getUserKebabs(EntityManager entityManager, int userId) {
		List<Kebab> userKebabsList = new ArrayList<Kebab>();
		try {

			User user = entityManager.find(User.class, userId);

			userKebabsList = entityManager.createNamedQuery("getUserKebabs", Kebab.class).setParameter("user", user).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to retrieve partial user kebabs");
		}
		return userKebabsList;
	}
}