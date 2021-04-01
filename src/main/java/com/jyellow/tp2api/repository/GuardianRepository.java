package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Guardian;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
	Guardian findByEmail(String email);
	Guardian findByDni(String dni);
	List<Guardian> findByPatientUserLoginDni(String patientDni);
	Guardian findByDniAndPatientUserLoginDni(String dni, String patientDni);
}
