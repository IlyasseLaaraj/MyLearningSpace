package com.advancia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancia.daos.config.ConnectionFactory;

public class UserDao {

	private static final String GET_USER_ID_BY_USERNAME_PASSWORD = "SELECT USERS.user_id as USER_ID FROM USERS WHERE USERS.USERNAME = ? AND USERS.PASSWORD = ?";
	
	private static final Logger log = LoggerFactory.getLogger(UserDao.class); 
	
	public int checkUserCredentials(Connection connection, String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		log.info("Checking user credential for username: {}", username);
		try {
			ps = connection.prepareStatement(GET_USER_ID_BY_USERNAME_PASSWORD);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				log.info("Login completed for user {}", username);
				return rs.getInt("USER_ID");
			}
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException("Error while checking user credentials for " + username);
		} finally {
			ConnectionFactory.closeRequest(ps, rs);
		}
	}

}
