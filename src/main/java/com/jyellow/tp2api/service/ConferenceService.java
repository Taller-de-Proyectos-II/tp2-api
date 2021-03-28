package com.jyellow.tp2api.service;

import com.jyellow.tp2api.dto.ConferenceDTO;

public interface ConferenceService {
	void create(ConferenceDTO conferenceDTO);
	void update(ConferenceDTO conferenceDTO);
	void delete(int idConference);
}
