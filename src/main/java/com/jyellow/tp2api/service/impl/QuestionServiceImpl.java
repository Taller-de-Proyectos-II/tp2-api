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

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionTypeRepository questionTypeRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<QuestionDTO> listByQuestionTypeId(int questionTypeId) {
		log.info("QuestionServiceImpl: method listByQuestionTypeId");
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
		log.info("QuestionServiceImpl: method listAll");
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
	public int createDefault() {
		log.info("QuestionServiceImpl: method createDefault");
		List<Question> questions = new ArrayList<Question>();
		QuestionType questionType = new QuestionType();
		List<Answer> answers = new ArrayList<Answer>();
		List<Question> questionsAux = questionRepository.findAll();
		List<QuestionType> questionsTypeAux = questionTypeRepository.findAll();
		if(questionsTypeAux == null || questionsTypeAux.size() == 0) {
			return -1;
		}
		if ((questionsAux == null || questionsAux.size() == 0)) {
			questionType = questionTypeRepository.findByName("Ansiedad");
			questions.add(new Question(0, "Me siento m??s intranquilo(a) y nervioso(a) que de costumbre", questionType,
					answers));
			questions.add(new Question(0, "Me siento atemorizado(a) sin motivo", questionType, answers));
			questions.add(new Question(0, "Me altero o agito con rapidez", questionType, answers));
			questions.add(new Question(0, "Me siento hecho(a) pedazos", questionType, answers));
			questions.add(
					new Question(0, "Creo que todo est?? bien y que no va a pasar nada malo", questionType, answers));
			questions.add(new Question(0, "Me tiemblan las manos, los brazos y las piernas", questionType, answers));
			questions.add(new Question(0, "Sufro dolores de cabeza, de cuello y de la espalda", questionType, answers));
			questions.add(new Question(0, "Me siento d??bil y me canso f??cilmente", questionType, answers));
			questions.add(
					new Question(0, "Me siento tranquilo(a) y me es f??cil estarme quieto(a)", questionType, answers));
			questions.add(new Question(0, "Siento que el coraz??n me late aprisa", questionType, answers));
			questions.add(new Question(0, "Sufro mareos (v??rtigos)", questionType, answers));
			questions.add(new Question(0, "Me desmayo o siento que voy a desmayarme", questionType, answers));
			questions.add(new Question(0, "Puedo respirar f??cilmente", questionType, answers));
			questions.add(new Question(0, "Se me duermen y me hormiguean los dedos de las manos y de los pies",
					questionType, answers));
			questions.add(new Question(0, "Sufro dolores de est??mago e indigesti??n", questionType, answers));
			questions.add(new Question(0, "Tengo que orinar con mucha frecuencia", questionType, answers));
			questions.add(new Question(0, "Por lo general tengo las manos secas y calientes", questionType, answers));
			questions.add(new Question(0, "La cara se me pone caliente y roja", questionType, answers));
			questions.add(new Question(0, "Duermo f??cilmente y descanso bien por las noches", questionType, answers));
			questions.add(new Question(0, "Tengo pesadillas", questionType, answers));

			questionType = questionTypeRepository.findByName("Depresi??n");
			questions.add(new Question(0, "Me siento deca??do y triste", questionType, answers));
			questions.add(new Question(0, "Por la ma??ana es cuando me siento mejor", questionType, answers));
			questions.add(new Question(0, "Siento ganas de llorar o irrumpo en llanto", questionType, answers));
			questions.add(new Question(0, "Tengo problemas para dormir por la noche", questionType, answers));
			questions.add(new Question(0, "Como la misma cantidad de siempre", questionType, answers));
			questions.add(new Question(0, "Todav??a disfruto el sexo", questionType, answers));
			questions.add(new Question(0, "He notado que estoy perdiendo peso", questionType, answers));
			questions.add(new Question(0, "Tengo problemas de estre??imiento", questionType, answers));
			questions.add(new Question(0, "Mi coraz??n late m??s r??pido de lo normal", questionType, answers));
			questions.add(new Question(0, "Me canso sin raz??n alguna", questionType, answers));
			questions.add(new Question(0, "Mi mente est?? tan clara como siempre", questionType, answers));
			questions.add(new Question(0, "Me es f??cil hacer lo que siempre hac??a", questionType, answers));
			questions.add(new Question(0, "Me siento agitado y no puedo estar quieto", questionType, answers));
			questions.add(new Question(0, "Siento esperanza en el futuro", questionType, answers));
			questions.add(new Question(0, "Estoy m??s irritable de lo normal", questionType, answers));
			questions.add(new Question(0, "Me es f??cil tomar decisiones", questionType, answers));
			questions.add(new Question(0, "Siento que soy ??til y me necesitan", questionType, answers));
			questions.add(new Question(0, "Mi vida es bastante plena", questionType, answers));
			questions.add(new Question(0, "Siento que los dem??s estar??an mejor si yo muriera", questionType, answers));
			questions.add(new Question(0, "Todav??a disfruto de las cosas que disfrutaba antes", questionType, answers));

			questionType = questionTypeRepository.findByName("Manifestaciones");
			questions.add(new Question(0,
					"Tengo tres o m??s de los siguientes s??ntomas: dolor de cabeza, n??useas, sudoraci??n, sensaci??n de debilidad o cansancio, dificultad para respirar, aceleraci??n del ritmo card??aco, temblores o aumento de temperatura corporal",
					questionType, answers));
			questions.add(new Question(0,
					"Tengo tres o m??s de los siguientes s??ntomas: bloqueo a la hora de responder, dificultades para seguir una conversaci??n, irritabilidad, susceptibilidad, miedo intenso o angustia",
					questionType, answers));
			questions.add(new Question(0,
					"Tengo tres o m??s de los siguientes s??ntomas: inquietud motora, dificultad para estar quieto o en reposo, estado de alerta o hipervigilancia, aislamiento socialmente o dificultad para tomar decisiones",
					questionType, answers));
			questions.add(new Question(0,
					"Tengo tres o m??s de los siguientes s??ntomas: pensamientos distorcionados, rumiaci??n: pensamientos constantes, dificultades para concentrarme, preocupaciones excesivas, anticipaciones amenazantes o expectativas negativas",
					questionType, answers));

			questionRepository.saveAll(questions);
			return 1;
		} else return -2;
	}
}
