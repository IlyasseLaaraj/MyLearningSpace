package com.advancia.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advancia.models.dto.KebabDto;
import com.advancia.models.dto.kebabComponents.BaseDto;
import com.advancia.models.dto.kebabComponents.IngredientDto;
import com.advancia.models.dto.kebabComponents.MeatDto;
import com.advancia.models.dto.kebabComponents.SauceDto;
import com.advancia.models.kebabComponents.Base;
import com.advancia.models.kebabComponents.Ingredient;
import com.advancia.models.kebabComponents.Meat;
import com.advancia.models.kebabComponents.Sauce;

@Entity
@Table(name = "KEBAB")
public class Kebab {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KEBABS_SEQUENCE")
	@SequenceGenerator(name = "KEBABS_SEQUENCE", sequenceName = "KEBABS_SEQUENCE", allocationSize = 1)
	@Column(name = "kebab_id")
	int kebabId;

	@Column(name = "kebab_name")
	String kebabName;

	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "BASE_ID", referencedColumnName = "BASE_ID")
	Base base;

	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "MEAT_ID", referencedColumnName = "MEAT_ID")
	Meat meat;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "KEBAB_INGREDIENT", joinColumns = {
			@JoinColumn(name = "kebab_id", referencedColumnName = "kebab_id") }, inverseJoinColumns = {
					@JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id") })
	List<Ingredient> ingredients;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "KEBAB_SAUCE", joinColumns = {
			@JoinColumn(name = "kebab_id", referencedColumnName = "kebab_id") }, inverseJoinColumns = {
					@JoinColumn(name = "SAUCE_ID", referencedColumnName = "SAUCE_ID") })
	List<Sauce> sauces;

	@ManyToOne	
	private User user;

	public Kebab() {
	}

	public int getKebabId() {
		return kebabId;
	}

	public Kebab(String kebabName, Base base, Meat meat, List<Ingredient> ingredients, List<Sauce> sauces, User user) {
		super();
		this.kebabName = kebabName;
		this.base = base;
		this.meat = meat;
		this.ingredients = ingredients;
		this.sauces = sauces;
		this.user = user;
	}
	
	public Kebab(KebabDto kebab) {
		this.kebabId = kebab.getKebabId();
		this.kebabName = kebab.getName();
		this.base = new Base(kebab.getBase());
		this.meat = new Meat(kebab.getMeat());
		this.ingredients = kebab.getIngredients().stream().map(Ingredient::new).collect(Collectors.toList());
		this.sauces = kebab.getSauces().stream().map(Sauce::new).collect(Collectors.toList());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Kebab setKebabId(int kebabId) {
		this.kebabId = kebabId;
		return this;
	}

	public String getName() {
		return kebabName;
	}

	public Kebab setName(String kebabName) {
		this.kebabName = kebabName;
		return this;
	}

	public Base getBase() {
		return base;
	}

	public Kebab setBase(Base base) {
		this.base = base;
		return this;
	}

	public Meat getMeat() {
		return meat;
	}

	public Kebab setMeat(Meat meat) {
		this.meat = meat;
		return this;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public Kebab setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
		return this;
	}

	public List<Sauce> getSauces() {
		return sauces;
	}

	public Kebab setSauces(List<Sauce> sauces) {
		this.sauces = sauces;
		return this;
	}

	@Override
	public String toString() {
		return "Kebab [kebabId=" + kebabId + ", kebabName=" + kebabName + ", base=" + base + ", meat=" + meat
				+ ", ingredients=" + ingredients + ", sauces=" + sauces + ", user=" + user + "]";
	}

}
