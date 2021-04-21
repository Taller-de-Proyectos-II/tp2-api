package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.QuestionTypeDTO;

public interface QuestionTypeService {
	void createDefault();
	List<QuestionTypeDTO> listAll();
	QuestionTypeDTO listById(int idQuestionType);
}
