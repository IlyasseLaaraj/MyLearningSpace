package com.advancia.daos.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ConnectionFactory {

	private final BasicDataSource connectionSource;
	public ConnectionFactory() {
		try {
			InitialContext ctx = new InitialContext();
			connectionSource = (BasicDataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
		} catch (NamingException ex) {
			throw new RuntimeException("Error while getting context from tomcat", ex);
		}
	}

	public synchronized Connection getConnection() {
		try {
			return connectionSource.getConnection();
		} catch (SQLException ex) {
			throw new RuntimeException("Error while opening the connection from dataSource", ex);
		}
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeRequest(PreparedStatement preparedStatement, ResultSet resultSet) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Close PreparedStatement
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void openTransaction(Connection connection) {
		try {
			connection.setAutoCommit(false);
		}catch(Exception ex) {
			throw new RuntimeException("Error while opening transaction");
		}
	}
	
	public static void commitTransaction(Connection connection) {
		try {
			connection.commit();
			connection.setAutoCommit(true);
		}catch(Exception ex) {
			throw new RuntimeException("Error while committing transaction");
		}
	}
	
	public static void rollbackTransaction(Connection connection) {
		try {
			connection.rollback();
		}catch(Exception ex) {
			throw new RuntimeException("Error while executing rollback");
		}
	}

}
