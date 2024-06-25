package com.advancia.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.daos.config.HibernateSessionFactory;
import com.advancia.models.Kebab;
import com.advancia.models.kebabComponents.Allergen;

public class AllergenDao {

	private static final Logger log = LoggerFactory.getLogger(AllergenDao.class);

	public List<Allergen> getAllergenByKebabId(int kebabId) {
		SessionFactory sessionFactory = null;
		
		List<Allergen> kebabAllergensList = new ArrayList<Allergen>();

		log.info("Received new request to retrieve allergens for kebabId {}", kebabId);
		try {
			sessionFactory = HibernateSessionFactory.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			Kebab kebab = session.get(Kebab.class, kebabId);

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
		}finally {
			if (sessionFactory != null && !sessionFactory.isClosed())
				sessionFactory.close();
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

}
