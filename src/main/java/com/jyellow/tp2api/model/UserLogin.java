package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "usersLogin")
public class UserLogin {

	@Id
	@Column(name = "idUser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUser;

	@Column(name = "dni", length = 8)
	private String dni;

	@Column(name = "password", length = 20)
	private String password;

	@OneToOne(mappedBy = "userLogin")
	private Patient patient;

	@OneToOne(mappedBy = "userLogin")
	private Psychologist psychologist;

}
