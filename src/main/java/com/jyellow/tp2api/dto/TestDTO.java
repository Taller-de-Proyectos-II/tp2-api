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
public class TestDTO {
	private int idTest;
	private String startDate;
	private String endDate;
	private boolean finished;
	private String diagnostic;
	private String testType;
	private String color;
	List<AnswerDTO> answersDTO;
}
