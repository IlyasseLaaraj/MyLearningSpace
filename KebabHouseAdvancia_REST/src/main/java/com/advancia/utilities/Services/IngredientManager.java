package com.advancia.utilities.Services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import com.advancia.daos.IngredientDao;
import com.advancia.exceptions.InvalidAddException;
import com.advancia.models.dto.kebabComponents.IngredientDto;
import com.advancia.models.kebabComponents.Allergen;
import com.advancia.models.kebabComponents.Ingredient;

public class IngredientManager {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	IngredientDao ingrDao = new IngredientDao();

	public List<IngredientDto> fetchAllIngredients(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<IngredientDto> listOfIngredients = ingrDao.getAllIngredients(entityManager).stream().map(IngredientDto::new).collect(Collectors.toList());

		return listOfIngredients;
	}
	
	public Ingredient fetchIngredientById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingredient ingredient = ingrDao.getIngredientByIngredientId(entityManager, id);
		if (ingredient == null) {
	        throw new EntityNotFoundException("No allergen found with ID: " + id);
	    }
		return ingredient;
	}

	public Ingredient fetchIngredientByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		Ingredient ingredient = ingrDao.getIngredientByName(entityManager, name);
		
		return ingredient;
	}
	
	public void addIngredient(IngredientDto ingredientDto) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
		entityManager.getTransaction().begin();

		Ingredient ingredient = new Ingredient(ingredientDto);
		
		entityManager.persist(ingredient);
		
		entityManager.getTransaction().commit();
		} catch (RollbackException e) {

			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the ingredient", e.getCause().getCause());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("Unexpected error while adding the ingredient", e);
		}
	}
	
	public void deleteIngredient(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
	
		Ingredient ingredient = entityManager.find(Ingredient.class, id);
		
		entityManager.remove(ingredient);
		
		entityManager.getTransaction().commit();
	}
	
	public void updateIngredient(IngredientDto ingredient) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		try {
		entityManager.getTransaction().begin();
		
		Ingredient ingredientToUpdate = entityManager.find(Ingredient.class, ingredient.getIngredientId());
		
		ingredientToUpdate.setIngredientName(ingredient.getIngredientName());
		ingredientToUpdate.setIngredientDescription(ingredient.getIngredientDescription());
		ingredientToUpdate.setAllergens(ingredient.getAllergens().stream().map(Allergen::new).collect(Collectors.toList()));
		
		entityManager.persist(ingredientToUpdate);
		
		entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			entityManager.getTransaction().rollback();
			throw new InvalidAddException("Unexpected error while adding the ingredient", e.getCause().getCause());
		}
	}
}
