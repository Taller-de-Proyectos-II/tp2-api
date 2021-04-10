package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	@JoinColumn(name = "idPsychologist", nullable = false)
	private Psychologist psychologist;

}
