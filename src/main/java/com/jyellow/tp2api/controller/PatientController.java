package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.ChangePasswordDTO;
import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.PatientService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/patient")
@Log4j2
public class PatientController {

	@Autowired
	PatientService patientService;

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody PatientDTO patientDTO) {
		log.info("PatientController: method update");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = patientService.update(patientDTO);
			if (result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Actualización exitosa");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}

		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/updatePassword/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updatePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		log.info("PatientController: method updatePassword");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = patientService.updatePassword(changePasswordDTO);
			if (result == -1) {
				responseDTO.setMessage("El dni no está registrado");
				responseDTO.setStatus(0);
			} else if (result == -2) {
				responseDTO.setMessage("La contraseña no coincide");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Actualización exitosa");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByDni/", produces = "application/json")
	public ResponseEntity<?> listByDni(@RequestParam String dni) {
		log.info("PatientController: method listByDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			PatientDTO patientDTO = patientService.listByDni(dni);
			if (patientDTO == null) {
				responseDTO.setMessage("No se encontró paciente");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Paciente");
				responseDTO.setStatus(1);
				responseDTO.setPatientDTO(patientDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listByPsychologistDni(@RequestParam String psychologistDni) {
		log.info("PatientController: method listByPsychologistDni");
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
	
	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
		log.info("PatientController: method listAll");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<PatientDTO> patientsDTO = patientService.listAll();
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

	@GetMapping(path = "/assignToPsychologist/", produces = "application/json")
	public ResponseEntity<?> assignToPsychologist(@RequestParam String patientDni,
			@RequestParam String psychologistDni) {
		log.info("PatientController: method psychologistDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean result = patientService.assignToPsychologist(patientDni, psychologistDni);
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

	@GetMapping(path = "/removePsychologist/", produces = "application/json")
	public ResponseEntity<?> removePsychologist(@RequestParam String patientDni) {
		log.info("PatientController: method removePsychologist");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean result = patientService.removePsychologist(patientDni);
			if (result == true) {
				responseDTO.setMessage("Eliminación de la asignación exitosa");
				responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Error");
				responseDTO.setStatus(0);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/image/")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartImage, @RequestParam String dni)
			throws Exception {
		log.info("PatientController: method uploadImage");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			this.patientService.uploadImage(multipartImage, dni);
			responseDTO.setMessage("Imagen actualizada");
			responseDTO.setStatus(1);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(value = "/image/", produces = MediaType.IMAGE_JPEG_VALUE)
	public ByteArrayResource getImage(@RequestParam String dni) {
		log.info("PatientController: method getImage");
		ByteArrayResource image = patientService.getImage(dni);
		return image;
	}
}
