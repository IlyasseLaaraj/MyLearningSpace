package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.Kebab;
import com.advancia.models.kebabComponents.Sauce;

public class SauceDao {
	private static final Logger log = LoggerFactory.getLogger(SauceDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	public void deleteUserKebabSauces(EntityManager entityManager, int kebabId) {
		log.info("Received new request to delete sauces for kebab: {}", kebabId);
		try {

			Kebab kebab = entityManager.find(Kebab.class, kebabId);

			kebab.getSauces().clear();

			if (kebab.getSauces().size() == 0) {
				log.info("Removed {} sauces for kebabId", kebabId);
			}

		} catch (Exception e) {
			throw new RuntimeException("Error while deleting the sauces for kebabId " + kebabId, e);
		}
	}

	public void addSauceForKebabId(int sauceId, int kebabId) {

		log.info("Received new request to add sauceId {} for kebabId: {}", sauceId, kebabId);
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			Sauce sauce = entityManager.find(Sauce.class, sauceId);

			Kebab kebab = entityManager.find(Kebab.class, kebabId);

			kebab.getSauces().add(sauce);

			entityManager.persist(kebab);

			log.info("SauceId {} correctly added for kebabId: {}", sauceId, kebabId);

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException("Error while adding sauceId " + sauceId + " to kebabId " + kebabId, e);
		}
	}

	public List<Sauce> getAllSauces(EntityManager entityManager) {
		List<Sauce> saucesList = new ArrayList<Sauce>();
		log.info("Received new request to get all sauces");

		try {

			saucesList = entityManager.createNamedQuery("getAllSauces", Sauce.class).getResultList();

			log.info("Retrieved {} sauces", saucesList.size());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting all sauces", e);
		}
		return saucesList;
	}

	public Sauce getSauceBySauceId(int sauceId) {
		Sauce sauce = null;
		log.info("Received new request to get sauce for sauceId {}", sauceId);
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			sauce = entityManager.find(Sauce.class, sauceId);

			log.info("Sauce found for sauceId {}", sauceId);

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException("Error while getting sauce by id ", e);
		}
		return sauce;
	}

	public Sauce getSauceBySauceName(EntityManager entityManager, String sauceName) {
		Sauce sauce = null;
		log.info("Received new request to get sauce for sauceName {}", sauceName);

		sauce = (Sauce) entityManager.createNamedQuery("getSauceByName").setParameter("sauceName", sauceName)
				.getSingleResult();

		log.info("Sauce found for sauceName {}", sauceName);

		System.out.println("Before committing transaction");
		return sauce;
	}
}