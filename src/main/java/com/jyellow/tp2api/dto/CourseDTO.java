package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

	private int idCourse;
	private String name;
	private String description;
	private String studyCenter;
	private String psychologistDni;

}
