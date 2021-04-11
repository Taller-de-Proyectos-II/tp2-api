package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.ScheduleDTO;
import com.jyellow.tp2api.dto.ScheduleUpdateDTO;

public interface ScheduleService {
	List<ScheduleDTO> listAll();
	List<ScheduleDTO> listByPsychologistDni(String psychologistDni);
	List<ScheduleDTO> update(ScheduleUpdateDTO scheduleUpdateDTO);
}
