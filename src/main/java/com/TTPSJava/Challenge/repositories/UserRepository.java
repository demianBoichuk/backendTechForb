package com.TTPSJava.Challenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TTPSJava.Challenge.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findUserByDniOrEmail(Long dni, String email);
	Optional<User> findUserByDniAndPassword(Long dni,String password);
	Optional<User> findUserByDni(Long dni);
}
