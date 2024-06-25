package com.advancia.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQuery(name = "getUsernameAndUserPassword", query = "FROM User u WHERE u.username = :username AND u.password = :password")
@Entity
@Table(name = "Client")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTS_SEQUENCE")
	@SequenceGenerator(name = "CLIENTS_SEQUENCE", sequenceName = "CLIENTS_SEQUENCE", allocationSize = 1)
	@Column(name = "User_id")
	private int userId;

	@Column(name = "Username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany
	@JoinColumn(name = "User_id")
	private List<Kebab> customKebabs;

	public User() {
	}

	public User(String username, String password, List<Kebab> customKebabs) {
		this.username = username;
		this.password = password;
		this.customKebabs = customKebabs;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public List<Kebab> getCustomKebabs() {
		return customKebabs;
	}

	public void setCustomKebabs(List<Kebab> customKebabs) {
		this.customKebabs = customKebabs;
	}

	@Override
	public String toString() {
		return "userId: " + userId + "Name: " + username + "Password: " + password;
	}
}
