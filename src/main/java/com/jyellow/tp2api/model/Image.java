package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Image {

	@Id
	@Column(name = "idimage")
	@GeneratedValue
	Long idImage;

	@Lob
	byte[] content;

	@Column(name = "name")
	String name;
	
	@OneToOne(mappedBy = "image")
	private Psychologist psychologist;
	
	@OneToOne(mappedBy = "image")
	private Guardian guardian;
	
	@OneToOne(mappedBy = "image")
	private Patient patient;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(Long idImage, byte[] content, String name, Psychologist psychologist, Guardian guardian,
			Patient patient) {
		super();
		this.idImage = idImage;
		this.content = content;
		this.name = name;
		this.psychologist = psychologist;
		this.guardian = guardian;
		this.patient = patient;
	}

	public Long getIdImage() {
		return idImage;
	}

	public void setIdImage(Long idImage) {
		this.idImage = idImage;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
