package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "psychologists")
public class Psychologist {

	@Id
	@Column(name = "idPsychologist")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPsychologist;

	@Column(name = "names", length = 30)
	private String names;

	@Column(name = "lastNames", length = 30)
	private String lastNames;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "description", length = 300)
	private String description;

	@Column(name = "birthday", length = 10)
	private String birthday;

	@Column(name = "cpsp", length = 20)
	private String cpsp;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	private UserLogin userLogin;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idImage", referencedColumnName = "idImage")
	private Image image;

	@OneToMany(mappedBy = "psychologist")
	List<Study> studies;

	@OneToMany(mappedBy = "psychologist")
	List<WorkExperience> workExperiences;

	@OneToMany(mappedBy = "psychologist")
	List<Course> courses;

	@OneToMany(mappedBy = "psychologist")
	List<Conference> conferences;

	@OneToMany(mappedBy = "psychologist")
	List<Patient> patients;

}
