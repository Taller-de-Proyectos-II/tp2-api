package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.RecomendationDTO;
import com.jyellow.tp2api.model.Recomendation;
import com.jyellow.tp2api.repository.RecomendationRepository;
import com.jyellow.tp2api.service.RecomendationService;

@Service
public class RecomendationServiceImpl implements RecomendationService {
	@Autowired
	private RecomendationRepository recomendationRepository;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<RecomendationDTO> listAll() {
		List<Recomendation> recomendations = recomendationRepository.findAll();
		List<RecomendationDTO> recomendationsDTO = new ArrayList<RecomendationDTO>();
		for (Recomendation recomendation : recomendations) {
			recomendationsDTO.add(modelMapper.map(recomendation, RecomendationDTO.class));
		}
		return recomendationsDTO;
	}

	@Override
	public void createDefault() {
		List<Recomendation> recomendations = new ArrayList<Recomendation>();
		recomendations.add(new Recomendation(0,
				"Respire despacio y profundamente. Inspire por la nariz llenando el abdomen y expire lentamente por la boca"));
		recomendations.add(new Recomendation(0,
				"Busque un sitio tranquilo, túmbese o póngase cómodo, ponga música relajante y piense en situaciones agradables"));
		recomendations.add(new Recomendation(0,
				"Hable con alguna persona de su confianza, que conozca lo que le sucede y que tenga instrucciones de cómo ayudarle"));
		recomendations.add(new Recomendation(0,
				"Desvíe su atención hacia un tema que le haga sentir tranquilo o que sea de su agrado"));
		recomendations.add(new Recomendation(0,
				"Relaje su cuerpo. Intente realizar pequeños ejercicios de estiramiento, sin forzar sus músculos"));
		recomendationRepository.saveAll(recomendations);
	}
}
