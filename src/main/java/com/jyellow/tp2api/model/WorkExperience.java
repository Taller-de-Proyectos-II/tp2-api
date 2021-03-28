package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workExperiences")
public class WorkExperience {
	
	@Id
	@Column(name = "idWorkExperience")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idWorkExperience;
	
	@Column(name = "place", length = 30)
	private String place;
	
	@Column(name = "occupation", length = 30)
	private String occupation;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "workingDayType", length = 30)
	private String workingDayType;

	@Column(name = "startDate", length = 10)
	private String startDate;
	
	@Column(name = "endDate", length = 10)
	private String endDate;
	
	@Column(name = "isCurrent")
	private boolean isCurrent;
	
    @ManyToOne
    @JoinColumn(name="idPsychologist", nullable=false)
	private Psychologist psychologist;
	
	public WorkExperience() {
		super();
	}

	public WorkExperience(int idWorkExperience, String place, String occupation, String description,
			String workingDayType, String startDate, String endDate, boolean isCurrent, Psychologist psychologist) {
		super();
		this.idWorkExperience = idWorkExperience;
		this.place = place;
		this.occupation = occupation;
		this.description = description;
		this.workingDayType = workingDayType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isCurrent = isCurrent;
		this.psychologist = psychologist;
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

	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}
	
}
