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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AlertAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAlertAnswer;
	private int score;

	@ManyToOne
	private Symptom symptom;

	@ManyToOne
	private Alert alert;
}
