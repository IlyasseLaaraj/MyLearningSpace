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
import com.advancia.models.Allergen;

public class AllergenDao {

	private static final String GET_ALLERGENS_BY_KEBAB_ID = "SELECT allergens.allergen_id as allergenId, allergens.allergen_name as allergenName, allergens.allergen_description as allergenDescription\r\n"
			+ "FROM kebabs\r\n"
			+ "JOIN base_kebab on kebabs.kebab_id = base_kebab.base_kebab_id\r\n"
			+ "JOIN bases on bases.base_id = base_kebab.base_id\r\n"
			+ "JOIN allergen_base on bases.base_id = allergen_base.base_id\r\n"
			+ "JOIN allergens on allergens.allergen_id = allergen_base.allergen_id\r\n"
			+ "WHERE kebabs.kebab_id = ?\r\n"
			+ "UNION\r\n"
			+ "SELECT allergens.allergen_id as allergenId, allergens.allergen_name as allergenName, allergens.allergen_description as allergenDescription\r\n"
			+ "FROM kebabs\r\n"
			+ "JOIN ingredient_kebab on kebabs.kebab_id = ingredient_kebab.kebab_id\r\n"
			+ "JOIN ingredients on ingredients.ingredient_id = ingredient_kebab.ingredient_id\r\n"
			+ "JOIN allergen_ingredient on ingredients.ingredient_id = allergen_ingredient.ingredient_id\r\n"
			+ "JOIN allergens on allergens.allergen_id = allergen_ingredient.allergen_id\r\n"
			+ "WHERE kebabs.kebab_id = ?\r\n"
			+ "UNION\r\n"
			+ "SELECT allergens.allergen_id as allergenId, allergens.allergen_name as allergenName, allergens.allergen_description as allergenDescription\r\n"
			+ "FROM kebabs\r\n"
			+ "JOIN meat_kebab on meat_kebab.kebab_id = kebabs.kebab_id\r\n"
			+ "JOIN meats on meats.meat_id = meat_kebab.meat_id\r\n"
			+ "JOIN allergen_meat on allergen_meat.meat_id = meats.meat_id\r\n"
			+ "JOIN allergens on allergens.allergen_id = allergen_meat.allergen_id\r\n"
			+ "WHERE kebabs.kebab_id = ?\r\n"
			+ "UNION\r\n"
			+ "SELECT allergens.allergen_id as allergenId, allergens.allergen_name as allergenName, allergens.allergen_description as allergenDescription\r\n"
			+ "FROM kebabs\r\n"
			+ "JOIN sauce_kebab on kebabs.kebab_id = sauce_kebab.kebab_id\r\n"
			+ "JOIN sauces on sauces.sauce_id = sauce_kebab.sauce_id\r\n"
			+ "JOIN allergen_sauce on allergen_sauce.sauce_id = sauces.sauce_id\r\n"
			+ "JOIN allergens on allergens.allergen_id = allergen_sauce.allergen_id\r\n"
			+ "WHERE kebabs.kebab_id = ?";

	private static final Logger log = LoggerFactory.getLogger(AllergenDao.class); 
	
	public List<Allergen> getAllergenByKebabId(Connection connection, int kebabId) {
		List<Allergen> kebabAllergensList = new ArrayList<Allergen>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to retrieve allergens for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(GET_ALLERGENS_BY_KEBAB_ID);
			ps.setInt(1, kebabId);
			ps.setInt(2, kebabId);
			ps.setInt(3, kebabId);
			ps.setInt(4, kebabId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Allergen allergen = extractAllergen(rs);
				kebabAllergensList.add(allergen);
			}
			log.info("Retrieved {} allergens", kebabAllergensList.size());
			return kebabAllergensList;
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting all allergens for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	private Allergen extractAllergen(ResultSet rs) throws SQLException {
		String allergenName = rs.getString("allergenName");
		String allergenDescription = rs.getString("allergenDescription");
		int allergenId = rs.getInt("allergenId");
		return new Allergen(allergenName, allergenDescription, allergenId);
	}
	
}
