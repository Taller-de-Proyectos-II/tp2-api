package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.GuardianDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.GuardianService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/guardian")
@Log4j2
public class GuardianController {

	@Autowired
	GuardianService guardianService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody GuardianDTO guardianDTO) {
		log.info("GuardianController: method create");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.create(guardianDTO);
			if (result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else if (result == -2) {
				responseDTO.setMessage("Apoderado ya registrado para el paciente");
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
	public ResponseEntity<?> update(@RequestBody GuardianDTO guardianDTO) {
		log.info("GuardianController: method update");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.update(guardianDTO);
			if (result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Actualizaci??n exitosa");
				responseDTO.setStatus(1);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@DeleteMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam String dni, @RequestParam String patientDni) {
		log.info("GuardianController: method delete");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.delete(dni, patientDni);
			if (result == -1) {
				responseDTO.setMessage("No se encontr?? apoderado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Eliminaci??n exitosa");
				responseDTO.setStatus(1);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByDniAndPatientDni/", produces = "application/json")
	public ResponseEntity<?> listByDniAndPatientDni(@RequestParam String dni, @RequestParam String patientDni) {
		log.info("GuardianController: method listByDniAndPatientDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			GuardianDTO guardianDTO = guardianService.listByDniAndPatientDni(dni, patientDni);
			if (guardianDTO == null) {
				responseDTO.setMessage("No se encontr?? apoderado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Apoderado");
				responseDTO.setStatus(1);
				responseDTO.setGuardianDTO(guardianDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listByPatientDni(@RequestParam String dni) {
		log.info("GuardianController: method listByPatientDni");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<GuardianDTO> guardiansDTO = guardianService.listByPatientDni(dni);
			if (guardiansDTO == null || guardiansDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron apoderados");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Apoderados");
				responseDTO.setStatus(1);
				responseDTO.setGuardiansDTO(guardiansDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/image/")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartImage, @RequestParam String dni, @RequestParam String patientDni)
			throws Exception {
		log.info("GuardianController: method uploadImage");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			this.guardianService.uploadImage(multipartImage, dni, patientDni);
			responseDTO.setMessage("Imagen actualizada");
			responseDTO.setStatus(1);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(value = "/image/", produces = MediaType.IMAGE_JPEG_VALUE)
	public ByteArrayResource getImage(@RequestParam String dni, @RequestParam String patientDni) {
		log.info("GuardianController: method getImage");
		ByteArrayResource image = guardianService.getImage(dni, patientDni);
		return image;
	}
}
