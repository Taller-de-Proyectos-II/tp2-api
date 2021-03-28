package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.model.Patient;

public interface PatientService {
	Patient validatePatientByDni(String dni);
	int create(PatientDTO patientDTO);
	int update(PatientDTO patientDTO);
	boolean assign(String patientDni, String psychologistDni);
	PatientDTO listByDni(String dni);
	List<PatientDTO> listByPsychologistDni(String psychologistDni);
	List<PatientDTO> listByGuardianDni(String guardianDni);
}
