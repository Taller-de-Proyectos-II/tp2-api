package com.jyellow.tp2api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUser;

	private String dni;
	private String password;
	
	@OneToOne(mappedBy = "userLogin")
	private Patient patient;

	@OneToOne(mappedBy = "userLogin")
	private Psychologist psychologist;

}
