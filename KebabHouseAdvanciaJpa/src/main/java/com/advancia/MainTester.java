package com.advancia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.BaseDao;
import com.advancia.models.Kebab;

public class MainTester {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		
		entityManager.getTransaction().commit();	
	}

}