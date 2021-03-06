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

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SymptomServiceImpl implements SymptomService {
	@Autowired
	private SymptomRepository symptomRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<SymptomDTO> listAll() {
		log.info("SymptomServiceImpl: method listAll");
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
	public int createDefault() {
		log.info("SymptomServiceImpl: method createDefault");
		List<Symptom> symptoms = new ArrayList<Symptom>();
		List<AlertAnswer> alertAnswers = new ArrayList<AlertAnswer>();
		List<Symptom> symptomsAux = new ArrayList<Symptom>();
		if (symptomsAux == null || symptomsAux.size() == 0) {
			symptoms.add(new Symptom(0, "Sensación de ahogo u opresión en el pecho", alertAnswers));
			symptoms.add(new Symptom(0, "Miedo o pánico", alertAnswers));
			symptoms.add(new Symptom(0, "Sudoración, escalofríos o temblores", alertAnswers));
			symptoms.add(new Symptom(0, "Palpitaciones o taquicardia", alertAnswers));
			symptoms.add(new Symptom(0, "Náuseas, molestias abdominales o mareos", alertAnswers));
			symptoms.add(new Symptom(0, "Sensación de entumecimiento, hormigueo o irritabilidad", alertAnswers));
			symptomRepository.saveAll(symptoms);
			return 1;
		} else
			return -1;
	}
}
