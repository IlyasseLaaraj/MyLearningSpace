package com.advancia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ALLERGENS")
public class Allergen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALLERGENS_SEQUENCE")
	@SequenceGenerator(name = "ALLERGENS_SEQUENCE", sequenceName = "ALLERGENS_SEQUENCE", allocationSize = 1)
	@Column(name = "allergen_id")
	private int allergenId;
	
	@Column(name = "allergen_name")
	private String allergenName;
	
	@Column(name = "allergen_description")
	private String allergenDescription;

	public Allergen(String allergenName, String allergenDescription, int allergenId) {
		this.allergenName = allergenName;
		this.allergenDescription = allergenDescription;
		this.allergenId = allergenId;
	}

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
