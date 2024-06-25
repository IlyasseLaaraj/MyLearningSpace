package com.advancia.models.kebabComponents;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advancia.models.dto.kebabComponents.BaseDto;

@XmlRootElement(name = "BASES")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
@NamedQuery(name="getAllBases", query="Select b FROM Base b"),
@NamedQuery(name="getBaseByName", query="select b FROM Base b WHERE b.baseName = :baseName")
})
@Entity
@Table(name = "BASE")
public class Base {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BASE_SEQUENCE")
	@SequenceGenerator(name = "BASE_SEQUENCE", sequenceName = "BASE_SEQUENCE", allocationSize = 1)
	@Column(name = "base_id")
	private int baseId;
	
	@Column(name = "base_name")
	private String baseName;
	
	@Column(name = "base_description")
	private String baseDescription;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
	name = "BASE_ALLERGEN", joinColumns = { @JoinColumn(name = "BASE_ID",  referencedColumnName = "BASE_ID") },
	 inverseJoinColumns = {
	@JoinColumn(name = "ALLERGEN_ID",  referencedColumnName = "ALLERGEN_ID") })
	private List<Allergen> allergens;
	
	public Base(String baseName, String baseDescription, List<Allergen> allergens) {
		this.baseName = baseName;
		this.baseDescription = baseDescription;
		this.allergens = allergens;

	}
	
	public Base(BaseDto base) {
		this.baseId = base.getBaseId();
		this.baseName = base.getBaseName();
		this.baseDescription = base.getBaseDescription();
		this.allergens = base.getAllergens().stream().map(Allergen::new).collect(Collectors.toList());;
	}
	
	public Base() {}

	public List<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Allergen> allergens) {
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
