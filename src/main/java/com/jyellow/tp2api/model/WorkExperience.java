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
	@JoinColumn(name = "idPsychologist", nullable = false)
	private Psychologist psychologist;

}
