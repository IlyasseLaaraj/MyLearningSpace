package com.advancia.models.kebabComponents;

public class Base {

	private String baseName;
	private String baseDescription;
	private int baseId;

	public Base(String baseName, String baseDescription, int baseId) {
		this.baseName = baseName;
		this.baseDescription = baseDescription;
		this.baseId = baseId;

	}

	public int getBaseId() {
		return baseId;
	}

	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getBaseDescription() {
		return baseDescription;
	}

	public void setBaseDescription(String baseDescription) {
		this.baseDescription = baseDescription;
	}

	@Override
	public String toString() {
		return "Name: " + baseName + " Description: " + baseDescription + "\n";
	}

}
