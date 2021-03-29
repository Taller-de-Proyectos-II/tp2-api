package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.ConferenceDTO;
import com.jyellow.tp2api.model.Conference;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.repository.ConferenceRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.service.ConferenceService;

@Service
public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	ConferenceRepository conferenceRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public void create(ConferenceDTO conferenceDTO) {
		Conference conference = new Conference();
		conference.setName(conferenceDTO.getName());
		conference.setDescription(conferenceDTO.getDescription());
		conference.setPlace(conferenceDTO.getPlace());
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(conferenceDTO.getPsychologistDni());
		conference.setPsychologist(psychologist);
		conferenceRepository.save(conference);
	}

	@Override
	public void update(ConferenceDTO conferenceDTO) {
		Conference conference = conferenceRepository.findById(conferenceDTO.getIdConference()).get();
		conference.setName(conferenceDTO.getName());
		conference.setDescription(conferenceDTO.getDescription());
		conference.setPlace(conferenceDTO.getPlace());
		conferenceRepository.save(conference);
	}

	@Override
	public void delete(int idConference) {
		conferenceRepository.deleteById(idConference);
	}

	@Override
	public List<ConferenceDTO> listByDni(String dni) {
		List<Conference> conferences = conferenceRepository.findByPsychologistUserLoginDni(dni);
		List<ConferenceDTO> conferencesDTO = new ArrayList<ConferenceDTO>();
		ConferenceDTO conferenceDTO = new ConferenceDTO();
		for (Conference conference : conferences) {
			conferenceDTO.setIdConference(conference.getIdConference());
			conferenceDTO.setName(conference.getName());
			conferenceDTO.setDescription(conference.getDescription());
			conferenceDTO.setPlace(conference.getPlace());
			conferenceDTO.setPsychologistDni(dni);
			conferencesDTO.add(conferenceDTO);
		}
		return conferencesDTO;
	}

}
