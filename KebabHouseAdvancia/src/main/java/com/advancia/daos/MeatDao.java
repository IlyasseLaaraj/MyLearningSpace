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
import com.advancia.models.kebabComponents.Meat;

public class MeatDao {
	private static final String ADD_MEAT_FOR_KEBAB_ID = "INSERT INTO CUSTOM_KEBAB_MEAT VALUES (CUSTOM_KEBAB_MEAT_TRG.nextval, ?, ?)";
	private static final String GET_ALL_MEATS = "SELECT MEAT_ID, MEAT_NAME, MEAT_DESCRIPTION FROM MEATS";
	private static final String GET_MEAT_BY_MEAT_ID = "SELECT MEAT_NAME, MEAT_DESCRIPTION FROM MEATS WHERE MEAT_ID = ?";
	private static final String GET_MEAT_BY_MEAT_NAME = "SELECT meat_id, MEAT_NAME, MEAT_DESCRIPTION FROM MEATS WHERE lower(MEAT_NAME) = lower(?)";
	private static final String GET_MEAT_BY_KEBAB_ID = "SELECT MEATS.meat_id, MEATS.meat_name, MEATS.meat_description FROM CUSTOM_KEBAB_MEAT JOIN MEATS ON CUSTOM_KEBAB_MEAT.MEAT_ID = MEATS.MEAT_ID WHERE CUSTOM_KEBAB_MEAT.CUSTOM_KEBAB_ID = ?";
	private static final String DELETE_MEATS_FOR_KEBAB_ID = "DELETE FROM CUSTOM_KEBAB_MEAT WHERE custom_kebab_id = ?";
	private static final Logger log = LoggerFactory.getLogger(MeatDao.class);

	public void deleteUserKebabMeat(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to delete meats for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(DELETE_MEATS_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			int dropCount = ps.executeUpdate();
			log.info("Removed {} meats interconnection for kebabId {}", dropCount, kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while deleting the meat of the kebab ", e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}

	public void addMeatForKebabId(Connection connection, int meatId, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to add meatId {} for kebabId: {}", meatId, kebabId);
		try {
			ps = connection.prepareStatement(ADD_MEAT_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			ps.setInt(2, meatId);
			if( ps.executeUpdate()==1 ) {
				log.info("MeatId {} correctly added for kebabId: {}", meatId, kebabId);
				return;
			}
			throw new RuntimeException("Error while adding meatId " + meatId +" to kebabId " + kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while adding meatId " + meatId +" to kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public List<Meat> getAllMeats(Connection connection) {
		List<Meat> meatsList = new ArrayList<Meat>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get all meats");
		try {
			ps = connection.prepareStatement(GET_ALL_MEATS);
			rs = ps.executeQuery();
			while (rs.next()) {
				Meat meat = extractMeat(rs);
				meatsList.add(meat);
			}
			log.info("Retrieved {} meats", meatsList.size());
			return meatsList;
		} catch (SQLException e) {
			throw new RuntimeException("Error while deleting the meatsList ", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Meat getMeatByKebabId(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get sauces for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(GET_MEAT_BY_KEBAB_ID);
			ps.setInt(1, kebabId);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Meat found for kebabId {}", kebabId);
				return extractMeat(rs);
			}
			throw new RuntimeException("Error while getting the meat for kebabId " + kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the meat for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Meat getMeatByMeatId(Connection connection, int meatId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(GET_MEAT_BY_MEAT_ID);
			ps.setInt(1, meatId);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Meat found for meatId {}", meatId);
				return extractMeat(rs);
			}
			throw new RuntimeException("Error while getting the meat for meatId " + meatId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the meat for meatId " + meatId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Meat getMeatByMeatName(Connection connection, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(GET_MEAT_BY_MEAT_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Meat found for meatName {}", name);
				return extractMeat(rs);
			}
			throw new RuntimeException("Error while getting the meat for meatName " + name);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the meat for meatName " + name, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	private Meat extractMeat(ResultSet rs) throws SQLException {
		String meatName = rs.getString("MEAT_NAME");
		String meatDescription = rs.getString("MEAT_DESCRIPTION");
		int meatId = rs.getInt("meat_id");
		Meat meat = new Meat(meatName, meatDescription, meatId);
		return meat;
	}
}
