package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPatient;

	@OneToOne(cascade = CascadeType.ALL)
	private UserLogin userLogin;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Psychologist psychologist;

	@OneToMany(mappedBy = "patient")
	private List<Guardian> guardians;

	private String names;
	private String lastNames;
	private String phone;
	private String email;
	private String description;
	private String birthday;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Test> tests;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Session> sessions;

	@OneToMany(mappedBy = "patient")
	private List<Report> reports;
}
