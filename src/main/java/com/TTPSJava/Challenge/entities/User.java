package com.TTPSJava.Challenge.entities;


import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="usuarios")
//Clase entidad Usuario
public class User implements Serializable{
	private static final long serialVersionUID= 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUser")
	private Long idUser;
	@Column(unique = true)
	private Long dni;
	@Column
	private String password;
	@Column(unique = true)
	private String email;
	@Column
	private Long balance;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id_user",referencedColumnName = "idUser")
	private Set<CreditCard> creditCard;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Movements> movements;

}
