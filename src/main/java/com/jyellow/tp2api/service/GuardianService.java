package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.GuardianDTO;

public interface GuardianService {
	
	int create(GuardianDTO guardianDTO);
	int update(GuardianDTO guardianDTO);
	List<GuardianDTO> listByPatientDni(String patientDni);
	GuardianDTO listByDniAndPatientDni(String dni, String patientDni);
}
