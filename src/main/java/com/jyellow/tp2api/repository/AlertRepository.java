package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
	List<Alert> findByPatientUserLoginDni(String patientDni);
	List<Alert> findByPatientUserLoginDniAndImportant(String patientDni, boolean important);
}
