package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.GuardianDTO;
import com.jyellow.tp2api.model.Guardian;
import com.jyellow.tp2api.model.Image;
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

	@Transactional
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

	@Transactional
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

		Guardian guardian = guardianRepository.findByDni(dni);
		guardian.setImage(image);
		guardianRepository.save(guardian);
	}

	@Transactional
	@Override
	public ByteArrayResource getImage(String dni) {
		byte[] image = guardianRepository.findByDni(dni).getImage().getContent();
		return new ByteArrayResource(image);
	}
}
