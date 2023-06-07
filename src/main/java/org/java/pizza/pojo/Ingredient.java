package org.java.pizza.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Column(columnDefinition = "TEXT")
	@Size(min = 2, max = 20, message = "Il nome deve avere almeno almeno 2 caratteri e massimo 100.")
	private String name;

	@NumberFormat
	@Column(columnDefinition = "INT")
	private int kcal;
	
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizze = new ArrayList<>();

	public Ingredient() {}
	
	public Ingredient(String name) {
		setName(name);
	}
	
	public Ingredient(String name, int kcal) {
		this(name);
		setKcal(kcal);
	}
	
	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ingredient)) return false;
		Ingredient ingredientObj = (Ingredient) obj;
		return getId() == ingredientObj.getId();
	}
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public String toString() {
		 return "Id: " + getId()
			+ "\nIngrediente: " + getName()
			+ "\nkcal: " + getKcal();
	}
	
	

}
