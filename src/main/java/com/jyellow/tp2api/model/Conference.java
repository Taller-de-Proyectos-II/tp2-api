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
@Table(name = "conferences")
public class Conference {

	@Id
	@Column(name = "idConference")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idConference;
	
	@Column(name = "name", length = 30)
	private String name;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "place", length = 30)
	private String place;
	
    @ManyToOne
    @JoinColumn(name="idPsychologist", nullable = false)
	private Psychologist psychologist;

	public Conference() {
		super();
	}
	
	public Conference(int idConference, String name, String description, String place, Psychologist psychologist) {
		super();
		this.idConference = idConference;
		this.name = name;
		this.description = description;
		this.place = place;
		this.psychologist = psychologist;
	}

	public int getIdConference() {
		return idConference;
	}

	public void setIdConference(int idConference) {
		this.idConference = idConference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public Psychologist getPsychologist() {
		return psychologist;
	}

	public void setPsychologist(Psychologist psychologist) {
		this.psychologist = psychologist;
	}
}
