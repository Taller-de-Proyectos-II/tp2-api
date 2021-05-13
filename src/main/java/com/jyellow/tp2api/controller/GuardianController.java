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

@CrossOrigin
@RestController
@RequestMapping(path = "/guardian")
public class GuardianController {

	@Autowired
	GuardianService guardianService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody GuardianDTO guardianDTO) {
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
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.update(guardianDTO);
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

	@DeleteMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam String dni, @RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.delete(dni, patientDni);
			if (result == -1) {
				responseDTO.setMessage("No se encontró apoderado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Eliminación exitosa");
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
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			GuardianDTO guardianDTO = guardianService.listByDniAndPatientDni(dni, patientDni);
			if (guardianDTO == null) {
				responseDTO.setMessage("No se encontró apoderado");
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
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile multipartImage, @RequestParam String dni)
			throws Exception {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			this.guardianService.uploadImage(multipartImage, dni);
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
		ByteArrayResource image = guardianService.getImage(dni);
		return image;
	}
}
