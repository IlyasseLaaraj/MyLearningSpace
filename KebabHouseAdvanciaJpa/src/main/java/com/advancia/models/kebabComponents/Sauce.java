package com.advancia.models.kebabComponents;

import java.util.List;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@NamedQueries({
	@NamedQuery(name="getAllSauces", query="from Sauce"),
	@NamedQuery(name="getSauceByName", query="FROM Sauce s WHERE s.sauceName = :sauceName")
})
@Entity
@Table(name = "SAUCE")
public class Sauce {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAUCES_SEQUENCE")
	@SequenceGenerator(name = "SAUCES_SEQUENCE", sequenceName = "SAUCES_SEQUENCE", allocationSize = 1)
	@Column(name = "sauce_id")
	private int sauceId;
	
	@Column(name = "sauce_name")
	private String sauceName;
	
	@Column(name = "sauce_description")
	private String sauceDescription;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
	name = "SAUCE_ALLERGEN", joinColumns = { @JoinColumn(name = "SAUCE_ID",  referencedColumnName = "SAUCE_ID") },
	 inverseJoinColumns = {
	@JoinColumn(name = "ALLERGEN_ID",  referencedColumnName = "ALLERGEN_ID") })
	private List<Allergen> allergens;
	
	public Sauce() {}
	
	public Sauce(String sauceName, String sauceDescription, List<Allergen> allergens) {
		this.sauceName = sauceName;
		this.sauceDescription = sauceDescription;
		this.allergens = allergens;
	}

	
	public List<Allergen> getAllergens() {
		return allergens;
	}


	public void setAllergens(List<Allergen> allergens) {
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
