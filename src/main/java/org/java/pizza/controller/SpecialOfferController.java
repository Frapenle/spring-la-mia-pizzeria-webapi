package org.java.pizza.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpecialOfferController {
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@GetMapping
	public String getSpecialOffer(Model model) {
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		model.addAttribute("specialOffers", specialOffers);
		return "/";
	}
//	create
	@GetMapping("/offer/create")
	public String createSpecialOffer(Model model, @RequestParam(name = "id") Integer pizzaId) {
		List<Pizza> pizza = pizzaService.findAll();
		model.addAttribute("specialOffer", new SpecialOffer());
		model.addAttribute("pizza", pizza);
		return "specialOffers/spo-create";
	}
	@PostMapping("/offer/create")
	public String storeSpecialOffer(
			Model model,
			@ModelAttribute SpecialOffer specialOffer) {
		specialOfferService.save(specialOffer);
		return "redirect:/admin";
	}
//	update
	@GetMapping("/offer/edit/{id}")
	public String edit(Model model, 
			@PathVariable("id") Integer id) {
		Optional<SpecialOffer> specialOfferOpt = specialOfferService.findById(id);
		SpecialOffer specialOffer = specialOfferOpt.get();
		model.addAttribute("specialOffer", specialOffer);
		return "specialOffers/spo-update";
	}
	@PostMapping("/offer/edit/{id}")
	public String update(Model model, @PathVariable("id") Integer id, @ModelAttribute SpecialOffer specialOffer) {
		specialOfferService.save(specialOffer);
		return "redirect:/admin/pizze/" + specialOffer.getPizza().getId();
	}

}
