package com.advancia.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.advancia.daos.AddKebabRequest;
import com.advancia.daos.BaseDao;
import com.advancia.daos.IngredientDao;
import com.advancia.daos.KebabDao;
import com.advancia.daos.MeatDao;
import com.advancia.daos.SauceDao;
import com.advancia.models.Kebab;
import com.advancia.models.PageData;
import com.advancia.models.UpdateKebabRequest;
import com.advancia.models.User;
import com.advancia.models.kebabComponents.Base;
import com.advancia.models.kebabComponents.Ingredient;
import com.advancia.models.kebabComponents.Meat;
import com.advancia.models.kebabComponents.Sauce;

public class KebabUtilities {

	private static final KebabDao kebabDao = new KebabDao();
	private static final IngredientDao ingrDao = new IngredientDao();
	private static final SauceDao saucesDao = new SauceDao();
	private static final BaseDao basesDao = new BaseDao();
	private static final MeatDao meatsDao = new MeatDao();
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";


	public static PageData getPageData(EntityManager entityManager, int userId) {
		List<Kebab> userKebabs = kebabDao.getUserKebabs(entityManager, userId);
		List<Ingredient> ingredientsList = ingrDao.getAllIngredients(entityManager);
		List<Sauce> saucesList = saucesDao.getAllSauces(entityManager);
		List<Base> basesList = basesDao.getAllBases(entityManager);
		List<Meat> meatsList = meatsDao.getAllMeats(entityManager);
		return new PageData().setUserKebabs(userKebabs).setIngredientsList(ingredientsList).setSaucesList(saucesList)
				.setBasesList(basesList).setMeatsList(meatsList).setBasesList(basesList);
	}

	public static void updateKebab( UpdateKebabRequest updatedKebab) {
		Kebab kebab = convertKebabReqToKebab(updatedKebab).setKebabId(updatedKebab.getKebabId());
		kebabDao.updateKebab(kebab);
	}

	public static void deleteKebab(int kebabId) {
		kebabDao.deleteKebabByKebabId(kebabId);
	}

	public static Kebab convertKebabReqToKebab(AddKebabRequest addKebabRequest) {
		Kebab kebab = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			EntityManager entityManager = emf.createEntityManager();
			entityManager.getTransaction().begin();
			String kebabName = addKebabRequest.getName();
			Base kebabBase = basesDao.getBaseByName(addKebabRequest.getBase());
			Meat kebabMeat = meatsDao.getMeatByName(addKebabRequest.getMeat());
			List<Ingredient> kebabIngredients = new ArrayList<Ingredient>();
			List<String> ingredientsNames = addKebabRequest.getIngredients();
			ingredientsNames.forEach(ingredientName -> {
				Ingredient ingredient = ingrDao.getIngredientByName(entityManager, ingredientName);
				kebabIngredients.add(ingredient);
			});
			List<Sauce> kebabSauces = new ArrayList<Sauce>();
			List<String> saucesNames = addKebabRequest.getSauces();
			saucesNames.forEach(sauceName -> {
				Sauce sauce = saucesDao.getSauceBySauceName(sauceName);
				kebabSauces.add(sauce);
			});
			User user = entityManager.find(User.class, addKebabRequest.getUserId());

			kebab = new Kebab(kebabName, kebabBase, kebabMeat, kebabIngredients, kebabSauces, user);
			System.out.println("Before committing transaction");
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while adding kebab " + kebab.getName(), e);
		}
		return kebab;
	}

}