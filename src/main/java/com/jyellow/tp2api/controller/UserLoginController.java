package com.jyellow.tp2api.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.EmailDTO;
import com.jyellow.tp2api.dto.PatientDTO;
import com.jyellow.tp2api.dto.PsychologistDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;
import com.jyellow.tp2api.service.EmailService;
import com.jyellow.tp2api.service.PatientService;
import com.jyellow.tp2api.service.PsychologistService;
import com.jyellow.tp2api.service.UserLoginService;
import com.jyellow.tp2api.util.PasswordGenerator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/login")
@Log4j2
public class UserLoginController {

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	PatientService patientService;

	@Autowired
	PsychologistService psychologistService;

	@Autowired
	EmailService emailService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
		log.info("UserLoginController: method login");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int loginSuccessful = userLoginService.loginSuccessful(userLoginDTO.getDni(), userLoginDTO.getPassword());
			if (loginSuccessful == -1) {
				responseDTO.setMessage("Usuario no registrado");
				responseDTO.setStatus(0);
			} else if (loginSuccessful == -2) {
				responseDTO.setMessage("Contrase??a incorrecta");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Login exitoso");
				String token = getJWTToken(userLoginDTO.getDni(), userLoginDTO.getPassword());
				responseDTO.setToken(token);
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/loginPsychologist/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> loginPsychologist(@RequestBody UserLoginDTO userLoginDTO) {
		log.info("UserLoginController: method loginPsychologist");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int loginSuccessful = userLoginService.loginSuccessfulPsychologist(userLoginDTO.getDni(),
					userLoginDTO.getPassword());
			if (loginSuccessful == -1) {
				responseDTO.setMessage("Usuario no registrado");
				responseDTO.setStatus(0);
			} else if (loginSuccessful == -2) {
				responseDTO.setMessage("Contrase??a incorrecta");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Login exitoso");
				String token = getJWTToken(userLoginDTO.getDni(), userLoginDTO.getPassword());
				responseDTO.setToken(token);
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/loginAdmin/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> loginAdmin(@RequestBody UserLoginDTO userLoginDTO) {
		log.info("UserLoginController: method login");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int loginSuccessful = userLoginService.loginSuccessfulAdmin(userLoginDTO.getPassword());
			if (loginSuccessful == -1) {
				responseDTO.setMessage("Contrase??a incorrecta");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Login exitoso");
				String token = getJWTToken("admin", userLoginDTO.getPassword());
				responseDTO.setToken(token);
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
		log.info("UserLoginController: method restorePasswordPsychologist");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean existEmail = userLoginService.existEmailPsychologist(emailDTO.getEmail());
			if (existEmail == false) {
				responseDTO.setMessage("Email no registrado");
				responseDTO.setStatus(0);
			} else {
				String newPassword = PasswordGenerator.getPassword(
						PasswordGenerator.LOWERCASE + PasswordGenerator.UPPERCASE + PasswordGenerator.SPECIAL, 10);
				userLoginService.changePasswordPsychologist(emailDTO.getEmail(), newPassword);
				emailService.sendEmail(emailDTO.getEmail(), "Su nueva contrase??a es: " + newPassword);
				responseDTO.setMessage("Email enviado");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/restorePasswordPatient/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> restorePasswordPatient(@RequestBody EmailDTO emailDTO) {
		log.info("UserLoginController: method restorePasswordPatient");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean existEmail = userLoginService.existEmailPatient(emailDTO.getEmail());
			if (existEmail == false) {
				responseDTO.setMessage("Email no registrado");
				responseDTO.setStatus(0);
			} else {
				String newPassword = PasswordGenerator.getPassword(
						PasswordGenerator.LOWERCASE + PasswordGenerator.UPPERCASE + PasswordGenerator.SPECIAL, 10);
				userLoginService.changePasswordPatient(emailDTO.getEmail(), newPassword);
				emailService.sendEmail(emailDTO.getEmail(), "Su nueva contrase??a es: " + newPassword);
				responseDTO.setMessage("Email enviado");
				responseDTO.setStatus(1);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/createPatient/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createPatient(@RequestBody PatientDTO patientDTO) {
		log.info("UserLoginController: method createPatient");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = patientService.create(patientDTO);
			if (result == -2) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else if (result == -1) {
				responseDTO.setMessage("DNI ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Registro exitoso");
				responseDTO.setStatus(1);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/createPsychologist/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createPsychologist(@RequestBody PsychologistDTO psychologistDTO) {
		log.info("UserLoginController: method createPsychologist");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = psychologistService.create(psychologistDTO, psychologistDTO.getUserLoginDTO());
			if (result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else if (result == -2) {
				responseDTO.setMessage("DNI ya registrado");
				responseDTO.setStatus(0);
			} else if (result == -3) {
				responseDTO.setMessage("CPSP ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Registro exitoso");
				responseDTO.setStatus(1);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	private String getJWTToken(String dni, String password) {
		log.info("UserLoginController: method getJWTToken");
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("tp2-api").setSubject(dni)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	/*
	 * @PostMapping(path = "/convertDefault/", produces = "application/json") public
	 * ResponseEntity<?> convertDefault() {
	 * log.info("UserLoginController: method convertDefault"); ResponseDTO
	 * responseDTO = new ResponseDTO(); try { userLoginService.convertPasswords();
	 * responseDTO.setStatus(1); responseDTO.setMessage("Conversi??n exitosa"); }
	 * catch (Exception e) { responseDTO.setMessage("Error");
	 * responseDTO.setStatus(0); } return ResponseEntity.ok(responseDTO); }
	 */
}
