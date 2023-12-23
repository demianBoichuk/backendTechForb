package com.TTPSJava.Challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TTPSJava.Challenge.entities.Movements;


public interface MovementsRepository extends JpaRepository<Movements, Long> {
	
	//obtener los movimientos recientes para un usuario en especifico
	@Query("SELECT m FROM Movements m WHERE m.user.idUser = :id ORDER BY m.date DESC LIMIT 5")
	public List<Movements> findRecentsMovements(@Param("id") Long id);
	
	
	//obtener todos los movimientos de un usuario en especifico
	@Query("SELECT m FROM Movements m WHERE m.user.idUser = :id ORDER BY m.date DESC")
	public List<Movements> findAllMyMovements(@Param("id") Long id);
}