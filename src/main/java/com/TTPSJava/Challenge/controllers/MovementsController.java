package com.TTPSJava.Challenge.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTPSJava.Challenge.entities.Movements;
import com.TTPSJava.Challenge.entities.Movements.TypeMovement;
import com.TTPSJava.Challenge.services.MovementsService;

@RestController
@RequestMapping(path = "api/v1/movements")
@CrossOrigin("*")

public class MovementsController {
	private final MovementsService movementsService;
	
	@Autowired
	public MovementsController(MovementsService movementsService) {
		this.movementsService = movementsService;
		// TODO Auto-generated constructor stub
	}
	
	//Obtener todos los movimientos(ingresos y egresos)
	@GetMapping("/all")
	public List<Movements> getAllMovements() {
		return movementsService.getAllMovements();
	}
	
	
	//Obtener todos los movimientos de un usuario (id es el id del usario en la bbdd)
	@GetMapping("/all/{id}")
	public ResponseEntity<?> getAllMyMovements(@PathVariable Long id) {
		return movementsService.getAllMyMovements(id);
	}
	
	//Obtener todos los movimientos recientes de un usuario (id es el id del usario en la bbdd)
	@GetMapping("/recents/{id}")
	public ResponseEntity<?> getLastMovements(@PathVariable Long id) {
		return movementsService.getLastMovements(id);
	}
	
	//Agregar un moviemiento a un usuario (id es el id del usario en la bbdd)
	@PostMapping("/addMovement/{userId}")
	public Object addMovement(@PathVariable Long userId, @RequestBody Map<String, Object> requestBody) {
	    Long balance = (requestBody.get("balance") != null) ? ((Number) requestBody.get("balance")).longValue() : null;
	    TypeMovement typeMovement = TypeMovement.valueOf((String) requestBody.get("typeMovement"));

	    return movementsService.addMovement(userId, balance, typeMovement);
	}
	
	//Hacer una transferencia (id es el id del usario en la bbdd)
	@PostMapping("/makeTransfer/{userId}")
	public ResponseEntity<Object> makeTransfer(@PathVariable Long userId,@RequestBody Map<String, Object> requestBody) {
		Long balance = (requestBody.get("balance") != null) ? ((Number) requestBody.get("balance")).longValue() : null;
		Long dni = (requestBody.get("dni") != null) ? ((Number) requestBody.get("dni")).longValue() : null;
		return movementsService.makeTransfer(userId, dni, balance);
	}
}