package com.jyellow.tp2api.service;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.GuardianDTO;

public interface GuardianService {
	
	int create(GuardianDTO guardianDTO);
	int update(GuardianDTO guardianDTO);
	List<GuardianDTO> listByPatientDni(String patientDni);
	GuardianDTO listByDniAndPatientDni(String dni, String patientDni);
	void uploadImage(MultipartFile multipartImage, String dni, String patientDni) throws Exception;
	int delete(String dni, String patientDni);
	ByteArrayResource getImage(String dni, String patientDni);
}
