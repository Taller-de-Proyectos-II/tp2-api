package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.ConferenceDTO;

public interface ConferenceService {
	void create(ConferenceDTO conferenceDTO);
	void update(ConferenceDTO conferenceDTO);
	void delete(int idConference);
	List<ConferenceDTO> listByDni(String dni);
}
