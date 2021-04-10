package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyDTO {
	private int idStudy;
	private String academicDiscipline;
	private String title;
	private boolean isComplete;
	private String startDate;
	private String endDate;
	private String description;
	private String studyCenter;
	private String psychologistDni;
}
