package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.TestCreateDTO;
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.dto.TestUpdateDTO;

public interface TestService {
	List<TestDTO> listByPatientDni(String patientDni);
	List<TestDTO> listByPatientDniAndFinished(String patientDni, boolean finished);
	TestDTO create(TestCreateDTO testCreateDTO);
	TestDTO cancel(int idTest);
	TestDTO update(TestUpdateDTO testUpdateDTO);
}
