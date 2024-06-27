package com.advancia.models;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.advancia.models.dto.UserDto;

@XmlRootElement(name = "USERS")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
		@NamedQuery(name = "getUsernameAndUserPassword", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "getAllUsers", query="select u from User u"),
		@NamedQuery(name = "getUserByName", query="Select u from User u WHERE u.username = :username")
})
@Entity
@Table(name = "Client", uniqueConstraints = { @UniqueConstraint(columnNames = "USERNAME")})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTS_SEQUENCE")
	@SequenceGenerator(name = "CLIENTS_SEQUENCE", sequenceName = "CLIENTS_SEQUENCE", allocationSize = 1)
	@Column(name = "id")
	private int userId;

	@Column(name = "Username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Kebab> customKebabs;

	public User() {
	}

	public User(String username, String password, List<Kebab> customKebabs) {
		this.username = username;
		this.password = password;
		this.customKebabs = customKebabs;
	}
	

	public User(UserDto user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.customKebabs = user.getCustomKebabs().stream().map(Kebab::new).collect(Collectors.toList());;
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
