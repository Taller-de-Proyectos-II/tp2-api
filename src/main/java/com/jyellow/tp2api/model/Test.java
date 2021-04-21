package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTest;

	private String startDate;
	private String endDate;
	private boolean finished;
	private String diagnostic;
	private String testType;

	@OneToMany(mappedBy = "test")
	private List<Answer> answers;

	@ManyToOne
	private Patient patient;
}
