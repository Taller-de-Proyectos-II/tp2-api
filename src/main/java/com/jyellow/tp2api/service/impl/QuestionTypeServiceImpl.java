package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.QuestionDTO;
import com.jyellow.tp2api.dto.QuestionTypeDTO;
import com.jyellow.tp2api.model.Question;
import com.jyellow.tp2api.model.QuestionType;
import com.jyellow.tp2api.repository.QuestionRepository;
import com.jyellow.tp2api.repository.QuestionTypeRepository;
import com.jyellow.tp2api.service.QuestionTypeService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class QuestionTypeServiceImpl implements QuestionTypeService {

	private boolean defaultCreation;

	public QuestionTypeServiceImpl() {
		defaultCreation = false;
	}

	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	@Autowired
	private QuestionRepository questionRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public void createDefault() {
		log.info("QuestionTypeServiceImpl: method createDefault");
		List<QuestionType> questionTypes = new ArrayList<QuestionType>();
		List<Question> questions = new ArrayList<Question>();
		if (defaultCreation == false) {
			questionTypes.add(new QuestionType(0, "Depresión", "Test de Depresión", questions));
			questionTypes.add(new QuestionType(0, "Ansiedad", "Test de Ansiedad", questions));
			questionTypes.add(new QuestionType(0, "Manifestaciones", "Test de Manifestaciones", questions));
		}
		questionTypeRepository.saveAll(questionTypes);
	}

	@Override
	public List<QuestionTypeDTO> listAll() {
		log.info("QuestionTypeServiceImpl: method listAll");
		List<QuestionType> questionTypes = questionTypeRepository.findAll();
		QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();
		List<QuestionTypeDTO> questionTypesDTO = new ArrayList<QuestionTypeDTO>();
		List<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
		List<Question> questions = new ArrayList<Question>();
		for (QuestionType questionType : questionTypes) {
			questionTypeDTO = new QuestionTypeDTO();
			questionTypeDTO = modelMapper.map(questionType, QuestionTypeDTO.class);
			questionsDTO = new ArrayList<QuestionDTO>();
			questions = questionRepository.findByQuestionTypeIdQuestionType(questionType.getIdQuestionType());
			for (Question question : questions) {
				questionsDTO.add(modelMapper.map(question, QuestionDTO.class));
			}
			questionTypeDTO.setQuestionsDTO(questionsDTO);
			questionTypesDTO.add(questionTypeDTO);
		}
		return questionTypesDTO;
	}

	@Override
	public QuestionTypeDTO listById(int idQuestionType) {
		log.info("QuestionTypeServiceImpl: method listById");
		QuestionType questionType = questionTypeRepository.findById(idQuestionType).get();
		QuestionTypeDTO questionTypeDTO = modelMapper.map(questionType, QuestionTypeDTO.class);
		List<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
		List<Question> questions = questionRepository
				.findByQuestionTypeIdQuestionType(questionType.getIdQuestionType());
		for (Question question : questions) {
			questionsDTO.add(modelMapper.map(question, QuestionDTO.class));
		}
		questionTypeDTO.setQuestionsDTO(questionsDTO);
		return questionTypeDTO;
	}
	
	@Override
	public QuestionTypeDTO listByName(String questionTypeName) {
		log.info("QuestionTypeServiceImpl: method listByName");
		QuestionType questionType = questionTypeRepository.findByName(questionTypeName);
		QuestionTypeDTO questionTypeDTO = modelMapper.map(questionType, QuestionTypeDTO.class);
		List<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
		List<Question> questions = questionRepository
				.findByQuestionTypeIdQuestionType(questionType.getIdQuestionType());
		for (Question question : questions) {
			questionsDTO.add(modelMapper.map(question, QuestionDTO.class));
		}
		questionTypeDTO.setQuestionsDTO(questionsDTO);
		return questionTypeDTO;
	}

}
