package com.jyellow.tp2api.service;

import java.text.ParseException;
import java.util.List;

import com.jyellow.tp2api.dto.AlertCreateDTO;
import com.jyellow.tp2api.dto.AlertDTO;
import com.jyellow.tp2api.dto.AlertUpdateDTO;

public interface AlertService {
	List<AlertDTO> listByPatientDni(String patientDni);
	List<AlertDTO> listByPatientDniAndImportant(String patientDni, boolean finished);
	AlertDTO create(AlertCreateDTO testCreateDTO);
	AlertDTO update(AlertUpdateDTO alertUpdateDTO);
	List<AlertDTO> listByPatientDniAndDates(String patientDni, String startDate, String endDate) throws ParseException;
}
