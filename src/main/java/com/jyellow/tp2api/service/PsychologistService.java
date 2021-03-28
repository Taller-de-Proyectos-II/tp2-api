package com.jyellow.tp2api.service;

import com.jyellow.tp2api.dto.PsychologistDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;

public interface PsychologistService {
	int create(PsychologistDTO psychologistDTO, UserLoginDTO userLoginDTO);
	int update(PsychologistDTO psychologistDTO, UserLoginDTO userLoginDTO);
	PsychologistDTO listByDni(String dni);
}
