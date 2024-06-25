package com.advancia.utilities;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import com.advancia.daos.AllergenDao;
import com.advancia.daos.BaseDao;
import com.advancia.daos.IngredientDao;
import com.advancia.daos.KebabDao;
import com.advancia.daos.MeatDao;
import com.advancia.daos.SauceDao;
import com.advancia.models.AddKebabRequest;
import com.advancia.models.Kebab;
import com.advancia.models.KebabData;

public class KebabBuilder {

	private static final KebabDao kebabDao = new KebabDao();
	private static final IngredientDao ingrDao = new IngredientDao();
	private static final SauceDao saucesDao = new SauceDao();
	private static final BaseDao basesDao = new BaseDao();
	private static final MeatDao meatsDao = new MeatDao();
	private static final AllergenDao allergensDao = new AllergenDao();
	private static final IngredientDao ingredientsDao = new IngredientDao();
	
	public List<Kebab> getKebabsByUserId(Connection connection, int userId){
		List<Kebab> userKebabs = kebabDao.getPartialUserKebabs(connection, userId);
		return userKebabs.stream().map(kebab -> enrichKebab(connection, kebab)).collect(Collectors.toList());
	}
	
	public Kebab enrichKebab(Connection connection, Kebab kebab) {
		int kebabId = kebab.getKebabId();
		return new Kebab().setBase(basesDao.getBaseByKebabId(connection, kebabId))
				.setMeat(meatsDao.getMeatByKebabId(connection, kebabId))
				.setAllergen(allergensDao.getAllergenByKebabId(connection, kebabId))
				.setSauces(saucesDao.getSaucesByKebabId(connection, kebabId))
				.setIngredients(ingredientsDao.getKebabIngredientsByKebabId(connection, kebabId))
				.setKebabId(kebabId)
				.setKebabName(kebab.getKebabName());
		
	}
	
	public KebabData getKebabDataFromRequest(AddKebabRequest request, int kebabId, Connection connection) {
		List<Integer> ingredientsIds = request.getIngredients().stream()
				.map(ingre -> ingrDao.getIngredientByName(connection, ingre)).map(ingr -> ingr.getIngredientId())
				.collect(Collectors.toList());
		List<Integer> saucesIds = request.getSauces().stream()
				.map(sauce -> saucesDao.getSauceBySauceName(connection, sauce)).map(sauce -> sauce.getSauceId())
				.collect(Collectors.toList());
		Integer meatId = meatsDao.getMeatByMeatName(connection, request.getMeat()).getMeatId();
		Integer baseId = basesDao.getBaseByBaseName(connection, request.getBase()).getBaseId();
		return new KebabData()
				.setKebabId(kebabId)
				.setBase(baseId)
				.setMeat(meatId)
				.setSauces(saucesIds)
				.setIngredients(ingredientsIds);
	}
	
}
