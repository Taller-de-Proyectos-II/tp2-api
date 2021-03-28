package com.jyellow.tp2api.dto;

public class WorkExperienceDTO {

	private int idWorkExperience;
	
	private String place;
	
	private String occupation;
	
	private String description;
	
	private String workingDayType;

	private String startDate;
	
	private String endDate;
	
	private boolean isCurrent;
	
	private String psychologistDni;

	public WorkExperienceDTO() {
		super();
	}

	public WorkExperienceDTO(int idWorkExperience, String place, String occupation, String description,
			String workingDayType, String startDate, String endDate, boolean isCurrent, String psychologistDni) {
		super();
		this.idWorkExperience = idWorkExperience;
		this.place = place;
		this.occupation = occupation;
		this.description = description;
		this.workingDayType = workingDayType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isCurrent = isCurrent;
		this.psychologistDni = psychologistDni;
	}

	public int getIdWorkExperience() {
		return idWorkExperience;
	}

	public void setIdWorkExperience(int idWorkExperience) {
		this.idWorkExperience = idWorkExperience;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkingDayType() {
		return workingDayType;
	}

	public void setWorkingDayType(String workingDayType) {
		this.workingDayType = workingDayType;
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

	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getPsychologistDni() {
		return psychologistDni;
	}

	public void setPsychologistDni(String psychologistDni) {
		this.psychologistDni = psychologistDni;
	}
	
}
