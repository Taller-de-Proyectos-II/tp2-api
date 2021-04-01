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
@Table(name = "guardians")
public class Guardian {

	@Id
	@Column(name = "idGuardian")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGuardian;

	@Column(name = "names", length = 30)
	private String names;

	@Column(name = "lastNames", length = 30)
	private String lastNames;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "birthday", length = 10)
	private String birthday;
	
	@Column(name = "dni", length = 8)
	private String dni;

	@ManyToOne
	@JoinColumn(name = "idPatient", nullable = false)
	private Patient patient;

	public Guardian() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Guardian(int idGuardian, String names, String lastNames, String phone, String email, String birthday,
			String dni, Patient patient) {
		super();
		this.idGuardian = idGuardian;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
		this.dni = dni;
		this.patient = patient;
	}

	public int getIdGuardian() {
		return idGuardian;
	}

	public void setIdGuardian(int idGuardian) {
		this.idGuardian = idGuardian;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
