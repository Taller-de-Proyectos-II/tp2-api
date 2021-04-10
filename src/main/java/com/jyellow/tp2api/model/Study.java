package com.jyellow.tp2api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Study {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idStudy;
	private String academicDiscipline;
	private String title;
	private boolean isComplete;
	private String startDate;
	private String endDate;
	private String description;
	private String studyCenter;

	@ManyToOne
	private Psychologist psychologist;

}
