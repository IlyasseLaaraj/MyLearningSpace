package com.advancia.utilities.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.IngredientDao;
import com.advancia.models.kebabComponents.Ingredient;

public class IngredientManager {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	IngredientDao ingrDao = new IngredientDao();

	public List<Ingredient> fetchAllIngredients(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Ingredient> listOfIngredients = ingrDao.getAllIngredients(entityManager);

		entityManager.getTransaction().commit();
		return listOfIngredients;
	}
	
	public Ingredient fetchIngredientById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingredient ingredient = ingrDao.getIngredientByIngredientId(entityManager, id);
		
		entityManager.getTransaction().commit();
		return ingredient;
	}

	public Ingredient fetchIngredientByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingredient ingredient = ingrDao.getIngredientByName(entityManager, name);
		
		entityManager.getTransaction().commit();
		return ingredient;
	}
	
	public void addIngredient(Ingredient ingredient) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(ingredient);
		
		entityManager.getTransaction().commit();
	}
	
	public void deleteIngredient(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		Ingredient ingredient = entityManager.find(Ingredient.class, id);
		
		entityManager.remove(ingredient);
		
		entityManager.getTransaction().commit();
	}
	
	public void updateIngredient(Ingredient ingredient) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingredient ingredientToUpdate = entityManager.find(Ingredient.class, ingredient.getIngredientId());
		
		ingredientToUpdate.setIngredientName(ingredient.getIngredientName());
		ingredientToUpdate.setIngredientDescription(ingredient.getIngredientDescription());
		ingredientToUpdate.setAllergens(ingredient.getAllergens());
		
		entityManager.persist(ingredientToUpdate);
		
		entityManager.getTransaction().commit();
	}
}
