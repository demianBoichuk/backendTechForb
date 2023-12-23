package com.TTPSJava.Challenge.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TTPSJava.Challenge.entities.User;
import com.TTPSJava.Challenge.services.UserService;


@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin("*")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}

	//Obtener todos los usuarios
	@GetMapping()
	public List<User> getAllUsers(){
		return userService.getAllUser();
		
	}
	
	//Crear un usuario
	@PostMapping("/newUser")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		return this.userService.newUser(user);
		
	}
	
	//Login
	@PostMapping("/login")
	public ResponseEntity <?> login(@RequestBody User user){
		return userService.login(user);
	
	
		
		
}
}
