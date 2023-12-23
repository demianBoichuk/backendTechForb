package com.TTPSJava.Challenge.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="movimientos")
//Clase entidad Movimientos
public class Movements implements Serializable {
	
	public Movements(Object object, Long balance, Date date2, TypeMovement typeMovement2) {
		this.expenses=new ArrayList<Long>();
		this.incomes=new ArrayList<Long>();
	}
	 public Movements() {
	        this.incomes = new ArrayList<>();
	        this.expenses = new ArrayList<>();
	    }
	private static final long serialVersionUID= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idMovimiento")
	private Long id;
	
	@Column(name="ingresos")
	private List<Long>incomes;
	
	@Column(name="egresos")
	private List<Long> expenses;
	
	@Column(name = "fecha")
	private Date date;
	    
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimiento")
	private TypeMovement typeMovement;

	public enum TypeMovement {
	    INGRESO,
	    EGRESO
	}
	@ManyToOne
    @JoinColumn(name="id_user") 
    private User user;

}
