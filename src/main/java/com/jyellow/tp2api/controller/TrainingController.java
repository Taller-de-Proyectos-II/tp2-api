package com.jyellow.tp2api.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.TrainingDTO;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/training")
@Log4j2
public class TrainingController {

	@PostMapping(path = "/trainingAlerts/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> trainingAlerts(@RequestBody TrainingDTO trainingDTO) {
		log.info("TrainingController: method trainingAlerts");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			final String uri = "https://app-tp2-ia.herokuapp.com/alerts/training";
			String chain = trainingDTO.getJson();
			RestTemplate restTemplate = new RestTemplate();
			String reqBody = chain;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> result = new HttpEntity<String>(reqBody, headers);
			String resultString = restTemplate.postForObject(uri, result, String.class);
			
			responseDTO.setMessage(resultString);
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/trainingManifestations/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> trainingManifestations(@RequestBody TrainingDTO trainingDTO) {
		log.info("TrainingController: method trainingManifestations");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			final String uri = "https://app-tp2-ia.herokuapp.com/manifestations/training";
			String chain = trainingDTO.getJson();
			RestTemplate restTemplate = new RestTemplate();
			String reqBody = chain;
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> result = new HttpEntity<String>(reqBody, headers);
			String resultString = restTemplate.postForObject(uri, result, String.class);
			
			responseDTO.setMessage(resultString);
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
