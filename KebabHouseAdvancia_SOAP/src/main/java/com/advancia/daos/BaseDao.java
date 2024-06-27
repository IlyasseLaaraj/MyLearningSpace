package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.kebabComponents.Base;

public class BaseDao {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

	public List<Base> getAllBases(EntityManager entityManager) {
		List<Base> basesList = new ArrayList<Base>();
		log.info("Received new request to get all bases");
		try {

			basesList = entityManager.createNamedQuery("getAllBases", Base.class).getResultList();

			log.info("Retrieved {} bases", basesList.size());

		} catch (Exception e) {
			throw new RuntimeException("Error while getting all bases", e);
		}
		return basesList;
	}

	public Base getBaseById(int baseId) {

		Base base = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			base = entityManager.find(Base.class, baseId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the base for baseId " + baseId, e);
		}
		return base;
	}

	public Base getBaseByName(String baseName) {
		Base base = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			base = (Base) entityManager.createNamedQuery("getBaseByName").setParameter("baseName", baseName)
					.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("No base found with name: " + baseName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the allergen for sauceName: " + baseName, e);
		}
		return base;
	}
}
