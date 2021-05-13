package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.ChangePasswordDTO;
import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;
import com.jyellow.tp2api.model.Image;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.UserLogin;
import com.jyellow.tp2api.repository.GuardianRepository;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	GuardianRepository guardianRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public Patient validatePatientByDni(String dni) {
		return patientRepository.findByUserLoginDni(dni);
	}

	@Transactional
	@Override
	public int create(PatientDTO patientDTO) {
		// Comprobar si el dni está registrado con otra cuenta
		Patient patientExist = patientRepository.findByUserLoginDni(patientDTO.getUserLoginDTO().getDni());
		if (patientExist != null)
			return -1;

		// Comprobar si el email está registrado con otra cuenta
		patientExist = patientRepository.findByEmail(patientDTO.getEmail());
		if (patientExist != null)
			return -2;

		UserLogin userLogin = new UserLogin();
		userLogin.setDni(patientDTO.getUserLoginDTO().getDni());
		userLogin.setPassword(patientDTO.getUserLoginDTO().getPassword());

		Patient patient = new Patient();
		patient.setBirthday(patientDTO.getBirthday());
		patient.setDescription(patientDTO.getDescription());
		patient.setEmail(patientDTO.getEmail());
		patient.setLastNames(patientDTO.getLastNames());
		patient.setNames(patientDTO.getNames());
		patient.setPhone(patientDTO.getPhone());

		patient.setUserLogin(userLogin);
		patientRepository.save(patient);

		return 1;
	}

	@Transactional
	@Override
	public int update(PatientDTO patientDTO) {
		// Comprobar si el email está registrado con otra cuenta
		Patient patientExist = patientRepository.findByEmail(patientDTO.getEmail());
		if (patientExist != null && !patientExist.getUserLogin().getDni().equals(patientDTO.getUserLoginDTO().getDni()))
			return -1;

		Patient patient = patientRepository.findByUserLoginDni(patientDTO.getUserLoginDTO().getDni());
		patient.setBirthday(patientDTO.getBirthday());
		patient.setDescription(patientDTO.getDescription());
		patient.setEmail(patientDTO.getEmail());
		patient.setLastNames(patientDTO.getLastNames());
		patient.setNames(patientDTO.getNames());
		patient.setPhone(patientDTO.getPhone());
		patientRepository.save(patient);
		return 1;
	}

	@Transactional
	@Override
	public int updatePassword(ChangePasswordDTO changePasswordDTO) {
		UserLogin userLogin = userLoginRepository.findByDni(changePasswordDTO.getDni());
		if (userLogin == null) {
			return -1;
		}
		if (!userLogin.getPassword().equals(changePasswordDTO.getPassword())) {
			return -2;
		}
		userLogin.setPassword(changePasswordDTO.getNewPassword());
		userLoginRepository.save(userLogin);
		return 1;
	}

	@Transactional
	@Override
	public boolean assignToPsychologist(String patientDni, String psychologistDni) {
		Patient patient = patientRepository.findByUserLoginDni(patientDni);
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(psychologistDni);
		Patient patientExist = patientRepository.findByUserLoginDniAndPsychologistUserLoginDni(patientDni,
				psychologistDni);

		// Comprobar si el paciente ya está asignado
		if (patientExist != null)
			return false;

		patient.setPsychologist(psychologist);
		patientRepository.save(patient);
		return true;
	}

	@Transactional
	@Override
	public PatientDTO listByDni(String dni) {
		Patient patient = patientRepository.findByUserLoginDni(dni);
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setBirthday(patient.getBirthday());
		patientDTO.setDescription(patient.getDescription());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setLastNames(patient.getLastNames());
		patientDTO.setNames(patient.getNames());
		patientDTO.setPhone(patient.getPhone());
		patientDTO.setUserLoginDTO(new UserLoginDTO(patient.getUserLogin().getDni(), ""));
		return patientDTO;
	}

	@Transactional
	@Override
	public List<PatientDTO> listByPsychologistDni(String psychologistDni) {
		List<Patient> patients = patientRepository.findByPsychologistUserLoginDni(psychologistDni);
		List<PatientDTO> patientsDTO = new ArrayList<PatientDTO>();
		PatientDTO patientDTO = new PatientDTO();
		for (Patient patient : patients) {
			patientDTO = new PatientDTO();
			patientDTO.setBirthday(patient.getBirthday());
			patientDTO.setDescription(patient.getDescription());
			patientDTO.setEmail(patient.getEmail());
			patientDTO.setLastNames(patient.getLastNames());
			patientDTO.setNames(patient.getNames());
			patientDTO.setPhone(patient.getPhone());
			patientDTO.setUserLoginDTO(new UserLoginDTO(patient.getUserLogin().getDni(), ""));
			patientsDTO.add(patientDTO);
		}
		return patientsDTO;
	}

	@Transactional
	@Override
	public boolean removePsychologist(String patientDni) {
		Patient patient = patientRepository.findByUserLoginDni(patientDni);
		patient.setPsychologist(null);
		patientRepository.save(patient);
		return true;
	}

	@Transactional
	@Override
	public void uploadImage(MultipartFile multipartImage, String dni) throws Exception {
		Image image = new Image();
		try {
			image.setName(multipartImage.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setContent(multipartImage.getBytes());

		Patient patient = patientRepository.findByUserLoginDni(dni);
		patient.setImage(image);
		patientRepository.save(patient);
	}

	@Transactional
	@Override
	public ByteArrayResource getImage(String dni) {
		byte[] image = patientRepository.findByUserLoginDni(dni).getImage().getContent();
		return new ByteArrayResource(image);
	}
}
