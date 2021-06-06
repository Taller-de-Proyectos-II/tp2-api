package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.RecomendationDTO;
import com.jyellow.tp2api.service.RecomendationService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/recomendations")
@Log4j2
public class RecomendationController {
	@Autowired
	private RecomendationService recomendationService;

	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
		log.info("RecomendationController: method listAll");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<RecomendationDTO> recomendationsDTO = recomendationService.listAll();
			responseDTO.setMessage("Recomendaciones");
			responseDTO.setStatus(1);
			responseDTO.setRecomendationsDTO(recomendationsDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/createDefault/", produces = "application/json")
	public ResponseEntity<?> createDefault() {
		log.info("RecomendationController: method createDefault");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			recomendationService.createDefault();
			responseDTO.setMessage("Creación Default exitosa");
			responseDTO.setStatus(1);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
