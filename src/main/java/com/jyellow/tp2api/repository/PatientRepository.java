package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Patient findByUserLoginDni(String dni);
	Patient findByEmail(String email);
	Patient findByUserLoginDniAndPsychologistUserLoginDni(String dni, String psychologistDni);
	List<Patient> findByPsychologistUserLoginDni(String dni);
}
