package org.java.pizza.service;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.SpecialOffer;
import org.java.pizza.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {
	
	@Autowired
	private SpecialOfferRepository specialOfferRepository;
	
	public List<SpecialOffer> findAll() {
		
		return specialOfferRepository.findAll();
	}
	
	public Optional<SpecialOffer> findById(Integer id) {
		
		return specialOfferRepository.findById(id);
	}
	
	public SpecialOffer save(SpecialOffer specialOffer) {
		
		return specialOfferRepository.save(specialOffer);
	}
}
