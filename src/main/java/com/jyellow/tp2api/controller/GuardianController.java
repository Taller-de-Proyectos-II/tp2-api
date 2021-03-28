package com.jyellow.tp2api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.GuardianDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.GuardianService;

@RestController
@RequestMapping(path = "/guardian")
public class GuardianController {
	
	@Autowired
	GuardianService guardianService;
	
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody GuardianDTO guardianDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.create(guardianDTO, guardianDTO.getUserLoginDTO());
			if(result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else if(result == -2) {
				responseDTO.setMessage("DNI ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Registro exitoso");
				responseDTO.setStatus(1);
			}
			
		} catch(Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody GuardianDTO guardianDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = guardianService.update(guardianDTO, guardianDTO.getUserLoginDTO());
			if(result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Actualización exitosa");
				responseDTO.setStatus(1);
			}
			
		} catch(Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> listByDni(@RequestParam String dni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			GuardianDTO guardianDTO = guardianService.listByDni(dni);
			responseDTO.setMessage("Apoderado");
			responseDTO.setStatus(1);
			responseDTO.setGuardianDTO(guardianDTO);
		} catch(Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
