package com.TTPSJava.Challenge.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TTPSJava.Challenge.entities.CreditCard;
import com.TTPSJava.Challenge.entities.User;
import com.TTPSJava.Challenge.repositories.CreditCardRepository;
import com.TTPSJava.Challenge.repositories.UserRepository;

@Service
public class CreditCardService {
	private final CreditCardRepository creditCardRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public CreditCardService(CreditCardRepository creditCardRepository, UserRepository userRepository) {
		this.creditCardRepository = creditCardRepository;
		this.userRepository=userRepository;
		
	}
	
	//listar todas las tarjetas de credito de la bbdd
	public List<CreditCard> getAllCreditCard(){
		return creditCardRepository.findAll();
	}
	
	
	//agregar una tarjeta al usuario 
	public ResponseEntity<Object> newCreditCard(CreditCard creditCard, Long id) {
		
		HashMap<String,Object> datos = new HashMap<String, Object>();
		Optional<User> user= userRepository.findById(id);
		if (user.isPresent()){
			user.get().getCreditCard().add(creditCard);
			creditCardRepository.save(creditCard);
			datos.put("data", user);
			datos.put("message", "Se agrego la tarjeta "+ creditCard.getNumber());
			return new ResponseEntity<Object>(
					datos,
					HttpStatus.CREATED
					);
		}else {
			datos.put("error", true);
			datos.put("message", "Error de usuario");
			return new ResponseEntity<Object>(
					datos,
					HttpStatus.CONFLICT
					);
		}
	}
	
	
	//listar todas las tarjetas del usuario sabiendo su id
	public List<CreditCard> getAllMyCreditCard(Long id){
		HashMap<String,Object> datos = new HashMap<String, Object>();
		Set<CreditCard> allCreditCard = userRepository.findById(id).get().getCreditCard();
		return (List<CreditCard>) allCreditCard;
	}
}
