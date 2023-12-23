package com.TTPSJava.Challenge.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="tarjeta")
//Clase entidad Tarjeta de credito
public class CreditCard implements Serializable{
		private static final long serialVersionUID= 1L;


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="idCreditCard")
		private Long id;
		
		@Column
		private String owner;
		
		@Column
		private Long number;
				
		@Column(length = 3)
		private Long security;
		
		@Column(length = 4)
		private String expiration;
		
		

	}