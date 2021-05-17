package com.jyellow.tp2api.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.dto.SessionCreateDTO;
import com.jyellow.tp2api.dto.SessionDTO;
import com.jyellow.tp2api.dto.SessionUpdateDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.Schedule;
import com.jyellow.tp2api.model.Session;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.ScheduleRepository;
import com.jyellow.tp2api.repository.SessionRepository;
import com.jyellow.tp2api.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private PsychologistRepository psychologistRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public List<SessionDTO> listByPsychologistUserLoginDni(String psychologistDni) {
		List<Session> sessions = sessionRepository.findByPsychologistUserLoginDni(psychologistDni);
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		PatientDTO patientDTO = new PatientDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Session session : sessions) {
			sessionDTO = new SessionDTO();
			patientDTO = new PatientDTO();
			userLoginDTO = new UserLoginDTO();
			userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
			patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
			patientDTO.setUserLoginDTO(userLoginDTO);
			sessionDTO = modelMapper.map(session, SessionDTO.class);
			sessionDTO.setPatient(patientDTO);
			sessionsDTO.add(sessionDTO);
		}

		return sessionsDTO;
	}

	@Transactional
	@Override
	public List<SessionDTO> listByPsychologistUserLoginDniAndAceptedAndFinished(String psychologistDni, boolean acepted,
			boolean finished) {
		List<Session> sessions = sessionRepository.findByPsychologistUserLoginDniAndAceptedAndFinished(psychologistDni,
				acepted, finished);
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		PatientDTO patientDTO = new PatientDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Session session : sessions) {
			sessionDTO = new SessionDTO();
			patientDTO = new PatientDTO();
			userLoginDTO = new UserLoginDTO();
			userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
			patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
			patientDTO.setUserLoginDTO(userLoginDTO);
			sessionDTO = modelMapper.map(session, SessionDTO.class);
			sessionDTO.setPatient(patientDTO);
			sessionsDTO.add(sessionDTO);
		}

		return sessionsDTO;
	}

	@Transactional
	@Override
	public List<SessionDTO> listByPatientUserLoginDni(String patientDni) {
		List<Session> sessions = sessionRepository.findByPatientUserLoginDni(patientDni);
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		PatientDTO patientDTO = new PatientDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Session session : sessions) {
			sessionDTO = new SessionDTO();
			patientDTO = new PatientDTO();
			userLoginDTO = new UserLoginDTO();
			userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
			patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
			patientDTO.setUserLoginDTO(userLoginDTO);
			sessionDTO = modelMapper.map(session, SessionDTO.class);
			sessionDTO.setPatient(patientDTO);
			sessionsDTO.add(sessionDTO);
		}

		return sessionsDTO;
	}

	@Transactional
	@Override
	public List<SessionDTO> listByPatientUserLoginDniAndAceptedAndFinished(String patientDni, boolean acepted,
			boolean finished) {
		List<Session> sessions = sessionRepository.findByPatientUserLoginDniAndAceptedAndFinished(patientDni, acepted,
				finished);
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		PatientDTO patientDTO = new PatientDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Session session : sessions) {
			sessionDTO = new SessionDTO();
			patientDTO = new PatientDTO();
			userLoginDTO = new UserLoginDTO();
			userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
			patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
			patientDTO.setUserLoginDTO(userLoginDTO);
			sessionDTO = modelMapper.map(session, SessionDTO.class);
			sessionDTO.setPatient(patientDTO);
			sessionsDTO.add(sessionDTO);
		}

		return sessionsDTO;
	}

	@Transactional
	@Override
	public String create(SessionCreateDTO sessionCreateDTO) throws ParseException {
		Session session = new Session();
		Patient patient = patientRepository.findByUserLoginDni(sessionCreateDTO.getPatientDni());
		if (patient == null)
			return "El paciente no está registrado";
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(sessionCreateDTO.getPsychologistDni());
		if (psychologist == null)
			return "El psicólogo no está registrado";
		List<Session> sessions = sessionRepository
				.findByPsychologistUserLoginDni(sessionCreateDTO.getPsychologistDni());
		session.setAcepted(false);
		session.setDate(sessionCreateDTO.getDate());
		session.setFinished(false);
		session.setPatient(patient);
		session.setPsychologist(psychologist);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = formatter.parse(sessionCreateDTO.getDate());
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day == 1)
			day = 7;
		else
			day--;

		int hour = sessionCreateDTO.getHour();
		Schedule schedule = scheduleRepository.findByDayAndHour(day, hour);
		if (schedule == null)
			return "Horario fuera del rango de atención general";
		boolean inRange = psychologist.getSchedules().contains(schedule);
		if (inRange == false)
			return "Horario fuera del rango de atención del psicólogo";
		for (Session sessionAux : sessions) {
			if (sessionAux.getSchedule().getDay() == day && sessionAux.getSchedule().getHour() == hour
					&& sessionCreateDTO.getDate().equals(session.getDate()))
				return "Horario ya reservado";
		}
		session.setSchedule(schedule);
		sessionRepository.save(session);
		return "#" + session.getIdSession();
	}

	@Transactional
	@Override
	public SessionDTO updateAcepted(SessionUpdateDTO sessionUpdateDTO) {
		Session session = sessionRepository.findById(sessionUpdateDTO.getIdSession()).get();
		session.setAcepted(true);
		session.setMeetingLink(sessionUpdateDTO.getMeetingLink());
		sessionRepository.save(session);

		UserLoginDTO userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
		PatientDTO patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
		patientDTO.setUserLoginDTO(userLoginDTO);
		SessionDTO sessionDTO = modelMapper.map(session, SessionDTO.class);
		sessionDTO.setPatient(patientDTO);
		return sessionDTO;
	}

	@Transactional
	@Override
	public SessionDTO updateFinished(int idSession) {
		Session session = sessionRepository.findById(idSession).get();
		session.setFinished(true);
		sessionRepository.save(session);

		UserLoginDTO userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
		PatientDTO patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
		patientDTO.setUserLoginDTO(userLoginDTO);
		SessionDTO sessionDTO = modelMapper.map(session, SessionDTO.class);
		sessionDTO.setPatient(patientDTO);
		return sessionDTO;
	}

	@Transactional
	@Override
	public SessionDTO listById(int idSession) {
		Session session = sessionRepository.findById(idSession).get();

		UserLoginDTO userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
		PatientDTO patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
		patientDTO.setUserLoginDTO(userLoginDTO);
		SessionDTO sessionDTO = modelMapper.map(session, SessionDTO.class);
		sessionDTO.setPatient(patientDTO);
		return sessionDTO;
	}

	@Transactional
	@Override
	public List<SessionDTO> listByPsychologistUserLoginDniAndAceptedAndFinishedAndPatientUserLoginDni(
			String psychologistDni, boolean acepted, boolean finished, String patientDni) {
		List<Session> sessions = sessionRepository
				.findByPsychologistUserLoginDniAndAceptedAndFinishedAndPatientUserLoginDni(psychologistDni, acepted,
						finished, patientDni);
		List<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		PatientDTO patientDTO = new PatientDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Session session : sessions) {
			sessionDTO = new SessionDTO();
			patientDTO = new PatientDTO();
			userLoginDTO = new UserLoginDTO();
			userLoginDTO = modelMapper.map(session.getPatient().getUserLogin(), UserLoginDTO.class);
			patientDTO = modelMapper.map(session.getPatient(), PatientDTO.class);
			patientDTO.setUserLoginDTO(userLoginDTO);
			sessionDTO = modelMapper.map(session, SessionDTO.class);
			sessionDTO.setPatient(patientDTO);
			sessionsDTO.add(sessionDTO);
		}

		return sessionsDTO;
	}

}
