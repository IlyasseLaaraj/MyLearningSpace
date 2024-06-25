package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the base for baseId " + baseId, e);
		}
		return base;
	}

	public Base getBaseByName(EntityManager entityManager, String baseName) {
		Base base = null;

		log.info("Received new request to get base for baseName {}", baseName);

		base = (Base) entityManager.createNamedQuery("getBaseByName").setParameter("baseName", baseName)
				.getSingleResult();

		System.out.println("Before committing transaction");

		return base;
	}
}
