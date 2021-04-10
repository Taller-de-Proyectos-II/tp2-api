package com.jyellow.tp2api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

	@Id
	@Column(name = "idimage")
	@GeneratedValue
	Long idImage;

	@Lob
	byte[] content;

	@Column(name = "name")
	String name;

	@OneToOne(mappedBy = "image")
	private Psychologist psychologist;

	@OneToOne(mappedBy = "image")
	private Guardian guardian;

	@OneToOne(mappedBy = "image")
	private Patient patient;

}
