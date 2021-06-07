package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.QuestionDTO;

public interface QuestionService {
	List<QuestionDTO> listByQuestionTypeId(int questionTypeId);
	List<QuestionDTO> listAll();
	int createDefault();
}
