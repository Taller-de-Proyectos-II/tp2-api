package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.RecomendationDTO;

public interface RecomendationService {
	List<RecomendationDTO> listAll();

	void createDefault();
}
