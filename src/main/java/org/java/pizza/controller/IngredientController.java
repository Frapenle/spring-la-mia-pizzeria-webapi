package org.java.pizza.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Ingredient;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.IngredientService;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public String getIngredientIndex(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		return "ingredients/index-ingredient";
	}
	
	@GetMapping("/create")
	public String createIngredient(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("ingredient", new Ingredient());
		model.addAttribute("pizze", pizze);
		return "ingredients/create-ingredient";
	}
	@PostMapping("/create")
	public String storeIngredient(@ModelAttribute Ingredient ingredient) {
		ingredientService.save(ingredient);
		for (Pizza pizza : ingredient.getPizze()) {
		    pizza.addIngredient(ingredient);
		    pizzaService.save(pizza);
		}
		return "redirect:/admin/ingredients";
		
	}	
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable Integer id) {
		Optional<Ingredient> optionalIngredient = ingredientService.findById(id);
		Ingredient ingredient = optionalIngredient.get();
		ingredientService.delete(ingredient);
		return "redirect:/admin/ingredients";
	}
	
	@GetMapping("/update/{id}")
	public String editIngredient(@PathVariable("id") Integer id, Model model) {
//		Optional<Ingredient> optionalIngredient = ingredientService.findById(id);
		List<Pizza> pizze = pizzaService.findAll();
//		Ingredient ingredient = optionalIngredient.get();
		Ingredient ingredient = ingredientService.findById(id).get();
		model.addAttribute("pizze", pizze);
		model.addAttribute("ingredient", ingredient);
		return "ingredients/update-ingredient";
	}
	@PostMapping("/update/{id}")
	public String update(
			@PathVariable Integer id,
			@ModelAttribute Ingredient ingredient,
			Model model) {
		ingredientService.save(ingredient);
		for(Pizza pizza : pizzaService.findAll()) {
			pizza.removeIngredient(ingredient);
			pizzaService.save(pizza);
		}
		for (Pizza pizza : ingredient.getPizze()) {
			
			pizza.addIngredient(ingredient);
			pizzaService.save(pizza);
		}
		return "redirect:/admin/ingredients";
	}
}
