package com.advancia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.daos.config.ConnectionFactory;
import com.advancia.models.kebabComponents.Ingredient;

public class IngredientDao {
	
	private static final String ADD_INGREDIENT_FOR_KEBAB_ID = "INSERT INTO CUSTOM_KEBAB_INGREDIENT VALUES (CUSTOM_KEBAB_INGREDIENT_TRG.nextval, ?, ?)";
	private static final String GET_ALL_INGREDIENTS = "Select ingredient_id, INGREDIENT_NAME, INGREDIENT_DESCRIPTION FROM INGREDIENTS";
	private static final String GET_INGREDIENT_BY_INGREDIENT_ID = "SELECT ingredient_name, ingredient_description FROM INGREDIENTS WHERE ingredient_id = ?";
	private static final String GET_INGREDIENT_BY_INGREDIENT_NAME = "SELECT ingredient_id, ingredient_name, ingredient_description FROM INGREDIENTS WHERE lower(ingredient_name) = lower(?)";
	private static final String GET_INGREDIENTS_BY_KEBAB_ID = "SELECT INGREDIENTS.ingredient_id, INGREDIENTS.ingredient_name, INGREDIENTS.ingredient_description FROM CUSTOM_KEBAB_INGREDIENT JOIN INGREDIENTS ON CUSTOM_KEBAB_INGREDIENT.ingredient_ID = ingredientS.ingredient_ID WHERE CUSTOM_KEBAB_ingredient.CUSTOM_KEBAB_ID = ?";
	private static final String DELETE_INGREDIENTS_FOR_KEBAB_ID = "DELETE FROM custom_kebab_ingredient where custom_kebab_id = ?";

	private static final Logger log = LoggerFactory.getLogger(IngredientDao.class);

	public void deleteIngredientsForKebabId(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to delete ingredients for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(DELETE_INGREDIENTS_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			int dropCount = ps.executeUpdate();
			log.info("Removed {} ingredients interconnection for kebabId {}", dropCount, kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while deleting the ingredients of the kebab ", e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}

	public void addIngredientForKebabId(Connection connection, int ingredientId, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to add ingredientId {} for kebabId: {}", ingredientId, kebabId);
		try {
			ps = connection.prepareStatement(ADD_INGREDIENT_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			ps.setInt(2, ingredientId);
			if( ps.executeUpdate()== 0) {
				throw new RuntimeException("Error while adding ingredientId " + ingredientId +" to kebabId " + kebabId);
			}
			log.info("IngredientId {} correctly added for kebabId: {}", ingredientId, kebabId);
			
		} catch (SQLException e) {
			throw new RuntimeException("Error while adding ingredientId " + ingredientId +" to kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public List<Ingredient> getAllIngredients(Connection connection) {
		List<Ingredient> ingreditentsList = new ArrayList<Ingredient>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get all ingredients");
		try {
			ps = connection.prepareStatement(GET_ALL_INGREDIENTS);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ingredient ingredient = extractIngredient(rs);
				ingreditentsList.add(ingredient);
			}
			log.info("Retrieved {} ingredients", ingreditentsList.size());
			return ingreditentsList;
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting all ingredients", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Ingredient getIngredientByIngredientId(Connection connection, int ingredientId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get ingredient for ingredientId {}", ingredientId);
		try {
			ps = connection.prepareStatement(GET_INGREDIENT_BY_INGREDIENT_ID);
			ps.setInt(1, ingredientId);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Ingredient found for ingredientId {}", ingredientId);
				return extractIngredient(rs);
			}
			throw new RuntimeException("Error while getting the ingredient for ingredientId " + ingredientId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the ingredient for ingredientId " + ingredientId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Ingredient getIngredientByName(Connection connection, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get ingredient for ingredientName {}", name);
		try {
			ps = connection.prepareStatement(GET_INGREDIENT_BY_INGREDIENT_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Ingredient found for ingredientName {}", name);
				return extractIngredient(rs);
			}
			throw new RuntimeException("Error while getting the ingredient for ingredientName " + name);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the ingredient for ingredientName " + name, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public List<Ingredient> getKebabIngredientsByKebabId(Connection connection, int kebabId) {
		List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(GET_INGREDIENTS_BY_KEBAB_ID);
			ps.setInt(1, kebabId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ingredient ingredient = extractIngredient(rs);
				ingredientList.add(ingredient);
			}
			log.info("Retrieved {} ingredients for kebabId {}", ingredientList.size());
			return ingredientList;
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the ingredients for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	private Ingredient extractIngredient(ResultSet rs) throws SQLException {
		String ingredientName = rs.getString("INGREDIENT_NAME");
		String ingredientDescription = rs.getString("INGREDIENT_DESCRIPTION");
		int ingredientId = rs.getInt("ingredient_id");
		Ingredient ingredient = new Ingredient(ingredientName, ingredientDescription, ingredientId);
		return ingredient;
	}
}
