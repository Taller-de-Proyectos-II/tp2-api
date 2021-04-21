package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class QuestionType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idQuestionType;
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "questionType")
	private List<Question> questions;
}
