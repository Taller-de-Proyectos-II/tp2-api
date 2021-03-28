package com.jyellow.tp2api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.dto.GuardianDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;
import com.jyellow.tp2api.model.Guardian;
import com.jyellow.tp2api.model.UserLogin;
import com.jyellow.tp2api.repository.GuardianRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.GuardianService;

@Service
public class GuardianServiceImpl implements GuardianService {

	@Autowired
	GuardianRepository guardianRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Transactional
	@Override
	public int create(GuardianDTO guardianDTO, UserLoginDTO userLoginDTO) {

		// Comprobar si el dni está registrado con otra cuenta
		Guardian guardianExist = guardianRepository.findByUserLoginDni(userLoginDTO.getDni());
		if (guardianExist != null)
			return -2;
		
		// Comprobar si el email está registrado con otra cuenta
		guardianExist = guardianRepository.findByEmail(guardianDTO.getEmail());
		if (guardianExist != null)
			return -1;

		UserLogin userLogin = new UserLogin();
		userLogin.setDni(userLoginDTO.getDni());
		userLogin.setPassword(userLoginDTO.getPassword());

		Guardian guardian = new Guardian();
		guardian.setBirthday(guardianDTO.getBirthday());
		guardian.setEmail(guardianDTO.getEmail());
		guardian.setLastNames(guardianDTO.getLastNames());
		guardian.setNames(guardianDTO.getNames());
		guardian.setPhone(guardianDTO.getPhone());
		guardian.setUserLogin(userLogin);

		guardianRepository.save(guardian);
		return 1;
	}

	@Transactional
	@Override
	public int update(GuardianDTO guardianDTO, UserLoginDTO userLoginDTO) {

		// Comprobar si el email está registrado con otra cuenta
		Guardian guardianExist = guardianRepository.findByEmail(guardianDTO.getEmail());
		if (guardianExist != null && !guardianExist.getUserLogin().getDni().equals(userLoginDTO.getDni()))
			return -1;

		Guardian guardian = guardianRepository.findByUserLoginDni(userLoginDTO.getDni());
		guardian.setBirthday(guardianDTO.getBirthday());
		guardian.setEmail(guardianDTO.getEmail());
		guardian.setLastNames(guardianDTO.getLastNames());
		guardian.setNames(guardianDTO.getNames());
		guardian.setPhone(guardianDTO.getPhone());
		guardian.getUserLogin().setPassword(userLoginDTO.getPassword());

		guardianRepository.save(guardian);
		return 1;
	}
	
	@Override
	public GuardianDTO listByDni(String dni) {
		Guardian guardian = guardianRepository.findByUserLoginDni(dni);
		GuardianDTO guardianDTO = new GuardianDTO();
		guardianDTO.setBirthday(guardian.getBirthday());
		guardianDTO.setEmail(guardian.getEmail());
		guardianDTO.setLastNames(guardian.getLastNames());
		guardianDTO.setNames(guardian.getNames());
		guardianDTO.setPhone(guardian.getPhone());
		return guardianDTO;
	}
}
