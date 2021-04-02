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

	public Psychologist() {
		super();
	}

	public Psychologist(int idPsychologist, String names, String lastNames, String phone, String email,
			String description, String birthday, String cpsp, UserLogin userLogin, List<Study> studies,
			List<WorkExperience> workExperiences, List<Course> courses, List<Conference> conferences,
			List<Patient> patients) {
		super();
		this.idPsychologist = idPsychologist;
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.email = email;
		this.description = description;
		this.birthday = birthday;
		this.cpsp = cpsp;
		this.userLogin = userLogin;
		this.studies = studies;
		this.workExperiences = workExperiences;
		this.courses = courses;
		this.conferences = conferences;
		this.patients = patients;
	}

	public int getIdPsychologist() {
		return idPsychologist;
	}

	public void setIdPsychologist(int idPsychologist) {
		this.idPsychologist = idPsychologist;
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

	public String getCpsp() {
		return cpsp;
	}

	public void setCpsp(String cpsp) {
		this.cpsp = cpsp;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public List<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Conference> getConferences() {
		return conferences;
	}

	public void setConferences(List<Conference> conferences) {
		this.conferences = conferences;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
