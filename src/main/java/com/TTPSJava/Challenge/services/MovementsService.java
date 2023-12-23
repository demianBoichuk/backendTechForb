package com.TTPSJava.Challenge.services;

import java.util.Date;
import java.util.HashMap;
import com.TTPSJava.Challenge.entities.Movements.TypeMovement;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TTPSJava.Challenge.repositories.MovementsRepository;
import com.TTPSJava.Challenge.repositories.UserRepository;

import jakarta.transaction.Transactional;

import com.TTPSJava.Challenge.entities.Movements;
import com.TTPSJava.Challenge.entities.User;
@Service
public class MovementsService {
	
	private final MovementsRepository movementsRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public MovementsService(MovementsRepository movementsRepository, UserRepository userRepository) {
		this.movementsRepository = movementsRepository;
		this.userRepository = userRepository;
		
	}
	
	//listar todos los movimientos
	public List<Movements> getAllMovements(){
		return movementsRepository.findAll();
	}
	public ResponseEntity<Object> getAllMyMovements(Long id) {
	    List<Movements> userMovements = movementsRepository.findAllMyMovements(id);
	    HashMap<String, Object> datos = new HashMap<>();

	    if (userMovements.isEmpty()) {
	        datos.put("error", true);
	        datos.put("message", "El usuario no tiene movimientos");
	        return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<>(userMovements, HttpStatus.OK);
	}
	
	
	//listar los ultimos movimientos de un usuario
	public ResponseEntity<Object> getLastMovements(Long id){
		List<Movements> userMovements = movementsRepository.findRecentsMovements(id);
		HashMap<String, Object> datos = new HashMap<>();

		    if (userMovements.isEmpty()) {
		        datos.put("error", true);
		        datos.put("message", "El usuario no tiene movimientos");
		        return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
		    }

		    return new ResponseEntity<>(userMovements, HttpStatus.OK);
		}
	
	
	//agregar un movimiento a un usuario
	@Transactional
	public ResponseEntity<Object> addMovement(Long userId, Long balance, TypeMovement typeMovement) {
	    User user = userRepository.findById(userId).get();
	    HashMap<String,Object> datos = new HashMap<String, Object>();
	    Date date = new Date();
	    Movements movement = new Movements(null, balance, date, typeMovement);
	    movement.setUser(user);
	    movement.setTypeMovement(typeMovement);
	    movement.setDate(date);
	    if (typeMovement == TypeMovement.INGRESO) {
	        movement.getIncomes().add(balance);
	        user.setBalance(user.getBalance()+balance);
	    } else {
	    	var total=user.getBalance();
	    	if((total-balance)>=0) {
	    	
	    		movement.getExpenses().add(balance);
	    		user.setBalance(user.getBalance()-balance);
	    	}else {
	    		return new ResponseEntity<Object>(
	    				" egreso incorrecto. saldo insuficiente",
	    				HttpStatus.CONFLICT
	    				);
	    	}
	    }
	    movementsRepository.save(movement);
	    return new ResponseEntity<Object>(
	    		typeMovement + " correcto",
				HttpStatus.CREATED
				);
	  
	}
	
	
	//hacer una transferencia de un usuario a otro sabiendo su dni
	@Transactional
	public ResponseEntity<Object> makeTransfer(Long userId, Long dni, Long balance) {
	    User user = userRepository.findById(userId).get();
	    User receiver= userRepository.findUserByDni(dni).orElse(null);
	    HashMap<String,Object> datos = new HashMap<String, Object>();
	    if (receiver==null) {
	        datos.put("error", true);
	        datos.put("message", "El usuario con dni "+dni+ " no esta registrado en el sistema");
	        return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
	    }
	    var total=user.getBalance();
	    if((total-balance)>=0) {
	    	TypeMovement typeMovement = TypeMovement.EGRESO;
	    	Date date = new Date();
	    	Movements movement = new Movements(null, balance, date, typeMovement);
	    	movement.setUser(user);
		    movement.setTypeMovement(typeMovement);
		    movement.setDate(date);
		    user.setBalance(user.getBalance()-balance);
		    movement.getExpenses().add(balance);
		    movementsRepository.save(movement);
		    
		    TypeMovement typeMovement2 = TypeMovement.INGRESO;
		    Movements movementReceiver = new Movements(null, balance, date, typeMovement2);
		    movementReceiver.setUser(receiver);
		    movementReceiver.setTypeMovement(typeMovement2);
		    movementReceiver.setDate(date);
		    receiver.setBalance(receiver.getBalance()+balance);
		    movementReceiver.getIncomes().add(balance);
		    movementsRepository.save(movementReceiver);
		   
		    return new ResponseEntity<Object>(
		    		" Transferencia correcta",
					HttpStatus.CREATED
					);
	    }else {
	    	return new ResponseEntity<Object>(
    				" Transferencia incorrecta. saldo insuficiente",
    				HttpStatus.CONFLICT
    				);
	    
	    }
	}


}
