package com.jyellow.tp2api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Guardian {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGuardian;
	private String names;
	private String lastNames;
	private String phone;
	private String email;
	private String birthday;
	private String dni;

	@ManyToOne
	private Patient patient;

	@OneToOne(cascade = CascadeType.ALL)
	private Image image;

}
