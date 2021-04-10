package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	@JoinColumn(name = "idPsychologist", nullable = false)
	private Psychologist psychologist;

}
