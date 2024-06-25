package com.advancia.utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.advancia.daos.BaseDao;
import com.advancia.daos.IngredientDao;
import com.advancia.daos.MeatDao;
import com.advancia.daos.SauceDao;
import com.advancia.daos.KebabDao;
import com.advancia.daos.config.ConnectionFactory;
import com.advancia.models.AddKebabRequest;
import com.advancia.models.DeleteKebabRequest;
import com.advancia.models.Kebab;
import com.advancia.models.KebabData;
import com.advancia.models.PageData;
import com.advancia.models.UpdateKebabRequest;
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
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	private static final KebabBuilder kebabBuilder = new KebabBuilder();
	
	public void addKebab(AddKebabRequest request) {
		Connection connection = connectionFactory.getConnection();
		addKebabInTransaction(request, connection);
		connectionFactory.closeConnection(connection);
	}

	private void addKebabInTransaction(AddKebabRequest request, Connection connection) {
		int kebabId = kebabDao.getNextKebabId(connection);
		KebabData kebabData = kebabBuilder.getKebabDataFromRequest(request, kebabId, connection);
		try {
			connection.setAutoCommit(false);
			createKebab(connection, request.getName(), request.getUserId(), kebabData);
			ConnectionFactory.commitTransaction(connection);
		} catch (SQLException e) {
			ConnectionFactory.rollbackTransaction(connection);
			throw new RuntimeException("Error while executing transaction", e);
		}
	}

	private void createKebab(Connection connection, String name, int userId, KebabData kebabData) {
		kebabDao.addKebab(connection, kebabData.getKebabId(), name);
		kebabDao.addKebabForUserId(connection,  kebabData.getKebabId(), userId);
		meatsDao.addMeatForKebabId(connection, kebabData.getMeat(),  kebabData.getKebabId());
		basesDao.addBaseForKebabId(connection, kebabData.getBase(),  kebabData.getKebabId());
		kebabData.getSauces().forEach(sauceId -> saucesDao.addSauceForKebabId(connection, sauceId, kebabData.getKebabId()));
		kebabData.getIngredients().forEach(ingredientId -> ingrDao.addIngredientForKebabId(connection, ingredientId, kebabData.getKebabId()));
	}

	public boolean updateKebabInTransaction(UpdateKebabRequest request) {
		Connection connection = connectionFactory.getConnection();
		KebabData kebabData = kebabBuilder.getKebabDataFromRequest(request, request.getKebabId(), connection);
		try {
			connection.setAutoCommit(false);
			deleteKebab(connection, request.getKebabId(), request.getUserId());
			createKebab(connection, request.getName(), request.getUserId(), kebabData);
			ConnectionFactory.commitTransaction(connection);
		} catch (SQLException e) {
			ConnectionFactory.rollbackTransaction(connection);
			throw new RuntimeException("Error while executing transaction", e);
		}
		connectionFactory.closeConnection(connection);
		return true;
	}

	public void deleteKebabInTransaction(DeleteKebabRequest request) {
		Connection connection = connectionFactory.getConnection();
		try {
			connection.setAutoCommit(false);
			deleteKebab(connection, request.getKebabId(), request.getUserId());
			ConnectionFactory.commitTransaction(connection);
		} catch (SQLException e) {
			ConnectionFactory.rollbackTransaction(connection);
			throw new RuntimeException("Error while executing transaction", e);
		}
		connectionFactory.closeConnection(connection);
	}

	private void deleteKebab(Connection connection, int kebabId, int userId) {
		ingrDao.deleteIngredientsForKebabId(connection, kebabId);
		saucesDao.deleteUserKebabSauces(connection, kebabId);
		basesDao.deleteUserKebabBase(connection, kebabId);
		meatsDao.deleteUserKebabMeat(connection, kebabId);
		kebabDao.deleteKebabForUserId(connection, kebabId, userId);
		kebabDao.deleteKebabByKebabId(connection, kebabId);
	}

	public PageData getPageData(int userId) {
		Connection connection = connectionFactory.getConnection();
		List<Kebab> userKebabs = kebabBuilder.getKebabsByUserId(connection, userId);
		List<Ingredient> ingredientsList = ingrDao.getAllIngredients(connection);
		List<Sauce> saucesList = saucesDao.getAllSauces(connection);
		List<Base> basesList = basesDao.getAllBases(connection);
		List<Meat> meatsList = meatsDao.getAllMeats(connection);
		connectionFactory.closeConnection(connection);
		return new PageData().setUserKebabs(userKebabs).setIngredientsList(ingredientsList).setSaucesList(saucesList)
				.setBasesList(basesList).setMeatsList(meatsList).setBasesList(basesList);
	}
}