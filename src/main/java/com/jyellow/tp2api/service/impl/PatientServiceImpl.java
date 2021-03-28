package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.model.Guardian;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.repository.GuardianRepository;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	GuardianRepository guardianRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public Patient validatePatientByDni(String dni) {
		return patientRepository.findByDni(dni);
	}

	@Transactional
	@Override
	public int create(PatientDTO patientDTO) {
		// Comprobar si el dni está registrado con otra cuenta
		Patient patientExist = patientRepository.findByDni(patientDTO.getDni());
		if (patientExist != null)
			return -1;

		// Comprobar si el email está registrado con otra cuenta
		patientExist = patientRepository.findByEmail(patientDTO.getEmail());
		if (patientExist != null)
			return -2;

		Patient patient = new Patient();
		patient.setBirthday(patientDTO.getBirthday());
		patient.setDescription(patientDTO.getDescription());
		patient.setDni(patientDTO.getDni());
		patient.setEmail(patientDTO.getEmail());
		patient.setLastNames(patientDTO.getLastNames());
		patient.setNames(patientDTO.getNames());
		patient.setPhone(patientDTO.getPhone());
		Guardian guardian = guardianRepository.findByUserLoginDni(patientDTO.getGuardianDni());
		patient.setGuardian(guardian);
		patientRepository.save(patient);
		
		return 1;
	}

	@Transactional
	@Override
	public int update(PatientDTO patientDTO) {
		// Comprobar si el email está registrado con otra cuenta
		Patient patientExist = patientRepository.findByEmail(patientDTO.getEmail());
		if (patientExist != null && !patientExist.getDni().equals(patientDTO.getDni()))
			return -1;

		Patient patient = patientRepository.findByDni(patientDTO.getDni());
		patient.setBirthday(patientDTO.getBirthday());
		patient.setDescription(patientDTO.getDescription());
		patient.setDni(patientDTO.getDni());
		patient.setEmail(patientDTO.getEmail());
		patient.setLastNames(patientDTO.getLastNames());
		patient.setNames(patientDTO.getNames());
		patient.setPhone(patientDTO.getPhone());
		patientRepository.save(patient);
		return 1;
	}

	@Transactional
	@Override
	public boolean assign(String patientDni, String psychologistDni) {
		Patient patient = patientRepository.findByDni(patientDni);
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(psychologistDni);
		
		// Comprobar si el paciente ya está asignado
		if (patient.getPsychologist().getUserLogin().getDni().equals(psychologistDni))
			return false;
		
		patient.setPsychologist(psychologist);
		patientRepository.save(patient);
		return true;
	}

	@Override
	public PatientDTO listByDni(String dni) {
		Patient patient = patientRepository.findByDni(dni);
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setBirthday(patient.getBirthday());
		patientDTO.setDescription(patient.getDescription());
		patientDTO.setDni(patient.getDni());
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setGuardianDni(patient.getGuardian().getUserLogin().getDni());
		patientDTO.setLastNames(patient.getLastNames());
		patientDTO.setNames(patient.getNames());
		patientDTO.setPhone(patient.getPhone());
		return patientDTO;
	}

	@Override
	public List<PatientDTO> listByPsychologistDni(String psychologistDni) {
		List<Patient> patients = patientRepository.findByPsychologistUserLoginDni(psychologistDni);
		List<PatientDTO> patientsDTO = new ArrayList<PatientDTO>();
		PatientDTO patientDTO = new PatientDTO();
		for (Patient patient : patients) {
			patientDTO = new PatientDTO();
			patientDTO.setBirthday(patient.getBirthday());
			patientDTO.setDescription(patient.getDescription());
			patientDTO.setDni(patient.getDni());
			patientDTO.setEmail(patient.getEmail());
			patientDTO.setGuardianDni(patient.getGuardian().getUserLogin().getDni());
			patientDTO.setLastNames(patient.getLastNames());
			patientDTO.setNames(patient.getNames());
			patientDTO.setPhone(patient.getPhone());
			patientsDTO.add(patientDTO);
		}
		return patientsDTO;
	}

	@Override
	public List<PatientDTO> listByGuardianDni(String guardianDni) {
		List<Patient> patients = patientRepository.findByGuardianUserLoginDni(guardianDni);
		List<PatientDTO> patientsDTO = new ArrayList<PatientDTO>();
		PatientDTO patientDTO = new PatientDTO();
		for (Patient patient : patients) {
			patientDTO = new PatientDTO();
			patientDTO.setBirthday(patient.getBirthday());
			patientDTO.setDescription(patient.getDescription());
			patientDTO.setDni(patient.getDni());
			patientDTO.setEmail(patient.getEmail());
			patientDTO.setGuardianDni(patient.getGuardian().getUserLogin().getDni());
			patientDTO.setLastNames(patient.getLastNames());
			patientDTO.setNames(patient.getNames());
			patientDTO.setPhone(patient.getPhone());
			patientsDTO.add(patientDTO);
		}
		return patientsDTO;
	}

}
