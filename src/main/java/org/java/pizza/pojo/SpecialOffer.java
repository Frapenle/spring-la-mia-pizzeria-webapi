package org.java.pizza.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialOffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String name;
	private Integer discount;
	
//	Insert relationship
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	
	public SpecialOffer() {}
	
	public SpecialOffer(LocalDate startDate, LocalDate endDate, String name, Integer discount, Pizza pizza) {
		setStartDate(startDate);
		setEndDate(endDate);
		setName(name);
		setDiscount(discount);
		setPizza(pizza);
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public BigDecimal getDiscountedPrice() {
	    BigDecimal price = pizza.getPrice();
	    BigDecimal discountedPrice = price;
	    BigDecimal totalDiscount = BigDecimal.ZERO;

	    for (SpecialOffer offer : pizza.getSpecialOffers()) {
	        totalDiscount = totalDiscount.add(BigDecimal.valueOf(offer.getDiscount()));
	    }
	    
	    if (totalDiscount.compareTo(BigDecimal.valueOf(100)) > 0) {
	        return BigDecimal.ZERO;
	    }
	    for (SpecialOffer offer : pizza.getSpecialOffers()) {
	        BigDecimal discountAmount = price.multiply(BigDecimal.valueOf(offer.getDiscount())).divide(BigDecimal.valueOf(100));
	        discountedPrice = discountedPrice.subtract(discountAmount);
	    }
	    return discountedPrice.setScale(2);
	}

	@Override
	public String toString() {
		
		return "\nId: " + getId()
			+ "\nData inizio: " + getStartDate()
			+ "\nData fine: " + getEndDate()
			+ "\nSconto: " + getDiscount() + "%"
			+ "\nPrezzo scontato: " + getDiscountedPrice() + "€";
	}
}
