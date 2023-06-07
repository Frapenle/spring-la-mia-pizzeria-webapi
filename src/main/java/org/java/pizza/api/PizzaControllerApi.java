package org.java.pizza.api;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Ingredient;
import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.IngredientService;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class PizzaControllerApi {
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getPizze(){
		List<Pizza> pizze = pizzaService.findAll();
		if(pizze.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
	@GetMapping("/pizze/search")
	public ResponseEntity<List<Pizza>> getPizzeByName(@RequestParam(required = false) String name){
		List<Pizza> pizze = pizzaService.findByName(name);
		if(pizze.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
	@GetMapping("/pizze/{id}")
	public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
	    Optional<Pizza> optionalPizza = pizzaService.findById(id);
	    Pizza pizza = optionalPizza.get();
	    return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping("pizze/create")
	public ResponseEntity<Pizza> storePizza(@RequestBody Pizza pizza) {
	    pizza = pizzaService.save(pizza);
	    return new ResponseEntity<>(pizza, HttpStatus.CREATED);
	}
	
	@PutMapping("pizze/update/{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable("id") Integer id, @RequestBody Pizza pizza){
		pizza = pizzaService.save(pizza);
	    return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("/pizze/{id}")
	public ResponseEntity<Pizza> deletePizza(@PathVariable Integer id) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		if (optionalPizza.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);	
		}
		Pizza pizza = optionalPizza.get();
		pizzaService.delete(pizza);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	

}
