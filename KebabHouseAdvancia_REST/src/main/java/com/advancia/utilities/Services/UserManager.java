package com.advancia.utilities.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import com.advancia.daos.UserDao;
import com.advancia.exceptions.InvalidAddException;
import com.advancia.models.User;
import com.advancia.models.dto.UserDto;

public class UserManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	UserDao userDao = new UserDao();

	public List<UserDto> fetchAllUsers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		List<UserDto> listOfUsers = new ArrayList<UserDto>();
		try {
			entityManager.getTransaction().begin();
			listOfUsers = userDao.getAllUsers(entityManager).stream().map(UserDto::new).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error fetching all allergens");
			throw e;
		}
		return listOfUsers;
	}

	public User fetchUserById(int id) {

		User user = userDao.getUserById(id);
		if (user == null) {
			throw new EntityNotFoundException("No base found with ID: " + id);
		}
		return user;
	}

	public User fetchUserByName(String name) {
		User user = userDao.getUserByName(name);
		if (user == null) {
			throw new EntityNotFoundException("No allergen found with Name: " + name);
		}
		return user;
	}

	public void addUser(UserDto userDto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			User user = new User(userDto);

			entityManager.persist(user);

			entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the user", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while adding the user", e);
		}
	}

	public void deleteUser(int id) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		User user = entityManager.find(User.class, id);

		entityManager.remove(user);

		entityManager.getTransaction().commit();
	}

	public void updateUser(UserDto user) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			User userToUpdate = entityManager.find(User.class, user.getUserId());

			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());

			entityManager.persist(userToUpdate);

			entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while updating the user", e.getCause().getCause());

		}
	}
}
