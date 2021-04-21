package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.QuestionTypeDTO;
import com.jyellow.tp2api.model.Question;
import com.jyellow.tp2api.model.QuestionType;
import com.jyellow.tp2api.repository.QuestionTypeRepository;
import com.jyellow.tp2api.service.QuestionTypeService;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
	
	private boolean defaultCreation;
	
	public QuestionTypeServiceImpl() {
		defaultCreation = false;
	}
	
	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public void createDefault() {
		List<QuestionType> questionTypes = new ArrayList<QuestionType>();
		List<Question> questions = new ArrayList<Question>();
		if(defaultCreation == false) {
			questionTypes.add(new QuestionType(0, "Depresión", "Test de Depresión", questions));
			questionTypes.add(new QuestionType(0, "Ansiedad", "Test de Ansiedad", questions));
		}
		questionTypeRepository.saveAll(questionTypes);
	}

	@Override
	public List<QuestionTypeDTO> listAll() {
		List<QuestionType> questionTypes = questionTypeRepository.findAll();
		QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();
		List<QuestionTypeDTO> questionTypesDTO = new ArrayList<QuestionTypeDTO>();
		for(QuestionType questionType: questionTypes) {
			questionTypeDTO = new QuestionTypeDTO();
			questionTypeDTO = modelMapper.map(questionType, QuestionTypeDTO.class);
			questionTypesDTO.add(questionTypeDTO);
		}
		return questionTypesDTO;
	}

	@Override
	public QuestionTypeDTO listById(int idQuestionType) {
		QuestionType questionType = questionTypeRepository.findById(idQuestionType).get();
		QuestionTypeDTO questionTypeDTO = modelMapper.map(questionType, QuestionTypeDTO.class);
		return questionTypeDTO;
	}
	
	
}
