package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.AlertCreateDTO;
import com.jyellow.tp2api.dto.AlertDTO;
import com.jyellow.tp2api.dto.AlertUpdateDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.AlertService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/alerts")
@Log4j2
public class AlertController {
	@Autowired
	private AlertService alertService;
	
	@GetMapping(path = "/listByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listByPatientDni(@RequestParam String patientDni) {
		log.info("AlertController: method listByPatientDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<AlertDTO> alertsDTO = alertService.listByPatientDni(patientDni);
			if (alertsDTO == null || alertsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Alertas");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Alertas");
				responseDTO.setStatus(1);
				responseDTO.setAlertsDTO(alertsDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping(path = "/listImportantByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listImportantByPatientDni(@RequestParam String patientDni) {
		log.info("AlertController: method listImportantByPatientDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<AlertDTO> alertsDTO = alertService.listByPatientDniAndImportant(patientDni, true);
			if (alertsDTO == null || alertsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Alertas");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Alertas");
				responseDTO.setStatus(1);
				responseDTO.setAlertsDTO(alertsDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody AlertCreateDTO alertCreateDTO) {
		log.info("AlertController: method create");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			AlertDTO alertDTO = alertService.create(alertCreateDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);
			responseDTO.setAlertDTO(alertDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody AlertUpdateDTO alertUpdateDTO) {
		log.info("AlertController: method update");
		ResponseDTO responseDTO = new ResponseDTO();

			AlertDTO alertDTO = alertService.update(alertUpdateDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setAlertDTO(alertDTO);


		return ResponseEntity.ok(responseDTO);
	}
}
