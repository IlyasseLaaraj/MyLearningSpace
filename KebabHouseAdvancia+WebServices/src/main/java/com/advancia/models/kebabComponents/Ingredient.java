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

import com.advancia.models.dto.kebabComponents.IngredientDto;

@XmlRootElement(name = "INGREDIENTS")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
	@NamedQuery(name="getAllIngredients", query="Select i from Ingredient i"),
	@NamedQuery(name="getIngredientByName", query="select i FROM Ingredient i WHERE i.ingredientName = :ingredientName")
})
@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INGREDIENTS_SEQUENCE")
	@SequenceGenerator(name = "INGREDIENTS_SEQUENCE", sequenceName = "INGREDIENTS_SEQUENCE", allocationSize = 1)
	@Column(name = "ingredient_id")
	private int ingredientId; 
	
	@Column(name = "ingredient_name")
	private String ingredientName;
	
	@Column(name = "ingredient_description")
	private String ingredientDescription;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(
	name = "INGREDIENT_ALLERGEN", joinColumns = { @JoinColumn(name = "INGREDIENT_ID",  referencedColumnName = "INGREDIENT_ID") },
	 inverseJoinColumns = {
	@JoinColumn(name = "ALLERGEN_ID",  referencedColumnName = "ALLERGEN_ID") })
	private List<Allergen> allergens;
	
	public List<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Allergen> allergens) {
		this.allergens = allergens;
	}

	public Ingredient() {}
	
	public Ingredient(String ingredientName, String ingredientDescription, List<Allergen> allergens) {
		this.ingredientName = ingredientName;
		this.ingredientDescription = ingredientDescription;
		this.allergens = allergens;
	}
	
	public Ingredient(IngredientDto ingredient) {		
		this.ingredientId = ingredient.getIngredientId();
		this.ingredientName = ingredient.getIngredientName();
		this.ingredientDescription = ingredient.getIngredientDescription();
		this.allergens = ingredient.getAllergens().stream().map(Allergen::new).collect(Collectors.toList());
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientDescription() {
		return ingredientDescription;
	}

	public void setIngredientDescription(String ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}
	
	@Override
	public String toString() {
		return "Name: " + ingredientName + " Description: " + ingredientDescription + "\n"; 
	}
}
