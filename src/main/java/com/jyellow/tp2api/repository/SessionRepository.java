package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
	List<Session> findByPsychologistUserLoginDni(String psychologistDni);
	List<Session> findByPsychologistUserLoginDniAndAceptedAndFinished(String psychologistDni, boolean acepted, boolean finished);
	List<Session> findByPatientUserLoginDni(String patientDni);
	List<Session> findByPatientUserLoginDniAndAceptedAndFinished(String patientDni, boolean acepted, boolean finished);
}
