package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jyellow.tp2api.dto.ChangePasswordDTO;
import com.jyellow.tp2api.dto.PsychologistDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;
import com.jyellow.tp2api.model.Image;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.UserLogin;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.PsychologistService;

@Service
public class PsychologistServiceImpl implements PsychologistService {

	@Autowired
	PsychologistRepository psychologistRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Transactional
	@Override
	public int create(PsychologistDTO psychologistDTO, UserLoginDTO userLoginDTO) {

		// Comprobar si el dni está registrado con otra cuenta
		Psychologist psychologistExist = psychologistRepository.findByUserLoginDni(userLoginDTO.getDni());
		if (psychologistExist != null)
			return -2;

		// Comprobar si el email está registrado con otra cuenta
		psychologistExist = psychologistRepository.findByEmail(psychologistDTO.getEmail());
		if (psychologistExist != null)
			return -1;

		// Comprobar si el cpsp está registrado con otra cuenta
		psychologistExist = psychologistRepository.findByCpsp(psychologistDTO.getCpsp());
		if (psychologistExist != null)
			return -3;

		UserLogin userLogin = new UserLogin();
		userLogin.setDni(userLoginDTO.getDni());
		userLogin.setPassword(userLoginDTO.getPassword());

		Psychologist psychologist = new Psychologist();
		psychologist.setBirthday(psychologistDTO.getBirthday());
		psychologist.setCpsp(psychologistDTO.getCpsp());
		psychologist.setDescription(psychologistDTO.getDescription());
		psychologist.setEmail(psychologistDTO.getEmail());
		psychologist.setLastNames(psychologistDTO.getLastNames());
		psychologist.setNames(psychologistDTO.getNames());
		psychologist.setPhone(psychologistDTO.getPhone());
		psychologist.setUserLogin(userLogin);

		psychologistRepository.save(psychologist);
		return 1;
	}

	@Transactional
	@Override
	public int update(PsychologistDTO psychologistDTO) {

		// Comprobar si el email está registrado con otra cuenta
		Psychologist psychologistExist = psychologistRepository.findByEmail(psychologistDTO.getEmail());
		if (psychologistExist != null
				&& !psychologistExist.getUserLogin().getDni().equals(psychologistDTO.getUserLoginDTO().getDni()))
			return -1;

		// Comprobar si el cpsp está registrado con otra cuenta
		psychologistExist = psychologistRepository.findByCpsp(psychologistDTO.getCpsp());
		if (psychologistExist != null
				&& !psychologistExist.getUserLogin().getDni().equals(psychologistDTO.getUserLoginDTO().getDni()))
			return -2;

		Psychologist psychologist = psychologistRepository
				.findByUserLoginDni(psychologistDTO.getUserLoginDTO().getDni());
		psychologist.setBirthday(psychologistDTO.getBirthday());
		psychologist.setCpsp(psychologistDTO.getCpsp());
		psychologist.setDescription(psychologistDTO.getDescription());
		psychologist.setEmail(psychologistDTO.getEmail());
		psychologist.setLastNames(psychologistDTO.getLastNames());
		psychologist.setNames(psychologistDTO.getNames());
		psychologist.setPhone(psychologistDTO.getPhone());
		psychologistRepository.save(psychologist);

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
	public PsychologistDTO listByDni(String dni) {
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(dni);
		PsychologistDTO psychologistDTO = new PsychologistDTO();
		psychologistDTO.setBirthday(psychologist.getBirthday());
		psychologistDTO.setCpsp(psychologist.getCpsp());
		psychologistDTO.setDescription(psychologist.getDescription());
		psychologistDTO.setEmail(psychologist.getEmail());
		psychologistDTO.setLastNames(psychologist.getLastNames());
		psychologistDTO.setNames(psychologist.getNames());
		psychologistDTO.setPhone(psychologist.getPhone());
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		userLoginDTO.setDni(psychologist.getUserLogin().getDni());
		psychologistDTO.setUserLoginDTO(userLoginDTO);
		return psychologistDTO;
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

		Psychologist psychologist = psychologistRepository.findByUserLoginDni(dni);
		psychologist.setImage(image);
		psychologistRepository.save(psychologist);
	}

	@Transactional
	@Override
	public ByteArrayResource getImage(String dni) {
		byte[] image = psychologistRepository.findByUserLoginDni(dni).getImage().getContent();
		return new ByteArrayResource(image);
	}

	@Transactional
	@Override
	public List<PsychologistDTO> listAll() {
		List<Psychologist> psychologists = psychologistRepository.findAll();
		List<PsychologistDTO> psychologistsDTO = new ArrayList<PsychologistDTO>();
		PsychologistDTO psychologistDTO = new PsychologistDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Psychologist psychologist : psychologists) {
			psychologistDTO = new PsychologistDTO();
			psychologistDTO.setBirthday(psychologist.getBirthday());
			psychologistDTO.setCpsp(psychologist.getCpsp());
			psychologistDTO.setDescription(psychologist.getDescription());
			psychologistDTO.setEmail(psychologist.getEmail());
			psychologistDTO.setLastNames(psychologist.getLastNames());
			psychologistDTO.setNames(psychologist.getNames());
			psychologistDTO.setPhone(psychologist.getPhone());
			userLoginDTO = new UserLoginDTO();
			userLoginDTO.setDni(psychologist.getUserLogin().getDni());
			psychologistDTO.setUserLoginDTO(userLoginDTO);
			psychologistsDTO.add(psychologistDTO);
		}
		return psychologistsDTO;
	}

	@Transactional
	@Override
	public List<PsychologistDTO> listByNamesAndLastNames(String names, String lastNames) {
		List<Psychologist> psychologists = new ArrayList<Psychologist>();
		if (names == null && lastNames == null)
			psychologists = psychologistRepository.findAll();
		else if (lastNames == null)
			psychologists = psychologistRepository.findByNamesContainingIgnoreCase(names);
		else if (names == null)
			psychologists = psychologistRepository.findByLastNamesContainingIgnoreCase(lastNames);
		else
			psychologists = psychologistRepository
					.findByNamesContainingIgnoreCaseAndLastNamesContainingIgnoreCase(names, lastNames);
		List<PsychologistDTO> psychologistsDTO = new ArrayList<PsychologistDTO>();
		PsychologistDTO psychologistDTO = new PsychologistDTO();
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		for (Psychologist psychologist : psychologists) {
			psychologistDTO = new PsychologistDTO();
			psychologistDTO.setBirthday(psychologist.getBirthday());
			psychologistDTO.setCpsp(psychologist.getCpsp());
			psychologistDTO.setDescription(psychologist.getDescription());
			psychologistDTO.setEmail(psychologist.getEmail());
			psychologistDTO.setLastNames(psychologist.getLastNames());
			psychologistDTO.setNames(psychologist.getNames());
			psychologistDTO.setPhone(psychologist.getPhone());
			userLoginDTO = new UserLoginDTO();
			userLoginDTO.setDni(psychologist.getUserLogin().getDni());
			psychologistDTO.setUserLoginDTO(userLoginDTO);
			psychologistsDTO.add(psychologistDTO);
		}
		return psychologistsDTO;
	}
}
