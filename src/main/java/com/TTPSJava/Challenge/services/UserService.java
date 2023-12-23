package com.TTPSJava.Challenge.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TTPSJava.Challenge.entities.User;
import com.TTPSJava.Challenge.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository= userRepository;
	}

	//listar todos los usuarios
	public List<User> getAllUser(){
		return userRepository.findAll();
	}

	//Crear un usuario
	public ResponseEntity<Object> newUser(User user) {
		Optional<User> res= userRepository.findUserByDniOrEmail(user.getDni(), user.getEmail());
		HashMap<String,Object> datos = new HashMap<String, Object>();
		
		if (res.isPresent()){
			datos.put("error", true);
			datos.put("message", "Ya existe un usuario con ese dni o email");
			return new ResponseEntity<Object>(
					datos,
					HttpStatus.CONFLICT
					);
		}
		user.setBalance((long) 0);
		userRepository.save(user);
		datos.put("data", user);
		datos.put("message", "Se creo usuario con exito");
		return new ResponseEntity<Object>(
				datos,
				HttpStatus.CREATED
				);
		
	}
	//login de un usuario con dni y password
	public ResponseEntity<Object> login (User user) {
		Optional<User> res= userRepository.findUserByDniAndPassword(user.getDni(), user.getPassword());
		HashMap<String,Object> datos = new HashMap<String, Object>();
		
		if (res.isEmpty()){
			datos.put("error", true);
			datos.put("message", "Datos erroneos");
			return new ResponseEntity<Object>(
					datos,
					HttpStatus.CONFLICT
					);
		}
		
		datos.put("data", res);
		datos.put("message", "Login con exito");
		return new ResponseEntity<Object>(
				res,
				HttpStatus.ACCEPTED
				);
		
	}
}
