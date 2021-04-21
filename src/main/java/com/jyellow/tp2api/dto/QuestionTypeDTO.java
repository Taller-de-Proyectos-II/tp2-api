package com.jyellow.tp2api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTypeDTO {
	private int idQuestionType;
	private String name;
	private String description;
	private List<QuestionDTO> questionsDTO;
}
