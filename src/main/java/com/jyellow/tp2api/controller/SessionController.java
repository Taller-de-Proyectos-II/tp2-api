package com.jyellow.tp2api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.SessionCreateDTO;
import com.jyellow.tp2api.dto.SessionDTO;
import com.jyellow.tp2api.dto.SessionUpdateDTO;
import com.jyellow.tp2api.service.EmailService;
import com.jyellow.tp2api.service.SessionService;

@CrossOrigin
@RestController
@RequestMapping(path = "/session")
public class SessionController {

	@Autowired
	SessionService sessionService;
	@Autowired
	EmailService emailService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody SessionCreateDTO sessionCreateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String message = sessionService.create(sessionCreateDTO);
			if (message.contains("#") == true) {
				message = message.substring(1);
				SessionDTO sessionDTO = sessionService.listById(Integer.parseInt(message));
				responseDTO.setMessage("Registro exitoso");
				responseDTO.setStatus(1);
				responseDTO.setSessionDTO(sessionDTO);
				emailService.sendEmailSession(sessionDTO.getPatient().getEmail(),
						"Se ha registrado una sessión con ID: " + sessionDTO.getIdSession() + "\r\n" + "Fecha: "
								+ sessionDTO.getDate() + "\r\n" + "Hora: " + sessionDTO.getSchedule().getHour() + ":00"
								+ "\r\n" + "Paciente: " + sessionDTO.getPatient().getLastNames() + ", "
								+ sessionDTO.getPatient().getNames() + "\r\n" + "Psicólogo: "
								+ sessionDTO.getPsychologist().getLastNames() + ", "
								+ sessionDTO.getPsychologist().getNames(),
						"Registro de sesión");
				emailService.sendEmailSession(sessionDTO.getPsychologist().getEmail(),
						"Se ha registrado una sessión con ID: " + sessionDTO.getIdSession() + "\r\n" + "Fecha: "
								+ sessionDTO.getDate() + "\r\n" + "Hora: " + sessionDTO.getSchedule().getHour() + ":00"
								+ "\r\n" + "Paciente: " + sessionDTO.getPatient().getLastNames() + ", "
								+ sessionDTO.getPatient().getNames() + "\r\n" + "Psicólogo: "
								+ sessionDTO.getPsychologist().getLastNames() + ", "
								+ sessionDTO.getPsychologist().getNames(),
						"Registro de sesión");
			} else {
				responseDTO.setMessage(message);
				responseDTO.setStatus(0);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/updateAcepted/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateAcepted(@RequestBody SessionUpdateDTO sessionUPdateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SessionDTO sessionDTO = sessionService.updateAcepted(sessionUPdateDTO);
			emailService.sendEmailSession(sessionDTO.getPatient().getEmail(),
					"Se ha actualizado el enlace a la sessión con ID: " + sessionDTO.getIdSession() + "\r\n"
							+ "Enlace: " + sessionDTO.getMeetingLink() + "\r\n" + "Fecha: " + sessionDTO.getDate()
							+ "\r\n" + "Hora: " + sessionDTO.getSchedule().getHour() + ":00" + "\r\n" + "Paciente: "
							+ sessionDTO.getPatient().getLastNames() + ", " + sessionDTO.getPatient().getNames()
							+ "\r\n" + "Psicólogo: " + sessionDTO.getPsychologist().getLastNames() + ", "
							+ sessionDTO.getPsychologist().getNames(),
					"Actualización de sesión");
			emailService.sendEmailSession(sessionDTO.getPsychologist().getEmail(),
					"Se ha actualizado el enlace a la sessión con ID: " + sessionDTO.getIdSession() + "\r\n"
							+ "Enlace: " + sessionDTO.getMeetingLink() + "\r\n" + "Fecha: " + sessionDTO.getDate()
							+ "\r\n" + "Hora: " + sessionDTO.getSchedule().getHour() + ":00" + "\r\n" + "Paciente: "
							+ sessionDTO.getPatient().getLastNames() + ", " + sessionDTO.getPatient().getNames()
							+ "\r\n" + "Psicólogo: " + sessionDTO.getPsychologist().getLastNames() + ", "
							+ sessionDTO.getPsychologist().getNames(),
					"Actualización de sesión");
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setSessionDTO(sessionDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/updateFinished/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateFinished(@RequestBody SessionUpdateDTO sessionUpdateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SessionDTO sessionDTO = sessionService.updateFinished(sessionUpdateDTO.getIdSession());
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setSessionDTO(sessionDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAllByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listAllByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService.listByPatientUserLoginDni(patientDni);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listPendingByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listPendingByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService.listByPatientUserLoginDniAndAceptedAndFinished(patientDni,
					false, false);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAceptedByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listAceptedByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService.listByPatientUserLoginDniAndAceptedAndFinished(patientDni,
					true, false);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listFinishedByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listFinishedByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService.listByPatientUserLoginDniAndAceptedAndFinished(patientDni,
					true, true);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAllByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listAllByPsychologistDni(@RequestParam String psychologistDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService.listByPsychologistUserLoginDni(psychologistDni);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listPendingByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listPendingByPsychologistDni(@RequestParam String psychologistDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService
					.listByPsychologistUserLoginDniAndAceptedAndFinished(psychologistDni, false, false);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAceptedByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listAceptedByPsychologistDni(@RequestParam String psychologistDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService
					.listByPsychologistUserLoginDniAndAceptedAndFinished(psychologistDni, true, false);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listFinishedByPsychologistDni/", produces = "application/json")
	public ResponseEntity<?> listFinishedByPsychologistDni(@RequestParam String psychologistDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<SessionDTO> sessionsDTO = sessionService
					.listByPsychologistUserLoginDniAndAceptedAndFinished(psychologistDni, true, true);
			if (sessionsDTO == null || sessionsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Sesiones");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Sessiones");
				responseDTO.setStatus(1);
				responseDTO.setSessionsDTO(sessionsDTO);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
