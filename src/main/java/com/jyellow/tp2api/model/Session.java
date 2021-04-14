package com.jyellow.tp2api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSession;
	private String date;
	private boolean acepted;
	private boolean finished;
	private String meetingLink;

	@ManyToOne
	private Schedule schedule;

	@ManyToOne
	private Patient patient;

	@ManyToOne
	private Psychologist psychologist;
}
