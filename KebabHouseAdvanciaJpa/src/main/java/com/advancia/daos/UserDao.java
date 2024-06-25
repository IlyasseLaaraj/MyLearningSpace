package com.advancia.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.User;

public class UserDao {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	private static final Logger log = LoggerFactory.getLogger(UserDao.class);

	public User checkUserCredentials(String username, String password) {

		User user = null;
		log.info("Checking user credential for username: {}", username);
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			user = (User) entityManager.createNamedQuery("getUsernameAndUserPassword")
					.setParameter("username", username)
					.setParameter("password", password)				
					.getSingleResult();

			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();

			if (user != null) {
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while checking user credentials for " + username);

		}
	}

}
