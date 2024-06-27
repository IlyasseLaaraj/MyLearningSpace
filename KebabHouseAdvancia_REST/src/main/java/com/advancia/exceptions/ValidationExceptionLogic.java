package com.advancia.exceptions;

import com.advancia.models.dto.UserDto;
import com.advancia.models.dto.kebabComponents.AllergenDto;
import com.advancia.models.dto.kebabComponents.BaseDto;
import com.advancia.models.dto.kebabComponents.IngredientDto;
import com.advancia.models.dto.kebabComponents.MeatDto;
import com.advancia.models.dto.kebabComponents.SauceDto;

public class ValidationExceptionLogic {
	
	public String validateBaseDto(BaseDto base) {
	    if (base == null) {
	        return "Base cannot be null.";
	    }
	    if (base.getBaseId() != 0) {
	        return "Base ID must not be provided. It will be automatically generated.";
	    }
	    if (base.getBaseName() == null || base.getBaseName().trim().isEmpty()) {
	        return "Base name cannot be empty or blank.";
	    }
	    if (base.getBaseDescription() == null || base.getBaseDescription().trim().isEmpty()) {
	    	return "Base description cannot be empty or blank.";
	    }
	    return null; 
	}
	
	public String validateAllergenDto(AllergenDto allergen) {
		if (allergen == null) {
			return "Allergen cannot be null.";
		}
		if (allergen.getAllergenId() != 0) {
	        return "Allergen ID must not be provided. It will be automatically generated.";
	    }
		if (allergen.getAllergenName() == null || allergen.getAllergenName().trim().isEmpty()) {
			return "Allergen name cannot be empty or blank.";
		}
		if (allergen.getAllergenDescription() == null || allergen.getAllergenDescription().trim().isEmpty()) {
			return "Allergen description cannot be empty or blank.";
		}
		return null;
	}
	
	public String validateMeatDto(MeatDto meat) {
		if (meat == null) {
			return "Meat cannot be null.";
		}
		if (meat.getMeatId() != 0) {
			return "Meat ID must not be provided. It will be automatically generated.";
		}
		if (meat.getMeatName() == null || meat.getMeatName().trim().isEmpty()) {
			return "Meat name cannot be empty or blank.";
		}
		if (meat.getMeatDescription() == null || meat.getMeatDescription().trim().isEmpty()) {
			return "Meat description cannot be empty or blank.";
		}
		return null;
	}
	
	public String validateIngredientDto(IngredientDto ingredient) {
		if (ingredient == null) {
			return "Ingredient cannot be null.";
		}
		if (ingredient.getIngredientId() != 0) {
			return "Ingredient ID must not be provided. It will be automatically generated.";
		}
		if (ingredient.getIngredientName() == null || ingredient.getIngredientName().trim().isEmpty()) {
			return "Ingredient name cannot be empty or blank.";
		}
		if (ingredient.getIngredientDescription() == null || ingredient.getIngredientDescription().trim().isEmpty()) {
			return "Ingredient description cannot be empty or blank.";
		}
		return null;
	}
	
	public String validateSauceDto(SauceDto sauce) {
		if (sauce == null) {
			return "Sauce cannot be null.";
		}
		if (sauce.getSauceId() != 0) {
			return "Sauce ID must not be provided. It will be automatically generated.";
		}
		if (sauce.getSauceName() == null || sauce.getSauceName().trim().isEmpty()) {
			return "Sauce name cannot be empty or blank.";
		}
		if (sauce.getSauceDescription() == null || sauce.getSauceDescription().trim().isEmpty()) {
			return "Sauce description cannot be empty or blank.";
		}
		return null;
	}
	
	public String validateUserDto(UserDto user) {
		if (user == null) {
			return "User cannot be null.";
		}
		if (user.getUserId() != 0) {
			return "User ID must not be provided. It will be automatically generated.";
		}
		if (user.getUsername() == null || user.getUsername().length() < 8) {
			return "User description cannot be empty or blank.";
		}
		if (user.getPassword() == null || user.getPassword().length() < 8) {
			return "User password must be at least 8 caracters";
		}
		return null;
	}
}
