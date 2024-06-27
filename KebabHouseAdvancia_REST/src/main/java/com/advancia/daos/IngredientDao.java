package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.Kebab;
import com.advancia.models.kebabComponents.Ingredient;

public class IngredientDao {

	
	private static final Logger log = LoggerFactory.getLogger(IngredientDao.class);

	public void deleteIngredientsForKebabId(EntityManager entityManager, int kebabId) {

		log.info("Received new request to delete ingredients for kebabId {}", kebabId);
		try {

			Kebab kebab = entityManager.find(Kebab.class, kebabId);

			kebab.getIngredients().clear();

			if (kebab.getIngredients().size() == 0) {
				log.info("sauces removed successfully for kebabId {}", kebabId);
			}

			entityManager.persist(kebab);

			log.info("Removed {} ingredients interconnection for kebabId {}", kebabId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting the ingredients of the kebab ", e);
		}
	}


	public List<Ingredient> getAllIngredients(EntityManager entityManager) {

		List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
		log.info("Received new request to get all ingredients");
		try {
			
			ingredientsList = entityManager.createNamedQuery("getAllIngredients", Ingredient.class).getResultList();

			log.info("Retrieved {} ingredients", ingredientsList.size());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting all ingredients", e);
		}
		return ingredientsList;
	}

	public Ingredient getIngredientByIngredientId(EntityManager entityManager, int ingredientId) {
		Ingredient ingredient = null;
		log.info("Received new request to get ingredient for ingredientId {}", ingredientId);
		try {
			ingredient = entityManager.find(Ingredient.class, ingredientId);

			log.info("Ingredient found for ingredientId {}", ingredientId);

		} catch (Exception e) {
			throw new RuntimeException("Error while getting the ingredient for ingredientId " + ingredientId, e);

		}
		return ingredient;
	}

	public Ingredient getIngredientByName(EntityManager entityManager, String ingredientName) {
		Ingredient ingredient = null;
		log.info("Received new request to get ingredient for ingredientName {}", ingredientName);

		ingredient = (Ingredient)  entityManager.createNamedQuery("getIngredientByName").setParameter("ingredientName", ingredientName).getSingleResult();


		System.out.println("Before committing transaction");

		return ingredient;
	}
}
