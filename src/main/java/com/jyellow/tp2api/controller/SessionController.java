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
import com.jyellow.tp2api.service.SessionService;

@CrossOrigin
@RestController
@RequestMapping(path = "/session")
public class SessionController {

	@Autowired
	SessionService sessionService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody SessionCreateDTO sessionCreateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			String message = sessionService.create(sessionCreateDTO);
			if (message == "") {
				responseDTO.setMessage("Registro exitoso");
				responseDTO.setStatus(1);
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
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setSessionDTO(sessionDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/updateFinished/", produces = "application/json")
	public ResponseEntity<?> updateFinished(@RequestParam int idSession) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			SessionDTO sessionDTO = sessionService.updateFinished(idSession);
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
