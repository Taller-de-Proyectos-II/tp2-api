package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.QuestionTypeDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.QuestionTypeService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/questionType")
@Log4j2
public class QuestionTypeController {

	@Autowired
	private QuestionTypeService questionTypeService;

	@GetMapping(path = "/listAll/", produces = "application/json")
	public ResponseEntity<?> listAll() {
		log.info("QuestionTypeController: method listAll");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<QuestionTypeDTO> questionTypesDTO = questionTypeService.listAll();
			responseDTO.setMessage("Preguntas");
			responseDTO.setStatus(1);
			responseDTO.setQuestionTypesDTO(questionTypesDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listByQuestionTypeId/", produces = "application/json")
	public ResponseEntity<?> listByQuestionTypeId(@RequestParam int idQuestionType) {
		log.info("QuestionTypeController: method listByQuestionTypeId");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			QuestionTypeDTO questionTypeDTO = questionTypeService.listById(idQuestionType);
			if(questionTypeDTO != null) {
			responseDTO.setMessage("Tipos de Pregunta");
			responseDTO.setStatus(1);
			responseDTO.setQuestionTypeDTO(questionTypeDTO);
			} else {
				responseDTO.setMessage("Id incorrecto");
				responseDTO.setStatus(0);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/createDefault/", produces = "application/json")
	public ResponseEntity<?> createDefault() {
		log.info("QuestionTypeController: method createDefault");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			questionTypeService.createDefault();
			responseDTO.setMessage("Creación Default exitosa");
			responseDTO.setStatus(1);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
