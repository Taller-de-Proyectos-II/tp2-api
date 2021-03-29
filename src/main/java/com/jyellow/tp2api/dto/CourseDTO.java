package com.jyellow.tp2api.dto;

public class CourseDTO {

	private int idCourse;
	private String name;
	private String description;
	private String studyCenter;
	private String psychologistDni;

	public CourseDTO() {
		super();
	}

	public CourseDTO(int idCourse, String name, String description, String studyCenter, String psychologistDni) {
		super();
		this.idCourse = idCourse;
		this.name = name;
		this.description = description;
		this.studyCenter = studyCenter;
		this.psychologistDni = psychologistDni;
	}

	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStudyCenter() {
		return studyCenter;
	}

	public void setStudyCenter(String studyCenter) {
		this.studyCenter = studyCenter;
	}

	public String getPsychologistDni() {
		return psychologistDni;
	}

	public void setPsychologistDni(String psychologistDni) {
		this.psychologistDni = psychologistDni;
	}
	
}
