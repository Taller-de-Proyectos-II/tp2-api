package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.ManifestationDTO;
import com.jyellow.tp2api.dto.SymptomDTO;
import com.jyellow.tp2api.model.Manifestation;
import com.jyellow.tp2api.model.Symptom;
import com.jyellow.tp2api.repository.ManifestationRepository;
import com.jyellow.tp2api.service.ManifestationService;

@Service
public class ManifestationServiceImpl implements  ManifestationService {

	@Autowired
	private ManifestationRepository manifestationRepository;
	
	@Override
	public ManifestationDTO listByIdManifestation(int idManifestation) {
		Manifestation manifestation = manifestationRepository.findById(idManifestation).get();
		ManifestationDTO manifestationDTO = new ManifestationDTO();
		List<SymptomDTO> symptomsDTO = new ArrayList<SymptomDTO>();
		SymptomDTO symptomDTO = new SymptomDTO();
		manifestationDTO.setIdManifestation(idManifestation);
		manifestationDTO.setName(manifestation.getName());
		manifestationDTO.setDescription(manifestation.getDescription());
		for(Symptom symptom: manifestation.getSymptoms()) {
			symptomDTO = new SymptomDTO();;
			symptomDTO.setIdSymptom(symptom.getIdSymptom());
			symptomDTO.setName(symptom.getName());
			symptomDTO.setDescription(symptom.getDescription());
			symptomDTO.setIdManifestation(symptom.getManifestation().getIdManifestation());
			symptomsDTO.add(symptomDTO);
		}
		manifestationDTO.setSymptoms(symptomsDTO);
		return manifestationDTO;
	}

}
