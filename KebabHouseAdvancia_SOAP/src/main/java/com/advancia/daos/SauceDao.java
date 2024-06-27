package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.kebabComponents.Sauce;

public class SauceDao {
	private static final Logger log = LoggerFactory.getLogger(SauceDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	public List<Sauce> getAllSauces(EntityManager entityManager) {
		List<Sauce> saucesList = new ArrayList<Sauce>();
		log.info("Received new request to get all sauces");

		try {

			saucesList = entityManager.createNamedQuery("getAllSauces", Sauce.class).getResultList();

			log.info("Retrieved sauces" + saucesList.size());

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

		} catch (Exception e) {
			throw new RuntimeException("Error while getting sauce by id ", e);
		}
		return sauce;
	}

	public Sauce getSauceBySauceName(String sauceName) {
		Sauce sauce = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			sauce = (Sauce) entityManager.createNamedQuery("getSauceByName").setParameter("sauceName", sauceName)
					.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No allergen found with name: " + sauceName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the allergen for sauceName: " + sauceName, e);
		}
		return sauce;
	}

}
