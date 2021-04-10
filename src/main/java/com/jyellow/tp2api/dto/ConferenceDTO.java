package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceDTO {

	private int idConference;
	private String name;
	private String description;
	private String place;
	private String psychologistDni;

}
