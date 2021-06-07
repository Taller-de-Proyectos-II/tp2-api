package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.ScheduleDTO;
import com.jyellow.tp2api.dto.ScheduleUpdateDTO;
import com.jyellow.tp2api.service.ScheduleService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/schedule")
@Log4j2
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleServcie;

	@GetMapping(path = "/listSchedules/", produces = "application/json")
	public ResponseEntity<?> listSchedules() {
		log.info("ScheduleController: method listSchedules");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ScheduleDTO> schedulesDTO = scheduleServcie.listAll();
			responseDTO.setMessage("Horarios");
			responseDTO.setStatus(1);
			responseDTO.setSchedulesDTO(schedulesDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listSchedulesByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listSchedulesByPsychologistDni(@RequestParam String psychologistDni) {
		log.info("ScheduleController: method listSchedulesByPsychologistDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ScheduleDTO> schedulesDTO = scheduleServcie.listByPsychologistDni(psychologistDni);
			if (schedulesDTO == null || schedulesDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Horarios");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Horarios");
				responseDTO.setStatus(1);
				responseDTO.setSchedulesDTO(schedulesDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listSchedulesByPsychologistDniPatientView/", produces = "application/json")
	public ResponseEntity<?> listSchedulesByPsychologistDniPatientView(@RequestParam String psychologistDni,
			@RequestParam String date) {
		log.info("ScheduleController: method listSchedulesByPsychologistDniPatientView");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ScheduleDTO> schedulesDTO = scheduleServcie.listByPsychologistDniPatientView(psychologistDni, date);
			if (schedulesDTO == null || schedulesDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Horarios");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Horarios");
				responseDTO.setStatus(1);
				responseDTO.setSchedulesDTO(schedulesDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listSchedulesByPsychologistDniSessionsInSchedule/", produces = "application/json")
	public ResponseEntity<?> listSchedulesByPsychologistDniSessionsInSchedule(@RequestParam String psychologistDni,
			@RequestParam String date) {
		log.info("ScheduleController: method listSchedulesByPsychologistDniSessionsInSchedule");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ScheduleDTO> schedulesDTO = scheduleServcie.listByPsychologistDniSessionsInSchedule(psychologistDni,
					date);
			if (schedulesDTO == null || schedulesDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Horarios");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Horarios");
				responseDTO.setStatus(1);
				responseDTO.setSchedulesDTO(schedulesDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody ScheduleUpdateDTO scheduleUpdateDTO) {
		log.info("ScheduleController: method update");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ScheduleDTO> schedulesDTO = scheduleServcie.update(scheduleUpdateDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setSchedulesDTO(schedulesDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/createDefault/", produces = "application/json")
	public ResponseEntity<?> createDefault() {
		log.info("ScheduleController: method createDefault");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = scheduleServcie.createDefault();
			if (result == 1) {
				responseDTO.setMessage("Creación Default exitosa");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Los horarios por defecto ya fueron creados");
				responseDTO.setStatus(00);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
