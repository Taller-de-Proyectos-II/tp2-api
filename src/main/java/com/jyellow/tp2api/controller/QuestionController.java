package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.QuestionDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.QuestionService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/question")
@Log4j2
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
		log.info("QuestionController: method listAll");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<QuestionDTO> questionsDTO = questionService.listAll();
			responseDTO.setMessage("Preguntas");
			responseDTO.setStatus(1);
			responseDTO.setQuestionsDTO(questionsDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByQuestionTypeId/", produces = "application/json")
	public ResponseEntity<?> listByQuestionTypeId(@RequestParam int idQuestionType) {
		log.info("QuestionController: method listByQuestionTypeId");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<QuestionDTO> questionsDTO = questionService.listByQuestionTypeId(idQuestionType);
			responseDTO.setMessage("Preguntas");
			responseDTO.setStatus(1);
			responseDTO.setQuestionsDTO(questionsDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/createDefault/", produces = "application/json")
	public ResponseEntity<?> createDefault() {
		log.info("QuestionController: method createDefault");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = questionService.createDefault();
			if (result == 1) {
				responseDTO.setMessage("Creación Default exitosa");
				responseDTO.setStatus(1);
			} else if (result == -1) {
				responseDTO.setMessage("Los tipos de pregunta aún no están creados");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Las preguntas por defecto ya fueron creadas");
				responseDTO.setStatus(0);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
