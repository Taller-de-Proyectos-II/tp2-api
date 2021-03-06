package com.jyellow.tp2api.service;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.ChangePasswordDTO;
import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.model.Patient;

public interface PatientService {
	Patient validatePatientByDni(String dni);
	int create(PatientDTO patientDTO);
	int update(PatientDTO patientDTO);
	boolean assignToPsychologist(String patientDni, String psychologistDni);
	boolean removePsychologist(String patientDni);
	PatientDTO listByDni(String dni);
	List<PatientDTO> listByPsychologistDni(String psychologistDni);
	void uploadImage(MultipartFile multipartImage, String dni) throws Exception;
	ByteArrayResource getImage(String dni);
	int updatePassword(ChangePasswordDTO changePasswordDTO);
	List<PatientDTO> listAll();
}
