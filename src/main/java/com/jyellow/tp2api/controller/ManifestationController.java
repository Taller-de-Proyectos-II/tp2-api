package com.jyellow.tp2api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ManifestationsDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.ManifestationService;

@CrossOrigin
@RestController
@RequestMapping(path = "/manifestation")
public class ManifestationController {
	
	@Autowired
	ManifestationService manifestationService;
	
	@GetMapping(path = "/listManifestations/", produces = "application/json")
	public ResponseEntity<?> listManifestations() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ManifestationsDTO manifestationsDTO = new ManifestationsDTO();
			manifestationsDTO.setPhysical(manifestationService.listByIdManifestation(1));
			manifestationsDTO.setEmotional(manifestationService.listByIdManifestation(2));
			manifestationsDTO.setConductual(manifestationService.listByIdManifestation(3));
			manifestationsDTO.setCognitive(manifestationService.listByIdManifestation(4));
			responseDTO.setManifestationsDTO(manifestationsDTO);
			responseDTO.setMessage("Manifestaciones");
			responseDTO.setStatus(1);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
