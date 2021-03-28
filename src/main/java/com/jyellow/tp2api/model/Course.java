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
@Table(name = "courses")
public class Course {
	
	@Id
	@Column(name = "idCourse")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCourse;
	
	@Column(name = "name", length = 30)
	private String name;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "studyCenter", length = 30)
	private String studyCenter;
	
    @ManyToOne
    @JoinColumn(name="idPsychologist", nullable=false)
	private Psychologist psychologist;

	public Course() {
		super();
	}
	
	public Course(int idCourse, String name, String description, String studyCenter, Psychologist psychologist) {
		super();
		this.idCourse = idCourse;
		this.name = name;
		this.description = description;
		this.studyCenter = studyCenter;
		this.psychologist = psychologist;
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
	
	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}
}
