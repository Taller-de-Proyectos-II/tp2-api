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

@CrossOrigin
@RestController
@RequestMapping(path = "/symptom")
public class SymptomController {

	@Autowired
	private SymptomService symptomService;
	
	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
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
}
