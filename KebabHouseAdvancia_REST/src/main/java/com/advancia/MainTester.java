package com.advancia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.UserDao;
import com.advancia.models.User;
import com.advancia.utilities.Services.UserManager;

public class MainTester {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		
		UserDao userDao = new UserDao();
		userDao.getAllUsers(entityManager).forEach(user -> System.out.println(user));
		
		UserManager userManager = new UserManager();
		User user = userManager.fetchUserById(1);	
		System.out.println("By id: " +user.getCustomKebabs());
		
	}

}