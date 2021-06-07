package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.SymptomDTO;
import com.jyellow.tp2api.service.SymptomService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/symptoms")
@Log4j2
public class SymptomController {
	@Autowired
	private SymptomService symptomService;

	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
		log.info("SymptomController: method listAll");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SymptomDTO> symptomsDTO = symptomService.listAll();
			responseDTO.setMessage("Síntomas");
			responseDTO.setStatus(1);
			responseDTO.setSymptomsDTO(symptomsDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/createDefault/", produces = "application/json")
	public ResponseEntity<?> createDefault() {
		log.info("SymptomController: method createDefault");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = symptomService.createDefault();
			if (result == 1) {
			responseDTO.setMessage("Creación Default exitosa");
			responseDTO.setStatus(1);
			} else {
				responseDTO.setMessage("Los síntomas por defecto ya fueron creados");
				responseDTO.setStatus(0);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
