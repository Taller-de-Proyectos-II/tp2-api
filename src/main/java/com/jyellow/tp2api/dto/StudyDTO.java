package com.jyellow.tp2api.dto;


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

	public StudyDTO() {
		super();
	}

	public StudyDTO(int idStudy, String academicDiscipline, String title, boolean isComplete, String startDate,
			String endDate, String description, String studyCenter, String psychologistDni) {
		super();
		this.idStudy = idStudy;
		this.academicDiscipline = academicDiscipline;
		this.title = title;
		this.isComplete = isComplete;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.studyCenter = studyCenter;
		this.psychologistDni = psychologistDni;
	}

	public int getIdStudy() {
		return idStudy;
	}

	public void setIdStudy(int idStudy) {
		this.idStudy = idStudy;
	}

	public String getAcademicDiscipline() {
		return academicDiscipline;
	}

	public void setAcademicDiscipline(String academicDiscipline) {
		this.academicDiscipline = academicDiscipline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
