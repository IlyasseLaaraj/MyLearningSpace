package com.advancia;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class JdbcConnector {
	BasicDataSource connectionSource;
	
	public JdbcConnector() {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			connectionSource = (BasicDataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
			System.out.println("Connected to database JdbcConenctor");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws  SQLException {
		Connection connection;
		connection = connectionSource.getConnection();
		System.out.println("Connected to database from getConnection");
		return connection;
	}

}
