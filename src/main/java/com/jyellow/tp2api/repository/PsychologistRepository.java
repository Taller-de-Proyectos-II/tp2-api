package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyellow.tp2api.model.Psychologist;

public interface PsychologistRepository extends JpaRepository<Psychologist, Integer>{
	Psychologist findByEmail(String email);
	Psychologist findByUserLoginDni(String dni);
	Psychologist findByCpsp(String cpsp);
	List<Psychologist> findByNamesContainingIgnoreCase(String names);
	List<Psychologist> findByLastNamesContainingIgnoreCase(String lastNames);
	List<Psychologist> findByNamesContainingIgnoreCaseAndLastNamesContainingIgnoreCase(String names, String lastNames);
}
