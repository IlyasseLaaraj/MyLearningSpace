package com.advancia.models;

import java.util.List;

public class WebFormData {
	
	private String meat;
	private String base;
	private List<String> ingredients;
	private List<String> sauces;
	private String kebabName;
	private Integer kebabId;
	
	public String getMeat() {
		return meat;
	}
	public WebFormData setMeat(String meat) {
		this.meat = meat;
		return this;
	}
	public String getBase() {
		return base;
	}
	public WebFormData setBase(String base) {
		this.base = base;
		return this;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public WebFormData setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
		return this;
	}
	public List<String> getSauces() {
		return sauces;
	}
	public WebFormData setSauces(List<String> sauces) {
		this.sauces = sauces;
		return this;
	}
	public String getKebabName() {
		return kebabName;
	}
	public WebFormData setKebabName(String kebabName) {
		this.kebabName = kebabName;
		return this;
	}
	public Integer getKebabId() {
		return kebabId;
	}
	public WebFormData setKebabId(Integer kebabId) {
		this.kebabId = kebabId;
		return this;
	}
	@Override
	public String toString() {
		return "WebFormData [meat=" + meat + ", base=" + base + ", ingredients=" + ingredients + ", sauces=" + sauces
				+ ", kebabName=" + kebabName + ", kebabId=" + kebabId + "]";
	}
}
