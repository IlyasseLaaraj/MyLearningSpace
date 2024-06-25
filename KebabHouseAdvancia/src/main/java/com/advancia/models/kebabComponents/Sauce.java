package com.advancia.models.kebabComponents;

public class Sauce {
	private String sauceName;
	private String sauceDescription;
	private int sauceId;

	public Sauce(String sauceName, String sauceDescription, int sauceId) {
		this.sauceName = sauceName;
		this.sauceDescription = sauceDescription;
		this.sauceId = sauceId;
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
