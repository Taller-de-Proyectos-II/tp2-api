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
@Table(name = "studies")
public class Study {
	
	@Id
	@Column(name = "idStudy")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idStudy;
	
	@Column(name = "academicDiscipline", length = 30)
	private String academicDiscipline;
	
	@Column(name = "title", length = 40)
	private String title;
	
	@Column(name = "isComplete")
	private boolean isComplete;
	
	@Column(name = "startDate", length = 10)
	private String startDate;
	
	@Column(name = "endDate", length = 10)
	private String endDate;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "studyCenter", length = 30)
	private String studyCenter;
	
    @ManyToOne
    @JoinColumn(name="idPsychologist", nullable=false)
	private Psychologist psychologist;

	public Study() {
		super();
	}

	public Study(int idStudy, String academicDiscipline, String title, boolean isComplete, String startDate,
			String endDate, String description, String studyCenter, Psychologist psychologist) {
		super();
		this.idStudy = idStudy;
		this.academicDiscipline = academicDiscipline;
		this.title = title;
		this.isComplete = isComplete;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.studyCenter = studyCenter;
		this.psychologist = psychologist;
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
	
	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}
}
