package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Patient findByDni(String dni);
	Patient findByEmail(String email);
	List<Patient> findByPsychologistUserLoginDni(String dni);
	List<Patient> findByGuardianUserLoginDni(String dni);
}
