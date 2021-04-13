package com.jyellow.tp2api.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.TestCreateDTO;
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.dto.TestUpdateDTO;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Symptom;
import com.jyellow.tp2api.model.Test;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.SymptomRepository;
import com.jyellow.tp2api.repository.TestRepository;
import com.jyellow.tp2api.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private SymptomRepository symptomRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Transactional
	@Override
	public List<TestDTO> listByPatientDni(String patientDni) {
		List<Test> tests = testRepository.findByPatientUserLoginDni(patientDni);
		return tests.stream().map(test -> modelMapper.map(test, TestDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<TestDTO> listByPatientDniAndFinished(String patientDni, boolean finished) {
		List<Test> tests = testRepository.findByPatientUserLoginDniAndFinished(patientDni, finished);
		return tests.stream().map(test -> modelMapper.map(test, TestDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public TestDTO create(TestCreateDTO testCreateDTO) {
		Patient patient = patientRepository.findByUserLoginDni(testCreateDTO.getPatientDni());
		Test test = new Test();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String strDate = dateFormat.format(date);
		
		test.setStartDate(strDate);
		test.setFinished(false);
		test.setPatient(patient);
		testRepository.save(test);
		return modelMapper.map(test, TestDTO.class);
	}

	@Transactional
	@Override
	public TestDTO cancel(int idTest) {
		Test test = testRepository.findById(idTest).get();
		testRepository.deleteById(idTest);
		return modelMapper.map(test, TestDTO.class);
	}

	@Transactional
	@Override
	public TestDTO update(TestUpdateDTO testUpdateDTO) {
		Test test = testRepository.findById(testUpdateDTO.getIdTest()).get();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String strDate = dateFormat.format(date);
		
		test.setEndDate(strDate);
		test.setFinished(true);
		List<Symptom> symptoms = new ArrayList<Symptom>();
		Symptom symptom = new Symptom();
		for(int idSymptom: testUpdateDTO.getSymptoms()) {
			symptom = symptomRepository.findById(idSymptom).get();
			symptoms.add(symptom);
		}
		test.setSymptoms(symptoms);
		testRepository.save(test);
		return modelMapper.map(test, TestDTO.class);
	}
}
