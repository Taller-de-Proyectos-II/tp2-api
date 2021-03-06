package com.jyellow.tp2api.model;

import javax.persistence.Column;
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
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idReport;
	private String date;
	private String hour;
	@Column(length = 2004)
	private String description;
	@Column(nullable = true)
	private String type;
	
	@ManyToOne
	private Patient patient;
	
	@ManyToOne
	private Psychologist psychologist;
}
