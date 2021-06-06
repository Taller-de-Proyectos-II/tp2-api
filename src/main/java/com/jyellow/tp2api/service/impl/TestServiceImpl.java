package com.jyellow.tp2api.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.AnswerCreateDTO;
import com.jyellow.tp2api.dto.AnswerDTO;
import com.jyellow.tp2api.dto.DataScoreDTO;
import com.jyellow.tp2api.dto.QuestionDTO;
import com.jyellow.tp2api.dto.TestCreateDTO;
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.dto.TestDashboardDTO;
import com.jyellow.tp2api.dto.TestUpdateDTO;
import com.jyellow.tp2api.model.Answer;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Question;
import com.jyellow.tp2api.model.Test;
import com.jyellow.tp2api.repository.AnswerRepository;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.QuestionRepository;
import com.jyellow.tp2api.repository.TestRepository;
import com.jyellow.tp2api.service.TestService;
import com.jyellow.tp2api.util.ScoreOperation;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public List<TestDTO> listByPatientDni(String patientDni) {
		log.info("TestServiceImpl: method listByPatientDni");
		List<Test> tests = testRepository.findByPatientUserLoginDni(patientDni);
		Collections.reverse(tests);
		List<TestDTO> testsDTO = new ArrayList<TestDTO>();
		TestDTO testDTO = new TestDTO();
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Test test : tests) {
			testDTO = new TestDTO();
			testDTO = modelMapper.map(test, TestDTO.class);
			answersDTO = new ArrayList<AnswerDTO>();
			answerDTO = new AnswerDTO();
			questionDTO = new QuestionDTO();
			for (Answer answer : test.getAnswers()) {
				answerDTO = new AnswerDTO();
				questionDTO = new QuestionDTO();
				answerDTO = modelMapper.map(answer, AnswerDTO.class);
				questionDTO = modelMapper.map(answer.getQuestion(), QuestionDTO.class);
				answerDTO.setQuestionDTO(questionDTO);
				answersDTO.add(answerDTO);
			}
			testDTO.setAnswersDTO(answersDTO);
			testsDTO.add(testDTO);
		}
		return testsDTO;
	}

	@Override
	@Transactional
	public List<TestDTO> listByPatientDniAndTestType(String patientDni, String testType) {
		log.info("TestServiceImpl: method listByPatientDniAndTestType");
		List<Test> tests = testRepository.findByPatientUserLoginDniAndTestType(patientDni, testType);
		Collections.reverse(tests);
		List<TestDTO> testsDTO = new ArrayList<TestDTO>();
		TestDTO testDTO = new TestDTO();
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Test test : tests) {
			testDTO = new TestDTO();
			testDTO = modelMapper.map(test, TestDTO.class);
			answersDTO = new ArrayList<AnswerDTO>();
			answerDTO = new AnswerDTO();
			questionDTO = new QuestionDTO();
			for (Answer answer : test.getAnswers()) {
				answerDTO = new AnswerDTO();
				questionDTO = new QuestionDTO();
				answerDTO = modelMapper.map(answer, AnswerDTO.class);
				questionDTO = modelMapper.map(answer.getQuestion(), QuestionDTO.class);
				answerDTO.setQuestionDTO(questionDTO);
				answersDTO.add(answerDTO);
			}
			testDTO.setAnswersDTO(answersDTO);
			testsDTO.add(testDTO);
		}
		return testsDTO;
	}

	@Transactional
	@Override
	public List<TestDashboardDTO> listByPatientDniAndDates(String patientDni, String startDate, String endDate)
			throws ParseException {
		log.info("TestServiceImpl: method listByPatientDniAndDates");
		List<Test> tests = testRepository.findByPatientUserLoginDniAndFinished(patientDni, true);
		List<TestDashboardDTO> testsDTO = new ArrayList<TestDashboardDTO>();
		TestDashboardDTO testDashboardDTO = new TestDashboardDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		for (Test test : tests) {
			if ((formatter.parse(startDate).before(formatter.parse(test.getStartDate()))
					|| startDate.equals(test.getStartDate()))
					&& (formatter.parse(endDate).after(formatter.parse(test.getEndDate()))
							|| endDate.equals(test.getEndDate()))) {
				testDashboardDTO = new TestDashboardDTO();
				testDashboardDTO = modelMapper.map(test, TestDashboardDTO.class);
				testsDTO.add(testDashboardDTO);
			}
		}
		return testsDTO;
	}

	@Transactional
	@Override
	public List<TestDTO> listByPatientDniAndDatesManifestation(String patientDni, String startDate, String endDate)
			throws ParseException {
		log.info("TestServiceImpl: method listByPatientDniAndDatesManifestation");
		List<Test> tests = testRepository.findByPatientUserLoginDniAndFinished(patientDni, true);
		List<TestDTO> testsDTO = new ArrayList<TestDTO>();
		TestDTO testDTO = new TestDTO();
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		for (Test test : tests) {
			if ((formatter.parse(startDate).before(formatter.parse(test.getStartDate()))
					|| startDate.equals(test.getStartDate()))
					&& (formatter.parse(endDate).after(formatter.parse(test.getEndDate()))
							|| endDate.equals(test.getEndDate()))) {
				testDTO = new TestDTO();
				testDTO = modelMapper.map(test, TestDTO.class);
				answersDTO = new ArrayList<AnswerDTO>();
				answerDTO = new AnswerDTO();
				for (Answer answer : test.getAnswers()) {
					answerDTO = new AnswerDTO();
					answerDTO = modelMapper.map(answer, AnswerDTO.class);
					answersDTO.add(answerDTO);
				}
				testDTO.setAnswersDTO(answersDTO);
				testsDTO.add(testDTO);
			}
		}
		return testsDTO;
	}
	
	@Transactional
	@Override
	public List<TestDTO> listByPsychologistDniAndDatesManifestation(String psychologistDni, String startDate, String endDate)
			throws ParseException {
		log.info("TestServiceImpl: method listByPsychologistDniAndDatesManifestation");
		List<Test> tests = testRepository.findByPatientPsychologistUserLoginDniAndFinished(psychologistDni, true);
		List<TestDTO> testsDTO = new ArrayList<TestDTO>();
		TestDTO testDTO = new TestDTO();
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		for (Test test : tests) {
			if ((formatter.parse(startDate).before(formatter.parse(test.getStartDate()))
					|| startDate.equals(test.getStartDate()))
					&& (formatter.parse(endDate).after(formatter.parse(test.getEndDate()))
							|| endDate.equals(test.getEndDate()))) {
				testDTO = new TestDTO();
				testDTO = modelMapper.map(test, TestDTO.class);
				answersDTO = new ArrayList<AnswerDTO>();
				answerDTO = new AnswerDTO();
				for (Answer answer : test.getAnswers()) {
					answerDTO = new AnswerDTO();
					answerDTO = modelMapper.map(answer, AnswerDTO.class);
					answersDTO.add(answerDTO);
				}
				testDTO.setAnswersDTO(answersDTO);
				testsDTO.add(testDTO);
			}
		}
		return testsDTO;
	}


	@Transactional
	@Override
	public List<TestDTO> listByPatientDniAndFinished(String patientDni, boolean finished) {
		log.info("TestServiceImpl: method listByPatientDniAndFinished");
		List<Test> tests = testRepository.findByPatientUserLoginDniAndFinished(patientDni, finished);
		Collections.reverse(tests);
		List<TestDTO> testsDTO = new ArrayList<TestDTO>();
		TestDTO testDTO = new TestDTO();
		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Test test : tests) {
			testDTO = new TestDTO();
			testDTO = modelMapper.map(test, TestDTO.class);
			answersDTO = new ArrayList<AnswerDTO>();
			answerDTO = new AnswerDTO();
			questionDTO = new QuestionDTO();
			for (Answer answer : test.getAnswers()) {
				answerDTO = new AnswerDTO();
				questionDTO = new QuestionDTO();
				answerDTO = modelMapper.map(answer, AnswerDTO.class);
				questionDTO = modelMapper.map(answer.getQuestion(), QuestionDTO.class);
				answerDTO.setQuestionDTO(questionDTO);
				answersDTO.add(answerDTO);
			}
			testDTO.setAnswersDTO(answersDTO);
			testsDTO.add(testDTO);
		}
		return testsDTO;
	}

	@Transactional
	@Override
	public TestDTO create(TestCreateDTO testCreateDTO) {
		log.info("TestServiceImpl: method create");
		Patient patient = patientRepository.findByUserLoginDni(testCreateDTO.getPatientDni());
		Test test = new Test();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		DateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
		test.setStartDate(dateFormat.format(date));
		test.setStartHour(dateFormatHour.format(date));
		test.setFinished(false);
		test.setPatient(patient);
		test.setColor("white");
		test.setScore(0);
		List<Question> questions = questionRepository
				.findByQuestionTypeIdQuestionType(testCreateDTO.getIdQuestionType());
		List<Answer> answers = new ArrayList<Answer>();
		for (Question question : questions) {
			answers.add(new Answer(0, 0, 0, question, test));
		}
		List<Answer> answersAux = answerRepository.saveAll(answers);
		test.setAnswers(answersAux);
		test.setTestType(answers.get(0).getQuestion().getQuestionType().getName());
		test.setDiagnostic("");
		testRepository.save(test);

		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Answer answer : answersAux) {
			answerDTO = new AnswerDTO();
			questionDTO = new QuestionDTO();
			answerDTO = modelMapper.map(answer, AnswerDTO.class);
			questionDTO = modelMapper.map(answer.getQuestion(), QuestionDTO.class);
			answerDTO.setQuestionDTO(questionDTO);
			answersDTO.add(answerDTO);
		}
		TestDTO testDTO = modelMapper.map(test, TestDTO.class);
		testDTO.setAnswersDTO(answersDTO);

		return testDTO;
	}

	@Transactional
	@Override
	public TestDTO cancel(int idTest) {
		log.info("TestServiceImpl: method cancel");
		Test test = testRepository.findById(idTest).get();
		answerRepository.deleteAll(test.getAnswers());
		testRepository.deleteById(idTest);
		return modelMapper.map(test, TestDTO.class);
	}

	@Transactional
	@Override
	public TestDTO update(TestUpdateDTO testUpdateDTO) {
		log.info("TestServiceImpl: method update");
		Test test = testRepository.findById(testUpdateDTO.getIdTest()).get();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		DateFormat dateFormatHour = new SimpleDateFormat("HH:mm");
		test.setEndDate(dateFormat.format(date));
		test.setEndHour(dateFormatHour.format(date));
		test.setFinished(true);

		List<Answer> answers = new ArrayList<Answer>();
		Answer answer = new Answer();
		for (AnswerCreateDTO answerDTO : testUpdateDTO.getAnswersDTO()) {
			answer = answerRepository.findById(answerDTO.getIdAnswer()).get();
			answer.setScore(answerDTO.getScore());
			answers.add(answer);
		}
		List<Answer> answersAux = ScoreOperation.asignRealScore(answers, test.getTestType());
		DataScoreDTO dataScoreDTO = ScoreOperation.getDiagnostic(answersAux, test.getTestType());
		test.setDiagnostic(dataScoreDTO.getDiagnostic());
		test.setScore(dataScoreDTO.getScore());
		test.setColor(dataScoreDTO.getColor());
		answerRepository.saveAll(answersAux);
		testRepository.save(test);

		List<AnswerDTO> answersDTO = new ArrayList<AnswerDTO>();
		AnswerDTO answerDTO = new AnswerDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		for (Answer answerAux : answersAux) {
			answerDTO = new AnswerDTO();
			questionDTO = new QuestionDTO();
			answerDTO = modelMapper.map(answerAux, AnswerDTO.class);
			questionDTO = modelMapper.map(answerAux.getQuestion(), QuestionDTO.class);
			answerDTO.setQuestionDTO(questionDTO);
			answersDTO.add(answerDTO);
		}
		TestDTO testDTO = modelMapper.map(test, TestDTO.class);
		testDTO.setAnswersDTO(answersDTO);

		return testDTO;
	}
	
	@Transactional
	@Override
	public List<TestDashboardDTO> listByPsychologistDniAndDates(String psychologistDni, String startDate, String endDate)
			throws ParseException {
		log.info("TestServiceImpl: method listByPsychologistDniAndDates");
		List<Test> tests = testRepository.findByPatientPsychologistUserLoginDniAndFinished(psychologistDni, true);
		List<TestDashboardDTO> testsDTO = new ArrayList<TestDashboardDTO>();
		TestDashboardDTO testDashboardDTO = new TestDashboardDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		for (Test test : tests) {
			if ((formatter.parse(startDate).before(formatter.parse(test.getStartDate()))
					|| startDate.equals(test.getStartDate()))
					&& (formatter.parse(endDate).after(formatter.parse(test.getEndDate()))
							|| endDate.equals(test.getEndDate()))) {
				testDashboardDTO = new TestDashboardDTO();
				testDashboardDTO = modelMapper.map(test, TestDashboardDTO.class);
				testsDTO.add(testDashboardDTO);
			}
		}
		return testsDTO;
	}
}
