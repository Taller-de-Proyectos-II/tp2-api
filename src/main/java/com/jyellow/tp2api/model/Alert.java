package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAlert;
	private String date;
	private String hour;
	private boolean important;

	@OneToMany(mappedBy = "alert")
	private List<AlertAnswer> alertAnswers;

	@ManyToOne
	private Patient patient;
}