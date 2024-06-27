package com.advancia.models.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.advancia.models.User;


public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private int userId;

	private String username;

	private String password;

	private List<KebabDto> customKebabs;

	public UserDto() {
	}

	public UserDto(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.customKebabs = user.getCustomKebabs().stream().map(KebabDto::new).collect(Collectors.toList());;
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

	public List<KebabDto> getCustomKebabs() {
		return customKebabs;
	}

	public void setCustomKebabs(List<KebabDto> customKebabs) {
		this.customKebabs = customKebabs;
	}

	@Override
	public String toString() {
		return "userId: " + userId + "Name: " + username + "Password: " + password;
	}
}
