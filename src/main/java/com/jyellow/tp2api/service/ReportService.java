package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.ReportCreateDTO;
import com.jyellow.tp2api.dto.ReportDTO;
import com.jyellow.tp2api.dto.ReportUpdateDTO;

public interface ReportService {
	ReportDTO create(ReportCreateDTO reportCreateDTO);
	ReportDTO update(ReportUpdateDTO reportUpdateDTO);
	ReportDTO delete(int idReport);
	List<ReportDTO> listByPatientDni(String patientDni);
}
