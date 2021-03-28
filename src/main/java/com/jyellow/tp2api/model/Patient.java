package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@Column(name = "idPatient")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPatient;

	@ManyToOne
	@JoinColumn(name = "idPsychologist", nullable = true)
	private Psychologist psychologist;

	@ManyToOne
	@JoinColumn(name = "idGuardian", nullable = false)
	private Guardian guardian;

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

	@Column(name = "dni", length = 20)
	private String dni;

	public Patient() {
		super();
	}

	public Patient(int idPatient, Psychologist psychologist, Guardian guardian, String names, String lastNames,
			String phone, String email, String description, String birthday, String dni) {
		super();
		this.idPatient = idPatient;
		this.psychologist = psychologist;
		this.guardian = guardian;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.email = email;
		this.description = description;
		this.birthday = birthday;
		this.dni = dni;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}

	public Guardian getGuardian() {
		return guardian;
	}

	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
