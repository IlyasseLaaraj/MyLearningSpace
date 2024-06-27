package com.advancia.models.kebabComponents;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advancia.models.dto.kebabComponents.SauceDto;
@NamedQueries({
	@NamedQuery(name="getAllSauces", query="select s from Sauce s"),
	@NamedQuery(name="getSauceByName", query="select s FROM Sauce s WHERE s.sauceName = :sauceName")
})

@XmlRootElement(name = "SAUCE")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "SAUCE", uniqueConstraints = { @UniqueConstraint(columnNames = "SAUCE_NAME")})
public class Sauce implements Serializable {
	
	private static final long serialVersionUID = 1L;

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

	public Sauce(SauceDto sauce) {
		this.sauceId = sauce.getSauceId();
		this.sauceName = sauce.getSauceName();
		this.sauceDescription = sauce.getSauceDescription();
		this.allergens = sauce.getAllergens().stream().map(Allergen::new).collect(Collectors.toList());
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
