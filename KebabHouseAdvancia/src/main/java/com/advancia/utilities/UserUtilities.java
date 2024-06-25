package com.advancia.utilities;

import java.sql.Connection;

import com.advancia.daos.UserDao;
import com.advancia.daos.config.ConnectionFactory;

public class UserUtilities {
	
	private final UserDao userDao = new UserDao();
	private final ConnectionFactory connectionFactory = new ConnectionFactory();
	
	
	public int login(String username, String password) {
		Connection connection = connectionFactory.getConnection();
		int userId = userDao.checkUserCredentials(connection, username, password);
		connectionFactory.closeConnection(connection);
		return userId;
	}

}
