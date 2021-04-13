package com.jyellow.tp2api.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.ReportCreateDTO;
import com.jyellow.tp2api.dto.ReportDTO;
import com.jyellow.tp2api.dto.ReportUpdateDTO;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.Report;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.ReportRepository;
import com.jyellow.tp2api.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PsychologistRepository psychologistRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public ReportDTO create(ReportCreateDTO reportCreateDTO) {
		Patient patient = patientRepository.findByUserLoginDni(reportCreateDTO.getPatientDni());
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(reportCreateDTO.getPsychologistDni());
		Report report = new Report();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String strDate = dateFormat.format(date);
		report.setDate(strDate);
		report.setDescription(reportCreateDTO.getDescription());
		report.setPatient(patient);
		report.setPsychologist(psychologist);
		reportRepository.save(report);
		return modelMapper.map(report, ReportDTO.class);
	}

	@Transactional
	@Override
	public ReportDTO update(ReportUpdateDTO reportUpdateDTO) {
		Report report = reportRepository.findById(reportUpdateDTO.getIdReport()).get();
		report.setDescription(reportUpdateDTO.getDescription());
		reportRepository.save(report);
		return modelMapper.map(report, ReportDTO.class);
	}

	@Transactional
	@Override
	public ReportDTO delete(int idReport) {
		Report report = reportRepository.findById(idReport).get();
		reportRepository.deleteById(idReport);
		return modelMapper.map(report, ReportDTO.class);
	}

	@Transactional
	@Override
	public List<ReportDTO> listByPatientDni(String patientDni) {
		List<Report> reports = reportRepository.findByPatientUserLoginDni(patientDni);
		return reports.stream().map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());
	}
}
