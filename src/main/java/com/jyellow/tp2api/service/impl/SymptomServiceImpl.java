package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.SymptomDTO;
import com.jyellow.tp2api.model.AlertAnswer;
import com.jyellow.tp2api.model.Symptom;
import com.jyellow.tp2api.repository.SymptomRepository;
import com.jyellow.tp2api.service.SymptomService;

@Service
public class SymptomServiceImpl implements SymptomService {
	@Autowired
	private SymptomRepository symptomRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<SymptomDTO> listAll() {
		List<Symptom> symptoms = symptomRepository.findAll();
		List<SymptomDTO> symptomsDTO = new ArrayList<SymptomDTO>();
		SymptomDTO symptomDTO = new SymptomDTO();
		for (Symptom symptom : symptoms) {
			symptomDTO = new SymptomDTO();
			symptomDTO = modelMapper.map(symptom, SymptomDTO.class);
			symptomsDTO.add(symptomDTO);
		}
		return symptomsDTO;
	}

	@Override
	public void createDefault() {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		List<AlertAnswer> alertAnswers = new ArrayList<AlertAnswer>();
		symptoms.add(new Symptom(0, "Sensación de ahogo u opresión en el pecho", alertAnswers));
		symptoms.add(new Symptom(0, "Miedo o pánico", alertAnswers));
		symptoms.add(new Symptom(0, "Sudoración, escalofríos o temblores", alertAnswers));
		symptoms.add(new Symptom(0, "Palpitaciones o taquicardia", alertAnswers));
		symptoms.add(new Symptom(0, "Náuseas, molestias abdominales o mareos", alertAnswers));
		symptoms.add(new Symptom(0, "Sensación de entumecimiento, hormigueo o irritabilidad", alertAnswers));
		symptomRepository.saveAll(symptoms);
	}
}
