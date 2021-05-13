package com.jyellow.tp2api.service;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.ChangePasswordDTO;
import com.jyellow.tp2api.dto.PsychologistDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;

public interface PsychologistService {
	int create(PsychologistDTO psychologistDTO, UserLoginDTO userLoginDTO);
	PsychologistDTO listByDni(String dni);
	void uploadImage(MultipartFile multipartImage, String dni) throws Exception;
	ByteArrayResource getImage(String dni);
	List<PsychologistDTO> listAll();
	List<PsychologistDTO> listByNamesAndLastNames(String names, String lastNames);
	int update(PsychologistDTO psychologistDTO);
	int updatePassword(ChangePasswordDTO changePasswordDTO);
}
