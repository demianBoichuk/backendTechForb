package com.TTPSJava.Challenge.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TTPSJava.Challenge.entities.Menu;
import com.TTPSJava.Challenge.entities.Movements;
import com.TTPSJava.Challenge.entities.User;
import com.TTPSJava.Challenge.entities.Movements.TypeMovement;
import com.TTPSJava.Challenge.repositories.MenuRepository;

import jakarta.transaction.Transactional;

@Service
public class MenuService{

	private final MenuRepository menuRepository;
	
	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
		// TODO Auto-generated constructor stub
	}
	
	//obtener los datos de las opciones del menu
	public ResponseEntity<Object> getMenu(Long id) {
		Optional<Menu> menu= menuRepository.findById(id);
		HashMap<String, Object> datos = new HashMap<>();
	    if (menu.isEmpty()) {
	        datos.put("error", true);
	        datos.put("message", "El menu esta vacio");
	        return new ResponseEntity<>(datos, HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(menu.get().getOptions(), HttpStatus.OK);
	}
	
	//agregar una nueva opcion a la lista del menu
	@Transactional
	public ResponseEntity<Object> addOperation(Long id,String operation) {
	    Optional<Menu> menu = menuRepository.findById(id);
	    HashMap<String,Object> datos = new HashMap<String, Object>();
	    if (menu.isPresent()) {
	    	menu.get().getOptions().add(operation);
		    menuRepository.save(menu.get());
		    return new ResponseEntity<Object>(
		    		" operacion agregada",
					HttpStatus.CREATED
					);
	    }else {
	    	
	    	return new ResponseEntity<Object>(
	    				"menu inexistente",
	    				HttpStatus.CONFLICT
	    				);
	    	}
	    
}
	

}
