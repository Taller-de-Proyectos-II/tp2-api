package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "patients")
public class Patient {

	@Id
	@Column(name = "idPatient")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPatient;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	private UserLogin userLogin;

	@ManyToOne
	@JoinColumn(name = "idPsychologist", nullable = true)
	private Psychologist psychologist;

	@OneToMany(mappedBy = "patient")
	List<Guardian> guardians;

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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idImage", referencedColumnName = "idImage")
	private Image image;

}
