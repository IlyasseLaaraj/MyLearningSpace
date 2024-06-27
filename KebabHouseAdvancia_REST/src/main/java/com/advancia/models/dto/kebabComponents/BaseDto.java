package com.advancia.models.dto.kebabComponents;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


import com.advancia.models.kebabComponents.Base;

//@XmlRootElement(name="baseDto")
//@XmlAccessorType(XmlAccessType.FIELD)
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int baseId;
	
	private String baseName;
	
	private String baseDescription;
	
	private List<AllergenDto> allergens;
	
	public BaseDto(Base base) {
		this.baseId = base.getBaseId();
		this.baseName = base.getBaseName();
		this.baseDescription = base.getBaseDescription();
		this.allergens = base.getAllergens().stream().map(AllergenDto::new).collect(Collectors.toList());;

	}
	
	public BaseDto() {}

	public List<AllergenDto> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<AllergenDto> allergens) {
		this.allergens = allergens;
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
