package com.jyellow.tp2api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "guardians")
public class Guardian {

	@Id
	@Column(name = "idGuardian")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGuardian;

	@Column(name = "names", length = 30)
	private String names;

	@Column(name = "lastNames", length = 30)
	private String lastNames;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "birthday", length = 10)
	private String birthday;

	@Column(name = "dni", length = 8)
	private String dni;

	@ManyToOne
	@JoinColumn(name = "idPatient", nullable = false)
	private Patient patient;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idImage", referencedColumnName = "idImage")
	private Image image;

}
