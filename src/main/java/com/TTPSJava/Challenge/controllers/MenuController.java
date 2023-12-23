package com.TTPSJava.Challenge.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTPSJava.Challenge.entities.Menu;
import com.TTPSJava.Challenge.services.MenuService;

@RestController
@RequestMapping(path = "api/v1/menu")
@CrossOrigin("*")
public class MenuController {

	
	private final MenuService menuservice;
	
	@Autowired
	public MenuController(MenuService menuservice) {
		this.menuservice = menuservice;
		
	}
	
	//Obtener los campos del menu lateral para el front pasando el id del menu
	//Puede haber mas de una configuracion, por eso se diferencian por id
	@GetMapping("{id}")
	public ResponseEntity<Object> getMenu(@PathVariable Long id) {
		return menuservice.getMenu(id);
	}

	//Agregar una opcion al menu con un id en especifico
	@PostMapping("/add/{id}")
	public Object addMovement(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {
	    String operation = (requestBody.get("operation") != null) ? ( requestBody.get("operation")).toString() : null;
	    return menuservice.addOperation(id, operation);
	}
}
