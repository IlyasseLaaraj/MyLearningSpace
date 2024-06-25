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
@NamedQuery(name = "getAllMeats", query="from Meat"),
@NamedQuery(name = "getMeatByName", query="FROM Meat b WHERE b.meatName = :meatName")
})
@Entity
@Table(name = "MEAT")
public class Meat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEATS_SEQUENCE")
	@SequenceGenerator(name = "MEATS_SEQUENCE", sequenceName = "MEATS_SEQUENCE", allocationSize = 1)
	@Column(name = "meat_id")
	private int meatId;
	
	@Column(name = "meat_name")
	private String meatName;
	
	@Column(name = "meat_description")
	private String meatDescription;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
	name = "MEAT_ALLERGEN", joinColumns = { @JoinColumn(name = "MEAT_ID",  referencedColumnName = "MEAT_ID") },
	 inverseJoinColumns = {
	@JoinColumn(name = "ALLERGEN_ID",  referencedColumnName = "ALLERGEN_ID") })
	private List<Allergen> allergens;
	

	public Meat() {
	}
	
	public Meat(String meatName, String meatDescription, List<Allergen> allergens) {
		this.meatName = meatName;
		this.meatDescription = meatDescription;
		this.allergens = allergens;
	}

	public List<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Allergen> allergens) {
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
