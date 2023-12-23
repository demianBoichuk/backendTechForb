package com.TTPSJava.Challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTPSJava.Challenge.entities.CreditCard;
import com.TTPSJava.Challenge.entities.User;
import com.TTPSJava.Challenge.services.CreditCardService;

@RestController
@RequestMapping(path = "api/v1/ccard")
@CrossOrigin("*")
public class CreditCardController {
	private final CreditCardService creditCardService;

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
		
	}
	
	//obtener todas las tarjetas de credito
	@GetMapping()
	public List<CreditCard> getAllCreditcard(){
		return creditCardService.getAllCreditCard();
		
	}
	
	
	//crear una nueva tarjeta de credito al usuario id
	@PostMapping("/newCreditCard/{id}")
	public ResponseEntity<Object> saveCreditCard(@RequestBody CreditCard creditCard,@PathVariable Long id) {
		return creditCardService.newCreditCard(creditCard, id);		
	}

}
