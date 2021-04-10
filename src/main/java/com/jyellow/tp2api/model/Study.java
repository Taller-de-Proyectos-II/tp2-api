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
	@JoinColumn(name = "idPsychologist", nullable = false)
	private Psychologist psychologist;

}
