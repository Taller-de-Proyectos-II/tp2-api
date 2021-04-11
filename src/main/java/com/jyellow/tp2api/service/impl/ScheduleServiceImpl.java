package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
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
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.ScheduleRepository;
import com.jyellow.tp2api.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private PsychologistRepository psychologistRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ScheduleDTO> listAll() {
		List<Schedule> schedules = scheduleRepository.findAll();
		return schedules.stream().map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
				.collect(Collectors.toList());
	}

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
}
