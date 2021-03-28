package com.jyellow.tp2api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.EmailDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;
import com.jyellow.tp2api.service.EmailService;
import com.jyellow.tp2api.service.UserLoginService;
import com.jyellow.tp2api.util.PasswordGenerator;

@CrossOrigin
@RestController
@RequestMapping(path = "/login")
public class UserLoginController {

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	EmailService emailService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int loginSuccessful = userLoginService.loginSuccessful(userLoginDTO.getDni(), userLoginDTO.getPassword());
			if (loginSuccessful == -1) {
				responseDTO.setMessage("Usuario no registrado");
				responseDTO.setStatus(0);
			} else if (loginSuccessful == -2) {
				responseDTO.setMessage("Contraseña incorrecta");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Login exitoso");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/restorePasswordPsychologist/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> restorePasswordPsychologist(@RequestBody EmailDTO emailDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean existEmail = userLoginService.existEmailPsychologist(emailDTO.getEmail());
			if (existEmail == false) {
				responseDTO.setMessage("Email no registrado");
				responseDTO.setStatus(0);
			} else {
				String newPassword = PasswordGenerator.getPassword(
						PasswordGenerator.LOWERCASE + PasswordGenerator.UPPERCASE + PasswordGenerator.SPECIAL,
						10);
				userLoginService.changePasswordPsychologist(emailDTO.getEmail(), newPassword);
				emailService.sendEmail(emailDTO.getEmail(),
						"Su nueva contraseña es: " + newPassword);
				responseDTO.setMessage("Email enviado");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/restorePasswordGuardian/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> restorePasswordGuardian(@RequestBody EmailDTO emailDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean existEmail = userLoginService.existEmailGuardian(emailDTO.getEmail());
			if (existEmail == false) {
				responseDTO.setMessage("Email no registrado");
				responseDTO.setStatus(0);
			} else {
				String newPassword = PasswordGenerator.getPassword(
						PasswordGenerator.LOWERCASE + PasswordGenerator.UPPERCASE + PasswordGenerator.SPECIAL,
						10);
				userLoginService.changePasswordGuardian(emailDTO.getEmail(), newPassword);
				emailService.sendEmail(emailDTO.getEmail(),
						"Su nueva contraseña es: " + newPassword);
				responseDTO.setMessage("Email enviado");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

}
