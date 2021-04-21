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
public class Symptom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSymptom;
	private String name;
	private String description;
	
	@ManyToOne
	private Manifestation manifestation;
	
}
