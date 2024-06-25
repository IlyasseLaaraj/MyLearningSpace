package com.advancia.models.kebabComponents;

public class Ingredient {

	private String ingredientName;
	private String ingredientDescription;
	private int ingredientId;

	public Ingredient(String ingredientName, String ingredientDescription, int ingredientId) {
		this.ingredientName = ingredientName;
		this.ingredientDescription = ingredientDescription;
		this.ingredientId = ingredientId;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientDescription() {
		return ingredientDescription;
	}

	public void setIngredientDescription(String ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}
	
	@Override
	public String toString() {
		return "Name: " + ingredientName + " Description: " + ingredientDescription + "\n"; 
	}
}
