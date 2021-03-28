package com.jyellow.tp2api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyellow.tp2api.model.Psychologist;

public interface PsychologistRepository extends JpaRepository<Psychologist, Integer>{
	Psychologist findByEmail(String email);
	Psychologist findByUserLoginDni(String dni);
	Psychologist findByCpsp(String cpsp);
}
