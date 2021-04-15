package com.jyellow.tp2api.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.ScheduleDTO;
import com.jyellow.tp2api.dto.ScheduleUpdateDTO;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.Schedule;
import com.jyellow.tp2api.model.Session;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.ScheduleRepository;
import com.jyellow.tp2api.repository.SessionRepository;
import com.jyellow.tp2api.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private PsychologistRepository psychologistRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public List<ScheduleDTO> listAll() {
		List<Schedule> schedules = scheduleRepository.findAll();
		return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<ScheduleDTO> listByPsychologistDni(String psychologistDni) {
		List<Schedule> schedules = scheduleRepository.findByPsychologistsUserLoginDni(psychologistDni);
		return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<ScheduleDTO> update(ScheduleUpdateDTO scheduleUpdateDTO) {
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(scheduleUpdateDTO.getPsychologistDni());
		Schedule schedule = new Schedule();
		List<Schedule> schedules = new ArrayList<Schedule>();
		for (int idSchedule : scheduleUpdateDTO.getSchedules()) {
			schedule = scheduleRepository.findById(idSchedule).get();
			schedules.add(schedule);
		}
		psychologist.setSchedules(schedules);
		psychologistRepository.save(psychologist);
		return schedules.stream().map(scheduleAux -> modelMapper.map(scheduleAux, ScheduleDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<ScheduleDTO> listByPsychologistDniPatientView(String psychologistDni, String dateRequest) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		List<Schedule> schedules = scheduleRepository.findByPsychologistsUserLoginDni(psychologistDni);
		List<Session> sessions = sessionRepository.findByPsychologistUserLoginDni(psychologistDni);
		for(Session session: sessions) {
			
			Date date = formatter.parse(session.getDate());
			cal.setTime(date);
			int weekSession = cal.get(Calendar.WEEK_OF_YEAR);
			if(cal.get(Calendar.DAY_OF_WEEK) == 1) weekSession++; 
			date = formatter.parse(dateRequest);
			cal.setTime(date);
			int weekSearch = cal.get(Calendar.WEEK_OF_YEAR);
			if(cal.get(Calendar.DAY_OF_WEEK) == 1) weekSearch++; 
			
			if(schedules.contains(session.getSchedule()) && weekSession == weekSearch) {
				schedules.remove(session.getSchedule());
			}
		}
		return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
				.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public List<ScheduleDTO> listByPsychologistDniSessionsInSchedule(String psychologistDni, String dateRequest) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		List<Schedule> schedules = new ArrayList<Schedule>();
		List<Session> sessions = sessionRepository.findByPsychologistUserLoginDni(psychologistDni);
		for(Session session: sessions) {
			
			Date date = formatter.parse(session.getDate());
			cal.setTime(date);
			int weekSession = cal.get(Calendar.WEEK_OF_YEAR);
			if(cal.get(Calendar.DAY_OF_WEEK) == 1) weekSession++; 
			date = formatter.parse(dateRequest);
			cal.setTime(date);
			int weekSearch = cal.get(Calendar.WEEK_OF_YEAR);
			if(cal.get(Calendar.DAY_OF_WEEK) == 1) weekSearch++; 
			
			if(weekSession == weekSearch) {
				schedules.add(session.getSchedule());
			}
		}
		return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
				.collect(Collectors.toList());
	}
}
