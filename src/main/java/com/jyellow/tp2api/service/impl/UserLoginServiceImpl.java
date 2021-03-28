package com.jyellow.tp2api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.model.Guardian;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.UserLogin;
import com.jyellow.tp2api.repository.GuardianRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	GuardianRepository guardianRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public int loginSuccessful(String dni, String password) {
		UserLogin userLogin = userLoginRepository.findByDni(dni);
		if (userLogin == null)
			return -1;
		else {
			if (!userLogin.getPassword().equals(password))
				return -2;
			else
				return 1;
		}
	}

	@Override
	public boolean existEmailPsychologist(String email) {
		Psychologist psychologist = psychologistRepository.findByEmail(email);
		if (psychologist == null)
			return false;
		else {
			return true;
		}
	}

	@Override
	public boolean existEmailGuardian(String email) {
		Guardian guardian = guardianRepository.findByEmail(email);
		if (guardian == null)
			return false;
		else {
			return true;
		}
	}

	@Transactional
	@Override
	public void changePasswordGuardian(String email, String password) {
		Guardian guardian = guardianRepository.findByEmail(email);
		guardian.getUserLogin().setPassword(password);
		guardianRepository.save(guardian);
	}

	@Transactional
	@Override
	public void changePasswordPsychologist(String email, String password) {
		Psychologist psychologist = psychologistRepository.findByEmail(email);
		psychologist.getUserLogin().setPassword(password);
		psychologistRepository.save(psychologist);
	}
	
}
