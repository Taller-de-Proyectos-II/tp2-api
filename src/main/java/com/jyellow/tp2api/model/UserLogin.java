package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usersLogin")
public class UserLogin {

	@Id
	@Column(name = "idUser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUser;

	@Column(name = "dni", length = 8)
	private String dni;

	@Column(name = "password", length = 20)
	private String password;

	@OneToOne(mappedBy = "userLogin")
	private Patient patient;

	@OneToOne(mappedBy = "userLogin")
	private Psychologist psychologist;

	public UserLogin() {
		super();
	}

	public UserLogin(int idUser, String dni, String password) {
		super();
		this.idUser = idUser;
		this.dni = dni;
		this.password = password;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
