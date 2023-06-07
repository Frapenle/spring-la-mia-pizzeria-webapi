package org.java.pizza.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Ingredient;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.pojo.SpecialOffer;
import org.java.pizza.service.IngredientService;
import org.java.pizza.service.PizzaService;
import org.java.pizza.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller

public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private SpecialOfferService specialOfferService;
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/")
	public String getGuest() {
		return "guest";
	}
	
	@GetMapping("/admin")
	public String getIndex(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizze", pizze);
		return "index";
	}
//	SHOW
	@GetMapping("/pizze/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		Pizza pizza = optionalPizza.get();
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizza", pizza);
		return "pizza/pizza";
	}
	
	@PostMapping("/user/pizze")
	public String getPizzaByName(Model model, @RequestParam(required = false) String name) {
		List<Pizza> pizze = pizzaService.findByName(name);
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		System.err.println(pizze);
		model.addAttribute("pizze", pizze);
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("name", name);
		return "index";
	}
	
//	CREATE
	@GetMapping("/admin/pizze/create")
	public String createPizza(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredients);
		return "pizza/create";
	}

	@PostMapping("/admin/pizze/create")
	public String storePizza(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println("Error: " + err.getDefaultMessage());
			pizza.setNewPizza(true);
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			return "create";
		}
		pizzaService.save(pizza);
		return "redirect:/admin";
	}
	
//	DELETE
	@GetMapping("/admin/pizze/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		Pizza pizza = optionalPizza.get();
		pizzaService.delete(pizza);
		
		return "redirect:/admin";
	}
	
//	UPDATE
	@GetMapping("/admin/pizze/update/{id}")
	public String edit(@PathVariable("id") Integer id,
						Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		Pizza pizza = optionalPizza.get();
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", pizza);
		return "pizza/update";
	}
	
	@PostMapping("/admin/pizze/update/{id}")
	public String update(
			@PathVariable Integer id,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println("Error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			return "update";
		}
		pizzaService.save(pizza);
		return "redirect:/admin";
	}
	
	@GetMapping("admin/pizze/{id}/create")
	public String getSpecialOfferCreate(Model model, @PathVariable Integer id) {
		Pizza pizza = pizzaService.findById(id).get();
		model.addAttribute("specialOffer", new SpecialOffer());
		model.addAttribute("pizza", pizza);
		return "specialOffers/spo-create";
	}
	
	@PostMapping("admin/pizze/{id}/create")
	public String storeSpecialOffer(
			Model model,
			@ModelAttribute SpecialOffer specialOffer) {
		specialOfferService.save(specialOffer);
		return "redirect:/admin";
	}
}
