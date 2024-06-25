package com.advancia.models;

import java.util.List;

import com.advancia.models.kebabComponents.Base;
import com.advancia.models.kebabComponents.Ingredient;
import com.advancia.models.kebabComponents.Meat;
import com.advancia.models.kebabComponents.Sauce;

public class Kebab {
	String kebabName;
	String kebabDescription;
	Base base;
	Meat meat;
	List<Ingredient> ingredients;
	List<Sauce> sauces;
	List<Allergen> allergen;
	int kebabId;


	public int getKebabId() {
		return kebabId;
	}

	public Kebab setKebabId(int kebabId) {
		this.kebabId = kebabId;
		return this;
	}

	public String getKebabName() {
		return kebabName;
	}

	public Kebab setKebabName(String kebabName) {
		this.kebabName = kebabName;
		return this;
	}

	public String getKebabDescription() {
		return kebabDescription;
	}

	public Kebab setKebabDescription(String kebabDescription) {
		this.kebabDescription = kebabDescription;
		return this;
	}

	public List<Allergen> getAllergen() {
		return allergen;
	}

	public Kebab setAllergen(List<Allergen> allergen) {
		this.allergen = allergen;
		return this;
	}

	public String getName() {
		return kebabName;
	}
	
	public Kebab setName(String kebabName) {
		this.kebabName = kebabName;
		return this;
	}
	
	public String getDescription() {
		return kebabDescription;
	}
	
	public Kebab setDescription(String kebabDescription) {
		this.kebabDescription = kebabDescription;
		return this;
	}

	public Base getBase() {
		return base;
	}

	public Kebab setBase(Base base) {
		this.base = base;
		return this;
	}

	public Meat getMeat() {
		return meat;
	}

	public Kebab setMeat(Meat meat) {
		this.meat = meat;
		return this;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public Kebab setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
		return this;
	}

	public List<Sauce> getSauces() {
		return sauces;
	}

	public Kebab setSauces(List<Sauce> sauces) {
		this.sauces = sauces;
		return this;
	}

	@Override
	public String toString() {
		return "Name: " + kebabName + "\nDescription: " + kebabDescription + "\nbase: " + base + "\nmeat: " + meat + "\ningredients: " + ingredients + "\nSauces: " + sauces + "\nallergens : " + allergen; 
	}
}
