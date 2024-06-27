package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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

			return user;

		} catch(NoResultException e) {
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while checking user credentials for " + username);

		}
	}
	
	public List<User> getAllUsers(EntityManager entityManager){
		
		List<User> listOfUsers = new ArrayList<User>();
		log.info("Received new request to get all users");
		try {

			listOfUsers = entityManager.createNamedQuery("getAllUsers", User.class).getResultList();

			log.info("Retrieved {} users", listOfUsers.size());

		} catch (Exception e) {
			throw new RuntimeException("Error while getting all allergens", e);
		}
		return listOfUsers;
	}

	public User getUserById(int id) {

		User user = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();

			user = entityManager.find(User.class, id);

			System.out.println("Before committing transaction " + user.getCustomKebabs());
			System.out.println("Exit transaction");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the user for userId " + id, e);
		}
		return user;
	}
	
	public User getUserByName(String username) {
		User user = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
		
		user = (User) entityManager.createNamedQuery("getUserByName").setParameter("username", username)
				.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No username found with name: " + username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the allergen for username: " + username, e);
		}
		return user;
	}
}
