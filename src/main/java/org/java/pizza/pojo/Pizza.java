package org.java.pizza.pojo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@NonNull
//	@NotBlank(message = "Il nome non può essere vuoto.")
	@Size(min = 2, max = 20, message = "Il nome deve avere almeno 2 caratteri e massimo 20.")
	private String name;
	@NotBlank(message = "La descrizione non può essere vuota.")
	@Size(min = 5, max = 100, message = "La descrizione deve avere almeno almeno 5 caratteri e massimo 100.")
	private String description;
	@NotBlank(message = "Inserisci un url valido.")
	@URL(message = "Url non valido.")
	private String imageUrl;
	@NotNull(message = "Inserisci un numero.")
	@PositiveOrZero(message = "Il numero deve essere positivo.")
	@Max(value = 1000, message = "Non è possibile inserire un numero maggiore di 1000.")
	private BigDecimal price;
	
	private boolean isNewPizza;

	//	relationship
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
	private List<SpecialOffer> specialOffers;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() {};
	
	public Pizza(String name, String description, String imageUrl, BigDecimal price, Ingredient... ingredients) {
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
		setIngredients(ingredients);
	}

	public boolean isNewPizza() {
		return isNewPizza;
	}
	public void setNewPizza(boolean isNewPizza) {
		this.isNewPizza = isNewPizza;
	}

	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
//	INGREDIENT
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public void setIngredients(Ingredient[] ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	public void addIngredient(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
	public void removeIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);
	}
//	special offer
	public List<SpecialOffer> getSpecialOffers(){
		return specialOffers;
	}
	
	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	public BigDecimal getDiscountedPrice(List<SpecialOffer> specialOffers) {
	    for (SpecialOffer specialOffer : specialOffers) {
	        if (specialOffer.getPizza().getId() == getId()) {
	            return specialOffer.getDiscountedPrice();
	        }
	    }
	    return this.getPrice();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pizza)) return false;
		Pizza pizzaObj = (Pizza) obj;
		return getId() == pizzaObj.getId();
	}
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public String toString() {
		return "Id: " + getId()
				+ "\nName: " + getName()
				+ "\nDescription: " + getDescription()
				+ "\nPrice " + getPrice()
				+ "\nImage: " + getImageUrl()
				+ "\n\n=========================\n";
	}
}
