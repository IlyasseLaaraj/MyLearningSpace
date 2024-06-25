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
import com.advancia.models.Kebab;

public class KebabDao {
	
	private static final String ADD_KEBAB = "insert into custom_kebabs values (?, ?)";
	private static final String ADD_KEBAB_FOR_USER_ID = "INSERT INTO CUSTOM_KEBAB_USER values(CUSTOM_KEBAB_USER_TRG.nextval,?,?)";
	private static final String GET_KEBABS_BY_USER_ID = "SELECT CUSTOM_KEBABS.CUSTOM_KEBAB_NAME, CUSTOM_KEBABS.CUSTOM_KEBAB_ID FROM CUSTOM_KEBAB_USER JOIN USERS ON CUSTOM_KEBAB_USER.USER_ID = USERS.USER_ID JOIN CUSTOM_KEBABS ON CUSTOM_KEBABS.CUSTOM_KEBAB_ID = CUSTOM_KEBAB_USER.CUSTOM_KEBAB_ID WHERE USERS.USER_ID = ?";
	private static final String GET_NEXT_KEBAB_ID = "SELECT COUNT(*) AS LAST_ID FROM CUSTOM_KEBABS";
	private static final String DELETE_KEBAB_FOR_USER_ID = "delete from custom_kebab_user where custom_kebab_id = ?";
	private static final String DELETE_KEBAB = "delete from custom_kebabs where custom_kebab_id = ?";
	
	private static final Logger log = LoggerFactory.getLogger(KebabDao.class); 
	
	public synchronized int getNextKebabId(Connection connection) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(GET_NEXT_KEBAB_ID);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("last_Id") + 1;
			}
			throw new RuntimeException("Query for next id returns null");
		} catch (SQLException e) {
			throw new RuntimeException("Unable to compute new id", e);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	public void addKebab(Connection connection, int kebabId, String kebabName) {
		PreparedStatement ps = null;
		log.info("Received new request to add new kebab with and name: {}", kebabName);
		try {
			ps = connection.prepareStatement(ADD_KEBAB);
			ps.setInt(1, kebabId);
			ps.setString(2, kebabName);
			if( ps.executeUpdate()==1 ) {
				log.info("Kebab {} correctly added", kebabName);
				return;
			}
			throw new RuntimeException("Error while adding kebab " + kebabName);
		} catch (SQLException e) {
			throw new RuntimeException("Error while adding kebab " + kebabName, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public void addKebabForUserId(Connection connection, int kebabId, int userId) {
		PreparedStatement ps = null;
		log.info("Received new request to add kebabId {} for userId: {}", kebabId, userId);
		try {
			ps = connection.prepareStatement(ADD_KEBAB_FOR_USER_ID);
			ps.setInt(1, userId);
			ps.setInt(2, kebabId);
			if( ps.executeUpdate()==1 ) {
				log.info("KebabId {} correctly added for userId: {}", kebabId, userId);
				return;
			}
			throw new RuntimeException("Error while adding kebabId " + kebabId +" to userId " + userId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while adding kebabId " + kebabId +" to userId " + userId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public void deleteKebabForUserId(Connection connection, int kebabId, int userId) {
		PreparedStatement ps = null;
		log.info("Received new request to delete bases for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(DELETE_KEBAB_FOR_USER_ID);
			ps.setInt(1, kebabId);
			int deleteCount = ps.executeUpdate();
			if(deleteCount==1) {
				log.info("Removed {} interconnection for userId {}", kebabId, userId);
				return;
			};
			throw new RuntimeException("Error while deleting kebabId " + kebabId + " for userId " + userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting kebabId " + kebabId + " for userId " + userId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public void deleteKebabByKebabId(Connection connection, int kebabId) {
		PreparedStatement ps = null;
		log.info("Received new request to delete bases for kebabId {}", kebabId);
		try {
			ps = connection.prepareStatement(DELETE_KEBAB);
			ps.setInt(1, kebabId);
			if(ps.executeUpdate()==1) {
				log.info("Removed kebab with kebabId {}", kebabId);
				return;
			};
			throw new RuntimeException("Error while deleting kebabId " + kebabId);
		} catch (SQLException e) {
			throw new RuntimeException("Error while deleting kebabId " + kebabId, e);
		} finally {
			ConnectionFactory.closeRequest(ps, null);
		}
	}
	
	public List<Kebab> getPartialUserKebabs(Connection connection, int userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Kebab> userCustomKebabList = new ArrayList<Kebab>();
		try {
			ps = connection.prepareStatement(GET_KEBABS_BY_USER_ID);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				userCustomKebabList.add(buildPartialKebab(rs));
			}
			return userCustomKebabList;

		} catch (SQLException e) {
			throw new RuntimeException("Unable to retrieve partial user kebabs");

		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

	private Kebab buildPartialKebab(ResultSet rs) throws SQLException {
		return new Kebab().setKebabId(rs.getInt("CUSTOM_KEBAB_ID")).setName(rs.getString("CUSTOM_KEBAB_NAME"));
	}


}
