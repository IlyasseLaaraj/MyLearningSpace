package com.advancia.models;

import java.util.List;

import com.advancia.models.kebabComponents.Base;
import com.advancia.models.kebabComponents.Ingredient;
import com.advancia.models.kebabComponents.Meat;
import com.advancia.models.kebabComponents.Sauce;

public class PageData {

	private List<Kebab> userKebabs;
	private List<Ingredient> ingredientsList;
	private List<Sauce> saucesList;
	private List<Base> basesList;
	private List<Meat> meatsList;

	public List<Kebab> getUserKebabs() {
		return userKebabs;
	}

	public PageData setUserKebabs(List<Kebab> userKebabs) {
		this.userKebabs = userKebabs;
		return this;
	}

	public List<Ingredient> getIngredientsList() {
		return ingredientsList;
	}

	public PageData setIngredientsList(List<Ingredient> ingredientsList) {
		this.ingredientsList = ingredientsList;
		return this;
	}

	public List<Sauce> getSaucesList() {
		return saucesList;
	}

	public PageData setSaucesList(List<Sauce> saucesList) {
		this.saucesList = saucesList;
		return this;
	}

	public List<Base> getBasesList() {
		return basesList;
	}

	public PageData setBasesList(List<Base> basesList) {
		this.basesList = basesList;
		return this;
	}

	public List<Meat> getMeatsList() {
		return meatsList;
	}

	public PageData setMeatsList(List<Meat> meatsList) {
		this.meatsList = meatsList;
		return this;
	}

}
