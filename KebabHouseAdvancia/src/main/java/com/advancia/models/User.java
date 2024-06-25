package com.advancia.models;

public class User {
	private String username;
	private String password;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User(String username, String password, int userId) {
		this.username = username;
		this.password = password;
		this.userId = userId;	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Name: " + username + "Password: " + password; 
	}
}
