package com.jyellow.tp2api.dto;

public class ConferenceDTO {

	private int idConference;
	
	private String name;
	
	private String description;
	
	private String place;
	
	private String psychologistDni;

	public ConferenceDTO(String name, String description, String place, String psychologistDni, int idConference) {
		super();
		this.idConference = idConference;
		this.name = name;
		this.description = description;
		this.place = place;
		this.psychologistDni = psychologistDni;
	}

	public ConferenceDTO() {
		super();
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

	public String getPsychologistDni() {
		return psychologistDni;
	}

	public void setPsychologistDni(String psychologistDni) {
		this.psychologistDni = psychologistDni;
	}

	public int getIdConference() {
		return idConference;
	}

	public void setIdConference(int idConference) {
		this.idConference = idConference;
	}
	
}
