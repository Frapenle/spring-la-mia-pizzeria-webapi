package org.java.pizza.service;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Ingredient;
import org.java.pizza.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
	@Autowired
	IngredientRepository ingredientRepository;
	
	public List<Ingredient> findAll(){
		return ingredientRepository.findAll();
	}
	public Ingredient save(Ingredient ingredient){
		return ingredientRepository.save(ingredient);
	}
	public Optional<Ingredient> findById(Integer id){
		return ingredientRepository.findById(id);
	}
	public void delete(Ingredient ingredient) {
		// TODO Auto-generated method stub
		ingredientRepository.delete(ingredient);
	}
}
