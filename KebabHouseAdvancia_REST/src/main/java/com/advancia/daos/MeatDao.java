package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.advancia.models.kebabComponents.Meat;

public class MeatDao {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	private static final Logger log = LoggerFactory.getLogger(MeatDao.class);

	public List<Meat> getAllMeats(EntityManager entityManager) {

		List<Meat> meatsList = new ArrayList<Meat>();
		log.info("Received new request to get all bases");
		try {

			meatsList = entityManager.createNamedQuery("getAllMeats", Meat.class).getResultList();
			log.info("Retrieved {} bases", meatsList.size());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting all bases", e);
		}
		return meatsList;
	}

	public Meat getMeatById(int meatId) {

		Meat meat = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			meat = entityManager.find(Meat.class, meatId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the meat for meatId " + meatId, e);
		}
		return meat;
	}

	public Meat getMeatByName(String meatName) {

		Meat meat = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
		meat = (Meat) entityManager.createNamedQuery("getMeatByName").setParameter("meatName", meatName)
				.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No allergen found with name: " + meatName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the allergen for allergenName: " + meatName, e);
		}
		return meat;
	}
}
