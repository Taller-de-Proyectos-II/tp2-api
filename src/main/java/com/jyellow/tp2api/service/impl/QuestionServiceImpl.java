package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.QuestionDTO;
import com.jyellow.tp2api.model.Answer;
import com.jyellow.tp2api.model.Question;
import com.jyellow.tp2api.model.QuestionType;
import com.jyellow.tp2api.repository.QuestionRepository;
import com.jyellow.tp2api.repository.QuestionTypeRepository;
import com.jyellow.tp2api.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	private boolean defaultCreation;

	public QuestionServiceImpl() {
		defaultCreation = false;
	}

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<QuestionDTO> listByQuestionTypeId(int questionTypeId) {
		List<Question> questions = questionRepository.findByQuestionTypeIdQuestionType(questionTypeId);
		List<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Question question : questions) {
			questionDTO = new QuestionDTO();
			questionDTO = modelMapper.map(question, QuestionDTO.class);
			questionDTO.setIdQuestionType(question.getQuestionType().getIdQuestionType());
			questionsDTO.add(questionDTO);
		}
		return questionsDTO;
	}

	@Override
	public List<QuestionDTO> listAll() {
		List<Question> questions = questionRepository.findAll();
		List<QuestionDTO> questionsDTO = new ArrayList<QuestionDTO>();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Question question : questions) {
			questionDTO = new QuestionDTO();
			questionDTO = modelMapper.map(question, QuestionDTO.class);
			questionDTO.setIdQuestionType(question.getQuestionType().getIdQuestionType());
			questionsDTO.add(questionDTO);
		}
		return questionsDTO;
	}

	@Transactional
	@Override
	public void createDefault() {
		List<Question> questions = new ArrayList<Question>();
		QuestionType questionType = new QuestionType();
		List<Answer> answers = new ArrayList<Answer>();
		if (defaultCreation == false) {
			questionType = questionTypeRepository.findByName("Ansiedad");
			questions.add(new Question(0, "Me siento más intranquilo(a) y nervioso(a) que de costumbre", questionType,
					answers));
			questions.add(new Question(0, "Me siento atemorizado(a) sin motivo", questionType, answers));
			questions.add(new Question(0, "Me altero o agito con rapidez", questionType, answers));
			questions.add(new Question(0, "Me siento hecho(a) pedazos", questionType, answers));
			questions.add(
					new Question(0, "Creo que todo está bien y que no va a pasar nada malo", questionType, answers));
			questions.add(new Question(0, "Me tiemblan las manos, los brazos y las piernas", questionType, answers));
			questions.add(new Question(0, "Sufro dolores de cabeza, de cuello y de la espalda", questionType, answers));
			questions.add(new Question(0, "Me siento débil y me canso fácilmente", questionType, answers));
			questions.add(
					new Question(0, "Me siento tranquilo(a) y me es fácil estarme quieto(a)", questionType, answers));
			questions.add(new Question(0, "Siento que el corazón me late aprisa", questionType, answers));
			questions.add(new Question(0, "Sufro mareos (vértigos)", questionType, answers));
			questions.add(new Question(0, "Me desmayo o siento que voy a desmayarme", questionType, answers));
			questions.add(new Question(0, "Puedo respirar fácilmente", questionType, answers));
			questions.add(new Question(0, "Se me duermen y me hormiguean los dedos de las manos y de los pies",
					questionType, answers));
			questions.add(new Question(0, "Sufro dolores de estómago e indigestión", questionType, answers));
			questions.add(new Question(0, "Tengo que orinar con mucha frecuencia", questionType, answers));
			questions.add(new Question(0, "Por lo general tengo las manos secas y calientes", questionType, answers));
			questions.add(new Question(0, "La cara se me pone caliente y roja", questionType, answers));
			questions.add(new Question(0, "Duermo fácilmente y descanso bien por las noches", questionType, answers));
			questions.add(new Question(0, "Tengo pesadillas", questionType, answers));

			questionType = questionTypeRepository.findByName("Depresión");
			questions.add(new Question(0, "Me siento decaído y triste", questionType, answers));
			questions.add(new Question(0, "Por la mañana es cuando me siento mejor", questionType, answers));
			questions.add(new Question(0, "Siento ganas de llorar o irrumpo en llanto", questionType, answers));
			questions.add(new Question(0, "Tengo problemas para dormir por la noche", questionType, answers));
			questions.add(new Question(0, "Como la misma cantidad de siempre", questionType, answers));
			questions.add(new Question(0, "Todavía disfruto el sexo", questionType, answers));
			questions.add(new Question(0, "He notado que estoy perdiendo peso", questionType, answers));
			questions.add(new Question(0, "Tengo problemas de estreñimiento", questionType, answers));
			questions.add(new Question(0, "Mi corazón late más rápido de lo normal", questionType, answers));
			questions.add(new Question(0, "Me canso sin razón alguna", questionType, answers));
			questions.add(new Question(0, "Mi mente está tan clara como siempre", questionType, answers));
			questions.add(new Question(0, "Me es fácil hacer lo que siempre hacía", questionType, answers));
			questions.add(new Question(0, "Me siento agitado y no puedo estar quieto", questionType, answers));
			questions.add(new Question(0, "Siento esperanza en el futuro", questionType, answers));
			questions.add(new Question(0, "Estoy más irritable de lo normal", questionType, answers));
			questions.add(new Question(0, "Me es fácil tomar decisiones", questionType, answers));
			questions.add(new Question(0, "Siento que soy útil y me necesitan", questionType, answers));
			questions.add(new Question(0, "Mi vida es bastante plena", questionType, answers));
			questions.add(new Question(0, "Siento que los demás estarían mejor si yo muriera", questionType, answers));
			questions.add(new Question(0, "Todavía disfruto de las cosas que disfrutaba antes", questionType, answers));

			questionType = questionTypeRepository.findByName("Manifestaciones");
			questions.add(new Question(0,
					"Tengo tres o más de los siguientes síntomas: dolor de cabeza, náuseas, sudoración, sensación de debilidad o cansancio, dificultad para respirar, aceleración del ritmo cardíaco, temblores o aumento de temperatura corporal",
					questionType, answers));
			questions.add(new Question(0,
					"Tengo tres o más de los siguientes síntomas: bloqueo a la hora de responder, dificultades para seguir una conversación, irritabilidad, susceptibilidad, miedo intenso o angustia",
					questionType, answers));
			questions.add(new Question(0,
					"Tengo tres o más de los siguientes síntomas: inquietud motora, dificultad para estar quieto o en reposo, estado de alerta o hipervigilancia, aislamiento socialmente o dificultad para tomar decisiones",
					questionType, answers));
			questions.add(new Question(0,
					"Tengo tres o más de los siguientes síntomas: pensamientos distorcionados, rumiación: pensamientos constantes, dificultades para concentrarme, preocupaciones excesivas, anticipaciones amenazantes o expectativas negativas",
					questionType, answers));
		}
		questionRepository.saveAll(questions);
	}
}
