package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
	List<Test> findByPatientUserLoginDni(String patientDni);
	List<Test> findByPatientUserLoginDniAndFinished(String patientDni, boolean finished);
}
