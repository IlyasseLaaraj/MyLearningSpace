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
import com.advancia.models.kebabComponents.Base;

public class BaseDao {
	private static final String ADD_BASE_FOR_KEBAB_ID = "INSERT INTO CUSTOM_KEBAB_BASE VALUES (CUSTOM_KEBAB_BASE_TRG.nextval, ?, ?)";
	private static final String GET_ALL_BASES = "SELECT BASE_NAME, BASE_DESCRIPTION, BASE_ID FROM BASES";
	private static final String GET_BASE_BY_BASE_ID = "SELECT BASE_NAME, BASE_DESCRIPTION, BASE_ID FROM BASES WHERE BASE_ID = ?";
	private static final String GET_BASE_BY_KEBAB_ID = "SELECT BASES.base_id, bases.base_name, bases.base_description FROM CUSTOM_KEBAB_BASE JOIN BASES ON CUSTOM_KEBAB_BASE.BASE_ID = BASES.BASE_ID WHERE CUSTOM_KEBAB_BASE.CUSTOM_KEBAB_ID = ?";
	private static final String GET_BASE_BY_BASE_NAME = "SELECT bases.base_id, bases.base_name, bases.base_description from bases where lower(base_name) = lower(?)";
	private static final String DELETE_MEAT_FOR_KEBAB_ID = "DELETE FROM CUSTOM_KEBAB_BASE WHERE CUSTOM_KEBAB_ID = ?";

	private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

	public void deleteUserKebabBase(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to delete bases for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(DELETE_MEAT_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			int dropCount = ps.executeUpdate();
			log.info("Removed {} bases interconnection for kebabId {}", dropCount, kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while deleting bases for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}

	public void addBaseForKebabId(Connection connection, int baseId, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to add baseId {} for kebabId: {}", baseId, kebabId);
		try {
			ps = connection.prepareStatement(ADD_BASE_FOR_KEBAB_ID);
			ps.setInt(1, kebabId);
			ps.setInt(2, baseId);
			if( ps.executeUpdate()==1 ) {
				log.info("BaseId {} correctly added for kebabId: {}", baseId, kebabId);
				return;
			}
			throw new RuntimeException("Error while adding baseId " + baseId +" to kebabId " + kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while adding baseId " + baseId +" to kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public List<Base> getAllBases(Connection connection) {
		List<Base> basesList = new ArrayList<Base>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get all bases");
		try {
			ps = connection.prepareStatement(GET_ALL_BASES);
			rs = ps.executeQuery();
			while (rs.next()) {
				Base base = extractBase(rs);
				basesList.add(base);
			}
			log.info("Retrieved {} bases", basesList.size());
			return basesList;
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting all bases", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Base getBaseByBaseId(Connection connection, int baseId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get base for baseId {}", baseId);
		try {
			ps = connection.prepareStatement(GET_BASE_BY_BASE_ID);
			ps.setInt(1, baseId);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Base found for baseId {}", baseId);
				return extractBase(rs);
			}
			throw new RuntimeException("Error while getting the base for baseId " + baseId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the base for baseId " + baseId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Base getBaseByBaseName(Connection connection, String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get base for baseName {}", name);
		try {
			ps = connection.prepareStatement(GET_BASE_BY_BASE_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Base found for baseName {}", name);
				return extractBase( rs);
			}
			throw new RuntimeException("Error while getting the base for baseName " + name);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the base for baseName " + name, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public Base getBaseByKebabId(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Received new request to get base for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(GET_BASE_BY_KEBAB_ID);
			ps.setInt(1, kebabId);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Base found for kebabId {}", kebabId);
				return extractBase(rs);
			}
			throw new RuntimeException("Error while getting the base for kebabId " + kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while getting the base for kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	private Base extractBase(ResultSet rs) throws SQLException {
		String baseName = rs.getString("BASE_NAME").trim();
		String baseDescription = rs.getString("BASE_DESCRIPTION").trim();
		int baseId = rs.getInt("BASE_ID");
		return new Base(baseName, baseDescription, baseId);
	}
}
