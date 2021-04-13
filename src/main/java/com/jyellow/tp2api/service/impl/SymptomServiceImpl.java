package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.SymptomDTO;
import com.jyellow.tp2api.model.Symptom;
import com.jyellow.tp2api.repository.SymptomRepository;
import com.jyellow.tp2api.service.SymptomService;

@Service
public class SymptomServiceImpl implements SymptomService {
	
	@Autowired
	private SymptomRepository symptomRepository;

	@Transactional
	@Override
	public List<SymptomDTO> listAll() {
		List<Symptom> symptoms = symptomRepository.findAll();
		List<SymptomDTO> symptomsDTO = new ArrayList<SymptomDTO>();
		SymptomDTO symptomDTO = new SymptomDTO();
		for(Symptom symptom: symptoms) {
			symptomDTO = new SymptomDTO();
			symptomDTO.setIdSymptom(symptom.getIdSymptom());
			symptomDTO.setIdManifestation(symptom.getManifestation().getIdManifestation());
			symptomDTO.setName(symptom.getName());
			symptomDTO.setDescription(symptom.getDescription());
			symptomsDTO.add(symptomDTO);
		}
		return symptomsDTO;
	}
	
	
	
}
