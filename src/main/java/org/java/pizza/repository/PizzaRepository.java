package org.java.pizza.repository;



import java.util.List;

import org.java.pizza.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository <Pizza, Integer>{
	public List<Pizza> findByNameContaining(String name);
}
