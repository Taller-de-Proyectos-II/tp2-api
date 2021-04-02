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

	public Patient() {
		super();
	}

	public Patient(int idPatient, UserLogin userLogin, Psychologist psychologist, List<Guardian> guardians,
			String names, String lastNames, String phone, String email, String description, String birthday) {
		super();
		this.idPatient = idPatient;
		this.userLogin = userLogin;
		this.psychologist = psychologist;
		this.guardians = guardians;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.email = email;
		this.description = description;
		this.birthday = birthday;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
