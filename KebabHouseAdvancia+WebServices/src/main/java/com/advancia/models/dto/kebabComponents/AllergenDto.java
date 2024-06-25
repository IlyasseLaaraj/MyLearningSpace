package com.advancia.models.dto.kebabComponents;

import java.io.Serializable;

import com.advancia.models.kebabComponents.Allergen;

public class AllergenDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int allergenId;
	
	private String allergenName;
	
	private String allergenDescription;

	public AllergenDto(Allergen allergen) {
		this.allergenId = allergen.getAllergenId();
		this.allergenName = allergen.getAllergenName();
		this.allergenDescription = allergen.getAllergenDescription();
	}

	public AllergenDto() {}
	
	public int getAllergenId() {
		return allergenId;
	}

	public void setAllergenId(int allergenId) {
		this.allergenId = allergenId;
	}

	public String getAllergenName() {
		return allergenName;
	}

	public void setAllergenName(String allergenName) {
		this.allergenName = allergenName;
	}

	public String getAllergenDescription() {
		return allergenDescription;
	}

	public void setAllergenDescription(String allergenDescription) {
		this.allergenDescription = allergenDescription;
	}
		
	@Override
	public String toString() {
		return "Name: " + allergenName + "\nDescription: " + allergenDescription; 
	}
}
