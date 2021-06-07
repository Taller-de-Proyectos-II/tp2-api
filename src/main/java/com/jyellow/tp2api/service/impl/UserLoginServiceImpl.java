package com.jyellow.tp2api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.config.PropertyServiceForJasyptStarter;
import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.UserLogin;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.UserLoginService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserLoginServiceImpl implements UserLoginService {
	
	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	PsychologistRepository psychologistRepository;
	@Autowired
	PropertyServiceForJasyptStarter encryptorService;
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	@Transactional
	public int loginSuccessful(String dni, String password) {
		log.info("UserLoginServiceImpl: method loginSuccessful");
		UserLogin userLogin = userLoginRepository.findByDni(dni);
		log.info("UserLoginServiceImpl: method loginSuccessful");
		if (userLogin == null)
			return -1;
		else {
			if (!encoder.matches(password, userLogin.getPassword()))
				return -2;
			else
				return 1;
		}
	}
	
	@Override
	@Transactional
	public int loginSuccessfulAdmin(String password) {
		log.info("UserLoginServiceImpl: method loginSuccessfulAdmin");
		if (encryptorService.getAdminPassword().equals(password))
			return 1;
		else {
			return -1;
		}
	}
	
	@Override
	@Transactional
	public boolean existEmailPsychologist(String email) {
		log.info("UserLoginServiceImpl: method existEmailPsychologist");
		Psychologist psychologist = psychologistRepository.findByEmail(email);
		if (psychologist == null)
			return false;
		else {
			return true;
		}
	}
	
	@Override
	@Transactional
	public boolean existEmailPatient(String email) {
		log.info("UserLoginServiceImpl: method existEmailPatient");
		Patient patient = patientRepository.findByEmail(email);
		if (patient == null)
			return false;
		else {
			return true;
		}
	}
	
	@Transactional
	@Override
	public void changePasswordPatient(String email, String password) {
		log.info("UserLoginServiceImpl: method changePasswordPatient");
		Patient patient = patientRepository.findByEmail(email);
		patient.getUserLogin().setPassword(encoder.encode(password));
		patientRepository.save(patient);
	}
	
	@Transactional
	@Override
	public void changePasswordPsychologist(String email, String password) {
		log.info("UserLoginServiceImpl: method changePasswordPsychologist");
		Psychologist psychologist = psychologistRepository.findByEmail(email);
		psychologist.getUserLogin().setPassword(encoder.encode(password));
		psychologistRepository.save(psychologist);
	}
	
	@Transactional
	@Override
	public void convertPasswords() {
		List<UserLogin> users = userLoginRepository.findAll();
		for(UserLogin user: users) {
			user.setPassword(encoder.encode("1234"));
			userLoginRepository.save(user);
		}
	}
}
