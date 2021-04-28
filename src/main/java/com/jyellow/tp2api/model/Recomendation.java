package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recomendation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRecomendation;
	@Column(length = 500)
	private String description;
}
