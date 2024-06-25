package com.advancia.daos;

import java.util.List;

public class AddKebabRequest {

	private int userId;
	private String name;
	private List<String> ingredients;
	private List<String> sauces;
	private String base;
	private String meat;
	
	public AddKebabRequest() {
	}

	public int getUserId() {
		return userId;
	}

	public AddKebabRequest setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public String getName() {
		return name;
	}

	public AddKebabRequest setName(String name) {
		this.name = name;
		return this;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public AddKebabRequest setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
		return this;
	}

	public List<String> getSauces() {
		return sauces;
	}

	public AddKebabRequest setSauces(List<String> sauces) {
		this.sauces = sauces;
		return this;
	}

	public String getBase() {
		return base;
	}

	public AddKebabRequest setBase(String base) {
		this.base = base;
		return this;
	}

	public String getMeat() {
		return meat;
	}

	public AddKebabRequest setMeat(String meat) {
		this.meat = meat;
		return this;
	}

}
