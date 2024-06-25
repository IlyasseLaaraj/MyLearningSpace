package com.advancia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.daos.config.ConnectionFactory;
import com.advancia.models.kebabComponents.Sauce;

public class SauceDao {

	private static final String ADD_SAUCE_FOR_KEBAB_ID = "INSERT INTO CUSTOM_KEBAB_SAUCE VALUES (CUSTOM_KEBAB_SAUCE_TRG.nextval, ?, ?)";
	private static final String GET_ALL_SAUCES = "Select sauce_id, sauce_name, sauce_description FROM SAUCES";
	private static final String GET_SAUCE_BY_SAUCE_ID = "SELECT SAUCE_NAME, SAUCE_DESCRIPTION FROM SAUCES WHERE SAUCE_ID = ?";
	private static final String GET_SAUCE_BY_SAUCE_NAME = "SELECT sauce_id, SAUCE_NAME, SAUCE_DESCRIPTION FROM SAUCES WHERE lower(SAUCE_NAME) = lower(?)";
	private static final String GET_SAUCES_BY_KEBAB_ID = "SELECT SAUCES.sauce_id, SAUCES.sauce_name, SAUCES.sauce_description FROM CUSTOM_KEBAB_SAUCE JOIN SAUCES ON CUSTOM_KEBAB_SAUCE.SAUCE_ID = sauceS.sauce_ID WHERE CUSTOM_KEBAB_sauce.CUSTOM_KEBAB_ID = ?";
	private static final String DELETE_SAUCES_FOR_KEBAB_ID = "DELETE FROM CUSTOM_KEBAB_SAUCE WHERE CUSTOM_KEBAB_ID = ?";
	private static final Logger log = LoggerFactory.getLogger(SauceDao.class);

	public void deleteUserKebabSauces(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to delete sauces for kebab: {}", kebabId);
		try {
			ps = connection.prepareStatement(DELETE_SAUCES_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			int dropCount = ps.executeUpdate();
			log.info("Removed {} sauces interconnection", dropCount);
		} catch (SQLException e) {
			throw new RuntimeException("Error while deleting the sauces for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}

	public void addSauceForKebabId(Connection connection, int sauceId, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to add sauceId {} for kebabId: {}", sauceId, kebabId);
		try {
			ps = connection.prepareStatement(ADD_SAUCE_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			ps.setInt(2, sauceId);
			if (ps.executeUpdate() == 1) {
				log.info("SauceId {} correctly added for kebabId: {}", sauceId, kebabId);
				return;
			}
			throw new RuntimeException("Error while adding sauceId " + sauceId + " to kebabId " + kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while adding sauceId " + sauceId + " to kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}

	public List<Sauce> getAllSauces(Connection connection) {
		List<Sauce> saucesList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get all sauces");
		try {
			ps = connection.prepareStatement(GET_ALL_SAUCES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sauce sauce = extractSauce(rs);
				saucesList.add(sauce);
			}
			log.info("Retrieved {} sauces", saucesList.size());
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting all sauces", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
		return saucesList;
	}

	public List<Sauce> getSaucesByKebabId(Connection connection, int kebabId) {
		List<Sauce> saucesList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get sauces for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(GET_SAUCES_BY_KEBAB_ID);
			ps.setInt(1, kebabId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sauce sauce = extractSauce(rs);
				saucesList.add(sauce);
			}
			log.info("Retrieved {} sauces for kebabId {}", saucesList.size(), kebabId);
			return saucesList;
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting sauces for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Sauce getSauceBySauceId(Connection connection, int sauceId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get sauce for sauceId {}", sauceId);
		try {
			ps = connection.prepareStatement(GET_SAUCE_BY_SAUCE_ID);
			ps.setInt(1, sauceId);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Sauce found for sauceId {}", sauceId);
				return extractSauce(rs);
			}
			throw new RuntimeException("Sauce for id " + sauceId + " not found!");
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting sauce by id ", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Sauce getSauceBySauceName(Connection connection, String sauceName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get sauce for sauceName {}", sauceName);
		try {
			ps = connection.prepareStatement(GET_SAUCE_BY_SAUCE_NAME);
			ps.setString(1, sauceName);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Sauce found for sauceName {}", sauceName);
				return extractSauce(rs);
			}
			throw new RuntimeException("Sauce for name " + sauceName + " not found!");
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the sauce by name ", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public List<Sauce> getSaucesByNames(Connection connection, List<String> names) {
		return names.stream().map(name -> getSauceBySauceName(connection, name)).collect(Collectors.toList());
	}

	private Sauce extractSauce(ResultSet rs) throws SQLException {
		String sauceName = rs.getString("SAUCE_NAME");
		String sauceDescription = rs.getString("SAUCE_DESCRIPTION");
		int sauceId = rs.getInt("sauce_ID");
		return new Sauce(sauceName, sauceDescription, sauceId);
	}

}
