package com.advancia.models.dto.kebabComponents;

import java.io.Serializable;
import java.util.List;

import com.advancia.models.kebabComponents.Ingredient;

public class IngredientDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private int ingredientId; 

	private String ingredientName;

	private String ingredientDescription;

	private List<AllergenDto> allergens;
	
	public List<AllergenDto> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<AllergenDto> allergens) {
		this.allergens = allergens;
	}

	public IngredientDto() {}
	
	public IngredientDto(Ingredient ingredient) {
		this.ingredientId = ingredient.getIngredientId();
		this.ingredientName = ingredient.getIngredientName();
		this.ingredientDescription = ingredient.getIngredientDescription();
		this.allergens = ingredient.getAllergens().stream().map(AllergenDto::new).toList();
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
