package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	List<Report> findByPatientUserLoginDni(String patientDni);
	List<Report> findByPatientUserLoginDniAndType(String patientDni, String type);
}