package com.advancia.models;

import java.util.List;

public class KebabData {
	
	private List<Integer> ingredients;
	private List<Integer> sauces;
	private int meat;
	private int base;
	private String kebabName;
	private int kebabId;
	private List<Integer> allergens;
	
	public List<Integer> getAllergens() {
		return allergens;
	}

	public KebabData setAllergens(List<Integer> allergens) {
		this.allergens = allergens;
		return this;
	}

	public List<Integer> getIngredients() {
		return ingredients;
	}

	public KebabData setIngredients(List<Integer> ingredients) {
		this.ingredients = ingredients;
		return this;
	}

	public List<Integer> getSauces() {
		return sauces;
	}

	public KebabData setSauces(List<Integer> sauces) {
		this.sauces = sauces;
		return this;
	}

	public int getMeat() {
		return meat;
	}

	public KebabData setMeat(int meat) {
		this.meat = meat;
		return this;
	}

	public int getBase() {
		return base;
	}

	public KebabData setBase(int base) {
		this.base = base;
		return this;
	}

	public String getKebabName() {
		return kebabName;
	}

	public KebabData setKebabName(String kebabName) {
		this.kebabName = kebabName;
		return this;
	}

	public int getKebabId() {
		return kebabId;
	}

	public KebabData setKebabId(int kebabId) {
		this.kebabId = kebabId;
		return this;
	}
}
