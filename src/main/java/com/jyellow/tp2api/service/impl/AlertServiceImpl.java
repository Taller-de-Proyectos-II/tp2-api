package com.jyellow.tp2api.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.AlertAnswerCreateDTO;
import com.jyellow.tp2api.dto.AlertAnswerDTO;
import com.jyellow.tp2api.dto.AlertCreateDTO;
import com.jyellow.tp2api.dto.AlertDTO;
import com.jyellow.tp2api.dto.AlertUpdateDTO;
import com.jyellow.tp2api.dto.SymptomDTO;
import com.jyellow.tp2api.model.Alert;
import com.jyellow.tp2api.model.AlertAnswer;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Symptom;
import com.jyellow.tp2api.repository.AlertAnswerRepository;
import com.jyellow.tp2api.repository.AlertRepository;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.SymptomRepository;
import com.jyellow.tp2api.service.AlertService;
import com.jyellow.tp2api.util.ScoreOperation;

@Service
public class AlertServiceImpl implements AlertService {
	@Autowired
	private AlertRepository alertRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private SymptomRepository symptomRepository;
	@Autowired
	private AlertAnswerRepository alertAnswerRepository;
	ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	@Override
	public List<AlertDTO> listByPatientDni(String patientDni) {
		List<Alert> alerts = alertRepository.findByPatientUserLoginDni(patientDni);
		Collections.reverse(alerts);
		List<AlertDTO> alertsDTO = new ArrayList<AlertDTO>();
		AlertDTO alertDTO = new AlertDTO();
		List<AlertAnswerDTO> alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
		AlertAnswerDTO alertAnswerDTO = new AlertAnswerDTO();
		SymptomDTO symptomDTO = new SymptomDTO();
		for (Alert alert : alerts) {
			alertDTO = new AlertDTO();
			alertDTO = modelMapper.map(alert, AlertDTO.class);
			alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
			alertAnswerDTO = new AlertAnswerDTO();
			symptomDTO = new SymptomDTO();
			for (AlertAnswer alertAnswer : alert.getAlertAnswers()) {
				alertAnswerDTO = new AlertAnswerDTO();
				symptomDTO = new SymptomDTO();
				alertAnswerDTO = modelMapper.map(alertAnswer, AlertAnswerDTO.class);
				symptomDTO = modelMapper.map(alertAnswer.getSymptom(), SymptomDTO.class);
				alertAnswerDTO.setSymptomDTO(symptomDTO);
				alertAnswersDTO.add(alertAnswerDTO);
			}
			alertDTO.setAlertAnswersDTO(alertAnswersDTO);
			alertsDTO.add(alertDTO);
		}
		return alertsDTO;
	}
	
	@Transactional
	@Override
	public List<AlertDTO> listByPatientDniAndDates(String patientDni, String startDate, String endDate) throws ParseException {
		List<Alert> alerts = alertRepository.findByPatientUserLoginDni(patientDni);
		List<AlertDTO> alertsDTO = new ArrayList<AlertDTO>();
		AlertDTO alertDTO = new AlertDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		AlertAnswerDTO alertAnswerDTO = new AlertAnswerDTO();
		List<AlertAnswerDTO> alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
		for (Alert alert : alerts) {
			if ((formatter.parse(startDate).before(formatter.parse(alert.getDate()))
					|| startDate.equals(alert.getDate()))
					&& (formatter.parse(endDate).after(formatter.parse(alert.getDate()))
							|| endDate.equals(alert.getDate()))) {
				alertDTO = new AlertDTO();
				alertDTO = modelMapper.map(alert, AlertDTO.class);
				alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
				alertAnswerDTO = new AlertAnswerDTO();
				for (AlertAnswer alertAnswer : alert.getAlertAnswers()) {
					alertAnswerDTO = new AlertAnswerDTO();
					alertAnswerDTO = modelMapper.map(alertAnswer, AlertAnswerDTO.class);
					alertAnswersDTO.add(alertAnswerDTO);
				}
				alertDTO.setAlertAnswersDTO(alertAnswersDTO);
				alertsDTO.add(alertDTO);
			}
		}
		return alertsDTO;
	}


	@Transactional
	@Override
	public List<AlertDTO> listByPatientDniAndImportant(String patientDni, boolean important) {
		List<Alert> alerts = alertRepository.findByPatientUserLoginDniAndImportant(patientDni, important);
		Collections.reverse(alerts);
		List<AlertDTO> alertsDTO = new ArrayList<AlertDTO>();
		AlertDTO alertDTO = new AlertDTO();
		List<AlertAnswerDTO> alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
		AlertAnswerDTO alertAnswerDTO = new AlertAnswerDTO();
		SymptomDTO symptomDTO = new SymptomDTO();
		for (Alert alert : alerts) {
			alertDTO = new AlertDTO();
			alertDTO = modelMapper.map(alert, AlertDTO.class);
			alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
			alertAnswerDTO = new AlertAnswerDTO();
			symptomDTO = new SymptomDTO();
			for (AlertAnswer alertAnswer : alert.getAlertAnswers()) {
				alertAnswerDTO = new AlertAnswerDTO();
				symptomDTO = new SymptomDTO();
				alertAnswerDTO = modelMapper.map(alertAnswer, AlertAnswerDTO.class);
				symptomDTO = modelMapper.map(alertAnswer.getSymptom(), SymptomDTO.class);
				alertAnswerDTO.setSymptomDTO(symptomDTO);
				alertAnswersDTO.add(alertAnswerDTO);
			}
			alertDTO.setAlertAnswersDTO(alertAnswersDTO);
			alertsDTO.add(alertDTO);
		}
		return alertsDTO;
	}

	@Transactional
	@Override
	public AlertDTO create(AlertCreateDTO alertCreateDTO) {
		Patient patient = patientRepository.findByUserLoginDni(alertCreateDTO.getPatientDni());
		Alert alert = new Alert();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		DateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
		alert.setDate(dateFormat.format(date));
		alert.setHour(dateFormatHour.format(date));
		alert.setPatient(patient);

		List<Symptom> symptoms = symptomRepository.findAll();
		List<AlertAnswer> alertAnswers = new ArrayList<AlertAnswer>();
		for (Symptom symptom : symptoms) {
			alertAnswers.add(new AlertAnswer(0, 0, symptom, alert));
		}
		List<AlertAnswer> alertAnswersAux = alertAnswerRepository.saveAll(alertAnswers);
		alert.setAlertAnswers(alertAnswersAux);
		alertRepository.save(alert);

		List<AlertAnswerDTO> alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
		AlertAnswerDTO alertAnswerDTO = new AlertAnswerDTO();
		SymptomDTO symptomDTO = new SymptomDTO();
		for (AlertAnswer alertAnswer : alertAnswersAux) {
			alertAnswerDTO = new AlertAnswerDTO();
			symptomDTO = new SymptomDTO();
			alertAnswerDTO = modelMapper.map(alertAnswer, AlertAnswerDTO.class);
			symptomDTO = modelMapper.map(alertAnswer.getSymptom(), SymptomDTO.class);
			alertAnswerDTO.setSymptomDTO(symptomDTO);
			alertAnswersDTO.add(alertAnswerDTO);
		}
		AlertDTO alertDTO = modelMapper.map(alert, AlertDTO.class);
		alertDTO.setAlertAnswersDTO(alertAnswersDTO);

		return alertDTO;
	}

	@Transactional
	@Override
	public AlertDTO update(AlertUpdateDTO alertUpdateDTO) {
		Alert alert = alertRepository.findById(alertUpdateDTO.getIdAlert()).get();
		List<AlertAnswer> alertAnswers = new ArrayList<AlertAnswer>();
		AlertAnswer alertAnswer = new AlertAnswer();
		for (AlertAnswerCreateDTO alertAnswerDTO : alertUpdateDTO.getAlertAnswersDTO()) {
			alertAnswer = alertAnswerRepository.findById(alertAnswerDTO.getIdAlertAnswer()).get();
			alertAnswer.setScore(alertAnswerDTO.getScore());
			alertAnswers.add(alertAnswer);
		}
		alert.setImportant(ScoreOperation.getDiagnosticAlert(alertAnswers));
		alertAnswerRepository.saveAll(alertAnswers);
		alertRepository.save(alert);

		List<AlertAnswerDTO> alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
		AlertAnswerDTO alertAnswerDTO = new AlertAnswerDTO();
		SymptomDTO symptomDTO = new SymptomDTO();
		for (AlertAnswer alertAnswerAux : alertAnswers) {
			alertAnswerDTO = new AlertAnswerDTO();
			symptomDTO = new SymptomDTO();
			alertAnswerDTO = modelMapper.map(alertAnswerAux, AlertAnswerDTO.class);
			symptomDTO = modelMapper.map(alertAnswerAux.getSymptom(), SymptomDTO.class);
			alertAnswerDTO.setSymptomDTO(symptomDTO);
			alertAnswersDTO.add(alertAnswerDTO);
		}
		AlertDTO alertDTO = modelMapper.map(alert, AlertDTO.class);
		alertDTO.setAlertAnswersDTO(alertAnswersDTO);

		return alertDTO;
	}
	
	@Transactional
	@Override
	public List<AlertDTO> listByPsychologistDniAndDates(String psychologistDni, String startDate, String endDate) throws ParseException {
		List<Alert> alerts = alertRepository.findByPatientPsychologistUserLoginDni(psychologistDni);
		List<AlertDTO> alertsDTO = new ArrayList<AlertDTO>();
		AlertDTO alertDTO = new AlertDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		AlertAnswerDTO alertAnswerDTO = new AlertAnswerDTO();
		List<AlertAnswerDTO> alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
		for (Alert alert : alerts) {
			if ((formatter.parse(startDate).before(formatter.parse(alert.getDate()))
					|| startDate.equals(alert.getDate()))
					&& (formatter.parse(endDate).after(formatter.parse(alert.getDate()))
							|| endDate.equals(alert.getDate()))) {
				alertDTO = new AlertDTO();
				alertDTO = modelMapper.map(alert, AlertDTO.class);
				alertAnswersDTO = new ArrayList<AlertAnswerDTO>();
				alertAnswerDTO = new AlertAnswerDTO();
				for (AlertAnswer alertAnswer : alert.getAlertAnswers()) {
					alertAnswerDTO = new AlertAnswerDTO();
					alertAnswerDTO = modelMapper.map(alertAnswer, AlertAnswerDTO.class);
					alertAnswersDTO.add(alertAnswerDTO);
				}
				alertDTO.setAlertAnswersDTO(alertAnswersDTO);
				alertsDTO.add(alertDTO);
			}
		}
		return alertsDTO;
	}
}
