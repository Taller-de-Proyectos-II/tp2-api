package com.jyellow.tp2api.service;

import java.text.ParseException;
import java.util.List;

import com.jyellow.tp2api.dto.SessionCreateDTO;
import com.jyellow.tp2api.dto.SessionDTO;
import com.jyellow.tp2api.dto.SessionUpdateDTO;

public interface SessionService {
	List<SessionDTO> listByPsychologistUserLoginDni(String psychologistDni);
	List<SessionDTO> listByPsychologistUserLoginDniAndAceptedAndFinished(String psychologistDni, boolean acepted, boolean finished);
	List<SessionDTO> listByPatientUserLoginDni(String patientDni);
	List<SessionDTO> listByPatientUserLoginDniAndAceptedAndFinished(String patientDni, boolean acepted, boolean finished);
	String create(SessionCreateDTO sessionCreateDTO) throws ParseException;
	SessionDTO updateAcepted(SessionUpdateDTO sessionUpdateDTO);
	SessionDTO updateFinished(int idSession);
	SessionDTO listById(int idSession);
}
