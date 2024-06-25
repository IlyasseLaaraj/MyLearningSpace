package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.models.Kebab;
import com.advancia.models.kebabComponents.Allergen;

public class AllergenDao {

	private static final Logger log = LoggerFactory.getLogger(AllergenDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	
	public List<Allergen> getAllergenByKebabId(int kebabId) {
		
		List<Allergen> kebabAllergensList = new ArrayList<Allergen>();

		log.info("Received new request to retrieve allergens for kebabId {}", kebabId);
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			
			Kebab kebab = entityManager.find(Kebab.class, kebabId);

			List<Allergen> baseAllergens = extractBasesAllergens(kebab);
			List<Allergen> meatAllergens = extractMeatsAllergens(kebab);
			List<Allergen> IngredientsAllergens = extractIngredientsAllergens(kebab);
			List<Allergen> SaucesAllergens = extractSaucesAllergens(kebab);

			Stream.of(baseAllergens, meatAllergens, IngredientsAllergens, SaucesAllergens)
					.forEach(kebabAllergensList::addAll);
			
			log.info("Retrieved {} allergens", kebabAllergensList.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting all allergens for kebabId " + kebabId, e);
		}
		return kebabAllergensList;

	}

	private List<Allergen> extractBasesAllergens(Kebab kebab) {
		List<Allergen> basesAllergen = new ArrayList<Allergen>();

		basesAllergen = kebab.getBase().getAllergens();

		return basesAllergen;
	}

	private List<Allergen> extractMeatsAllergens(Kebab kebab) {
		List<Allergen> meatsAllergen = new ArrayList<Allergen>();

		meatsAllergen = kebab.getMeat().getAllergens();

		return meatsAllergen;
	}

	private List<Allergen> extractSaucesAllergens(Kebab kebab) {
		List<Allergen> saucesAllergen = new ArrayList<Allergen>();

		kebab.getSauces().stream().map(sauce -> sauce.getAllergens()).forEach(saucesAllergen::addAll);

		return saucesAllergen;
	}

	private List<Allergen> extractIngredientsAllergens(Kebab kebab) {
		List<Allergen> ingredientsAllergen = new ArrayList<Allergen>();

		kebab.getIngredients().stream().map(ingredient -> ingredient.getAllergens())
				.forEach(ingredientsAllergen::addAll);
		return ingredientsAllergen;
	}

	public List<Allergen> getAllAllergens(EntityManager entityManager) {
		List<Allergen> allergensList = new ArrayList<Allergen>();
		log.info("Received new request to get all allergens");
		try {

			allergensList = entityManager.createNamedQuery("getAllAllergens", Allergen.class).getResultList();

			log.info("Retrieved {} allergens", allergensList.size());

		} catch (Exception e) {
			throw new RuntimeException("Error while getting all allergens", e);
		}
		return allergensList;
	}
	
	public Allergen getAllergenById(int id) {

		Allergen allergen = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();

			allergen = entityManager.find(Allergen.class, id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting the allergen for allergenId " + id, e);
		}
		return allergen;
	}
	
	public Allergen getAllergenByName(String allergenName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();

		Allergen allergen = (Allergen) entityManager.createNamedQuery("getAllergenByName").setParameter("allergenName", allergenName)
				.getSingleResult();
		
		return allergen;
	}
	
}