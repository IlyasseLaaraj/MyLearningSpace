package com.advancia.utilities.Services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.UserDao;
import com.advancia.models.User;
import com.advancia.models.dto.UserDto;

public class UserManager {

	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	UserDao userDao = new UserDao();
	
	public List<UserDto> fetchAllUsers(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		List<UserDto> listOfUsers = userDao.getAllUsers(entityManager).stream().map(UserDto::new).collect(Collectors.toList());

		entityManager.getTransaction().commit();
		return listOfUsers;
	}
	
	public User fetchUserById(int id) {

		User user = userDao.getUserById(id);

		return user;
	}

	public User fetchUserByName(String name) {

		User user = userDao.getUserByName(name);

		return user;
	}
	
	
	public void addUser(User user) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
	}
	
	public void deleteUser(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		User user = entityManager.find(User.class, id);
		
		entityManager.remove(user);
		
		entityManager.getTransaction().commit();
	}
	
	public void updateUser(User user) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		User userToUpdate = entityManager.find(User.class, user.getUserId());
		
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setPassword(user.getPassword());
		
		entityManager.persist(userToUpdate);
		
		entityManager.getTransaction().commit();
	}
}
