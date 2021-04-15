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

@CrossOrigin
@RestController
@RequestMapping(path = "/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleServcie;

	@GetMapping(path = "/listSchedules/", produces = "application/json")
	public ResponseEntity<?> listSchedules() {
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
	public ResponseEntity<?> listSchedulesByPsychologistDniPatientView(@RequestParam String psychologistDni, @RequestParam String date) {
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
	public ResponseEntity<?> listSchedulesByPsychologistDniSessionsInSchedule(@RequestParam String psychologistDni, @RequestParam String date) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ScheduleDTO> schedulesDTO = scheduleServcie.listByPsychologistDniSessionsInSchedule(psychologistDni, date);
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
}
