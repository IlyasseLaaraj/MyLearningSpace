package com.advancia.models.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.advancia.models.Kebab;
import com.advancia.models.dto.kebabComponents.BaseDto;
import com.advancia.models.dto.kebabComponents.IngredientDto;
import com.advancia.models.dto.kebabComponents.MeatDto;
import com.advancia.models.dto.kebabComponents.SauceDto;
//@XmlRootElement(name="kebabs")
public class KebabDto implements Serializable{

	private static final long serialVersionUID = 1L;

	int kebabId;

	String kebabName;

	BaseDto base;

	MeatDto meat;

	List<IngredientDto> ingredients;

	List<SauceDto> sauces;


	public KebabDto() {
	}

	public int getKebabId() {
		return kebabId;
	}

	public KebabDto(Kebab kebab) {
		this.kebabId = kebab.getKebabId();
		this.kebabName = kebab.getName();
		this.base = new BaseDto(kebab.getBase());
		this.meat = new MeatDto(kebab.getMeat());
		this.ingredients = kebab.getIngredients().stream().map(IngredientDto::new).collect(Collectors.toList());
		this.sauces = kebab.getSauces().stream().map(SauceDto::new).collect(Collectors.toList());
	}

	public KebabDto setKebabId(int kebabId) {
		this.kebabId = kebabId;
		return this;
	}

	public String getName() {
		return kebabName;
	}

	public KebabDto setName(String kebabName) {
		this.kebabName = kebabName;
		return this;
	}

	public BaseDto getBase() {
		return base;
	}

	public KebabDto setBase(BaseDto base) {
		this.base = base;
		return this;
	}

	public MeatDto getMeat() {
		return meat;
	}

	public KebabDto setMeat(MeatDto meat) {
		this.meat = meat;
		return this;
	}

	public List<IngredientDto> getIngredients() {
		return ingredients;
	}

	public KebabDto setIngredients(List<IngredientDto> ingredients) {
		this.ingredients = ingredients;
		return this;
	}

	public List<SauceDto> getSauces() {
		return sauces;
	}

	public KebabDto setSauces(List<SauceDto> sauces) {
		this.sauces = sauces;
		return this;
	}

	@Override
	public String toString() {
		return "Kebab [kebabId=" + kebabId + ", kebabName=" + kebabName + ", base=" + base + ", meat=" + meat
				+ ", ingredients=" + ingredients + ", sauces=" + sauces + "]";
	}

}
