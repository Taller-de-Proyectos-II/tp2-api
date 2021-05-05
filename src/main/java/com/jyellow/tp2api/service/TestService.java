package com.jyellow.tp2api.service;

import java.text.ParseException;
import java.util.List;

import com.jyellow.tp2api.dto.TestCreateDTO;
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.dto.TestDashboardDTO;
import com.jyellow.tp2api.dto.TestUpdateDTO;

public interface TestService {
	List<TestDTO> listByPatientDni(String patientDni);
	List<TestDTO> listByPatientDniAndFinished(String patientDni, boolean finished);
	TestDTO create(TestCreateDTO testCreateDTO);
	TestDTO cancel(int idTest);
	TestDTO update(TestUpdateDTO testUpdateDTO);
	List<TestDashboardDTO> listByPatientDniAndDates(String patientDni, String startDate, String endDate) throws ParseException;
	List<TestDTO> listByPatientDniAndTestType(String patientDni, String testType);
}
