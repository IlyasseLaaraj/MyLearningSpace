package com.advancia.models.dto.kebabComponents;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.advancia.models.kebabComponents.Meat;

public class MeatDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int meatId;
	
	private String meatName;
	
	private String meatDescription;
	
	private List<AllergenDto> allergens;
	

	public MeatDto() {
	}
	
	public MeatDto(Meat meat) {
		this.meatId = meat.getMeatId();
		this.meatName = meat.getMeatName();
		this.meatDescription = meat.getMeatDescription();
		this.allergens = meat.getAllergens().stream().map(AllergenDto::new).collect(Collectors.toList());;
	}

	public List<AllergenDto> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<AllergenDto> allergens) {
		this.allergens = allergens;
	}

	public String getMeatName() {
		return meatName;
	}

	public int getMeatId() {
		return meatId;
	}

	public void setMeatId(int meatId) {
		this.meatId = meatId;
	}

	public void setMeatName(String meatName) {
		this.meatName = meatName;
	}

	public String getMeatDescription() {
		return meatDescription;
	}

	public void setMeatDescription(String meatDescription) {
		this.meatDescription = meatDescription;
	}
	
	@Override
	public String toString() {
		return "Name: " + meatName + " Description: " + meatDescription + " meatId: " + meatId + "\n" ; 
	}
}
