package com.advancia.models.kebabComponents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.advancia.models.dto.kebabComponents.AllergenDto;

@XmlRootElement(name = "ALLERGENS")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
@NamedQuery(name="getAllAllergens", query="Select a FROM Allergen a"),
@NamedQuery(name="getAllergenByName", query="select a FROM Allergen a WHERE a.allergenName = :allergenName")
})
@Entity
@Table(name = "ALLERGEN", uniqueConstraints = { @UniqueConstraint(columnNames = "ALLERGEN_NAME")})
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

	public Allergen(String allergenName, String allergenDescription) {
		this.allergenName = allergenName;
		this.allergenDescription = allergenDescription;
	}
	
	public Allergen(AllergenDto allergen) {
		this.allergenId = allergen.getAllergenId();
		this.allergenName = allergen.getAllergenName();
		this.allergenDescription = allergen.getAllergenDescription();
	}

	public Allergen() {}
	
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
