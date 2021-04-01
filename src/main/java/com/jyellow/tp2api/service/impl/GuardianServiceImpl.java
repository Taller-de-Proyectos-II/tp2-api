package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.dto.GuardianDTO;
import com.jyellow.tp2api.model.Guardian;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.repository.GuardianRepository;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.GuardianService;

@Service
public class GuardianServiceImpl implements GuardianService {

	@Autowired
	GuardianRepository guardianRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	PatientRepository patientRepository;

	@Transactional
	@Override
	public int create(GuardianDTO guardianDTO) {
		Patient patient = patientRepository.findByUserLoginDni(guardianDTO.getPatientDni());
		Guardian guardian = new Guardian();
		guardian.setBirthday(guardianDTO.getBirthday());
		guardian.setEmail(guardianDTO.getEmail());
		guardian.setLastNames(guardianDTO.getLastNames());
		guardian.setNames(guardianDTO.getNames());
		guardian.setPhone(guardianDTO.getPhone());
		guardian.setDni(guardianDTO.getDni());
		guardian.setPatient(patient);

		guardianRepository.save(guardian);
		return 1;
	}
	// find by dni and patient dni
	@Transactional
	@Override
	public int update(GuardianDTO guardianDTO) {
		Guardian guardian = guardianRepository.findByDni(guardianDTO.getDni());
		guardian.setBirthday(guardianDTO.getBirthday());
		guardian.setEmail(guardianDTO.getEmail());
		guardian.setLastNames(guardianDTO.getLastNames());
		guardian.setNames(guardianDTO.getNames());
		guardian.setPhone(guardianDTO.getPhone());
		guardianRepository.save(guardian);
		return 1;
	}

	@Override
	public GuardianDTO listByDniAndPatientDni(String dni, String patientDni) {
		Guardian guardian = guardianRepository.findByDniAndPatientUserLoginDni(dni, patientDni);
		GuardianDTO guardianDTO = new GuardianDTO();
		guardianDTO.setBirthday(guardian.getBirthday());
		guardianDTO.setEmail(guardian.getEmail());
		guardianDTO.setLastNames(guardian.getLastNames());
		guardianDTO.setNames(guardian.getNames());
		guardianDTO.setPhone(guardian.getPhone());
		guardianDTO.setDni(guardian.getDni());
		guardianDTO.setPatientDni(guardian.getPatient().getUserLogin().getDni());
		return guardianDTO;
	}

	@Override
	public List<GuardianDTO> listByPatientDni(String patientDni) {
		List<Guardian> guardians = guardianRepository.findByPatientUserLoginDni(patientDni);
		List<GuardianDTO> guardiansDTO = new ArrayList<GuardianDTO>();
		GuardianDTO guardianDTO = new GuardianDTO();

		for (Guardian guardian : guardians) {
			guardianDTO = new GuardianDTO();
			guardianDTO.setBirthday(guardian.getBirthday());
			guardianDTO.setEmail(guardian.getEmail());
			guardianDTO.setLastNames(guardian.getLastNames());
			guardianDTO.setNames(guardian.getNames());
			guardianDTO.setPhone(guardian.getPhone());
			guardianDTO.setPatientDni(patientDni);
			guardianDTO.setDni(guardian.getDni());
			guardiansDTO.add(guardianDTO);
		}
		return guardiansDTO;
	}
}
