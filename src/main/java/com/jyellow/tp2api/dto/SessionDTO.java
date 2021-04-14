package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
	private int idSession;
	private String date;
	private String meetingLink;
	private boolean acepted;
	private boolean finished;
	private PsychologistDTO psychologist;
	private PatientDTO patient;
	private ScheduleDTO schedule;
}
