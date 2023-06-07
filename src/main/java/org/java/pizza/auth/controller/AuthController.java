package org.java.pizza.auth.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.pojo.SpecialOffer;
import org.java.pizza.service.PizzaService;
import org.java.pizza.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class AuthController {
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping
	public String getGuestHome(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizze", pizze);
		return "home";
	}
	@GetMapping("/pizze/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		Pizza pizza = optionalPizza.get();
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizza", pizza);
		return "pizza/pizza";
	}
	
	
}
