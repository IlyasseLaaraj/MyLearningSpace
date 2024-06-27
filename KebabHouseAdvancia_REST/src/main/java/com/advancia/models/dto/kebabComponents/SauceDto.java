package com.advancia.models.dto.kebabComponents;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.advancia.models.kebabComponents.Sauce;

public class SauceDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int sauceId;	
	private String sauceName;	
	private String sauceDescription;
	private List<AllergenDto> allergens;
	
	public SauceDto() {}
	
	public SauceDto(Sauce sauce) {
		this.sauceId = sauce.getSauceId();
		this.sauceName = sauce.getSauceName();
		this.sauceDescription = sauce.getSauceDescription();
		this.allergens = sauce.getAllergens().stream().map(AllergenDto::new).collect(Collectors.toList());;
	}
	
	public List<AllergenDto> getAllergens() {
		return allergens;
	}


	public void setAllergens(List<AllergenDto> allergens) {
		this.allergens = allergens;
	}


	public int getSauceId() {
		return sauceId;
	}

	public void setSauceId(int sauceId) {
		this.sauceId = sauceId;
	}

	public String getSauceName() {
		return sauceName;
	}

	public void setSauceName(String sauceName) {
		this.sauceName = sauceName;
	}

	public String getSauceDescription() {
		return sauceDescription;
	}

	public void setSauceDescription(String sauceDescription) {
		this.sauceDescription = sauceDescription;
	}
	
	@Override
	public String toString() {
		return "Name: " + sauceName + " Description: " + sauceDescription + "\n"; 
	}
}
