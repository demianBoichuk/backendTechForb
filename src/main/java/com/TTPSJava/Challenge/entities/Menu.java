package com.TTPSJava.Challenge.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="menu")
//Clase entidad Menu
public class Menu implements Serializable{
	private static final long serialVersionUID= 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMenu")
	private Long idMenu;
	
	@Column(name="options")
	private LinkedHashSet<String> options;

	
}
