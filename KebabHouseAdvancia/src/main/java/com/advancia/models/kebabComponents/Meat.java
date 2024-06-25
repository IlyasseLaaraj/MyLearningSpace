package com.advancia.models.kebabComponents;

public class Meat {
	private String meatName;
	private String meatDescription;
	private int meatId;

	public Meat(String meatName, String meatDescription, int meatId) {
		this.meatName = meatName;
		this.meatDescription = meatDescription;
		this.meatId = meatId;
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
