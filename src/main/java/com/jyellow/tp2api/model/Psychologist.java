package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Psychologist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPsychologist;
	private String names;
	private String lastNames;
	private String phone;
	private String email;
	private String description;
	private String birthday;
	private String cpsp;

	@OneToOne(cascade = CascadeType.ALL)
	private UserLogin userLogin;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

	@OneToMany(mappedBy = "psychologist")
	private List<Study> studies;

	@OneToMany(mappedBy = "psychologist")
	private List<WorkExperience> workExperiences;

	@OneToMany(mappedBy = "psychologist")
	private List<Course> courses;

	@OneToMany(mappedBy = "psychologist")
	private List<Conference> conferences;

	@OneToMany(mappedBy = "psychologist")
	private List<Patient> patients;

	@OneToMany(mappedBy = "psychologist")
	private List<Report> reports;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Schedule> schedules;
}
