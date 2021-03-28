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

import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.service.PatientService;

@CrossOrigin
@RestController
@RequestMapping(path = "/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody PatientDTO patientDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = patientService.create(patientDTO);
			if (result == -2) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else if (result == -1) {
				responseDTO.setMessage("DNI ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Registro exitoso");
				responseDTO.setStatus(1);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody PatientDTO patientDTO) {
		ResponseDTO responseDTO = new ResponseDTO();

		int result = patientService.update(patientDTO);
		if (result == -1) {
			responseDTO.setMessage("Email ya registrado");
			responseDTO.setStatus(0);
		} else {
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
		}

		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/validate/", produces = "application/json")
	public ResponseEntity<?> listByDni(@RequestParam String dni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Patient patient = patientService.validatePatientByDni(dni);
			if (patient == null) {
				responseDTO.setMessage("Validación exitosa");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Paciente ya registrado");
				responseDTO.setStatus(0);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listByPsychologistDni(@RequestParam String psychologistDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<PatientDTO> patientsDTO = patientService.listByPsychologistDni(psychologistDni);
			if (patientsDTO == null || patientsDTO.size() == 0) {
				responseDTO.setMessage("No existen pacientes");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setPatientsDTO(patientsDTO);
				responseDTO.setMessage("Pacientes");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByGuardianDni/", produces = "application/json")
	public ResponseEntity<?> listByGuardianDni(@RequestParam String guardianDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<PatientDTO> patientsDTO = patientService.listByGuardianDni(guardianDni);
			if (patientsDTO == null || patientsDTO.size() == 0) {
				responseDTO.setMessage("No existen pacientes");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setPatientsDTO(patientsDTO);
				responseDTO.setMessage("Pacientes");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/assign/", produces = "application/json")
	public ResponseEntity<?> listByGuardianDni(@RequestParam String patientDni, @RequestParam String psychologistDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean result = patientService.assign(patientDni, psychologistDni);
			if (result == true) {
				responseDTO.setMessage("Asignación exitosa");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Paciente ya asignado");
				responseDTO.setStatus(0);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
