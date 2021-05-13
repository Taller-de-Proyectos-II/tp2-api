package com.jyellow.tp2api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.model.Patient;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.UserLogin;
import com.jyellow.tp2api.repository.PatientRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.UserLoginRepository;
import com.jyellow.tp2api.service.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PsychologistRepository psychologistRepository;
	
	@Override
	@Transactional
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
	@Transactional
	public boolean existEmailPsychologist(String email) {
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
		Patient patient = patientRepository.findByEmail(email);
		patient.getUserLogin().setPassword(password);
		patientRepository.save(patient);
	}
	
	@Transactional
	@Override
	public void changePasswordPsychologist(String email, String password) {
		Psychologist psychologist = psychologistRepository.findByEmail(email);
		psychologist.getUserLogin().setPassword(password);
		psychologistRepository.save(psychologist);
	}
	
}
