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

@CrossOrigin
@RestController
@RequestMapping(path = "/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
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
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			questionService.createDefault();
			responseDTO.setMessage("Creación Default exitosa");
			responseDTO.setStatus(1);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
