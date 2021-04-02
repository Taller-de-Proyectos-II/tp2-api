package com.jyellow.tp2api.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.PsychologistDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;

public interface PsychologistService {
	int create(PsychologistDTO psychologistDTO, UserLoginDTO userLoginDTO);
	int update(PsychologistDTO psychologistDTO, UserLoginDTO userLoginDTO);
	PsychologistDTO listByDni(String dni);
	void uploadImage(MultipartFile multipartImage, String dni) throws Exception;
	ByteArrayResource getImage(String dni);
}
