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
public class WorkExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idWorkExperience;
	private String place;
	private String occupation;
	private String description;
	private String workingDayType;
	private String startDate;
	private String endDate;
	private boolean isCurrent;

	@ManyToOne
	private Psychologist psychologist;

}
