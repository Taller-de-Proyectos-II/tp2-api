package com.jyellow.tp2api.model;

import javax.persistence.Entity;
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
@Entity
public class Image {

	@Id
	@GeneratedValue
	private Long idImage;

	@Lob
	private byte[] content;

	private String name;

	@OneToOne(mappedBy = "image")
	private Psychologist psychologist;

	@OneToOne(mappedBy = "image")
	private Guardian guardian;

	@OneToOne(mappedBy = "image")
	private Patient patient;

}
