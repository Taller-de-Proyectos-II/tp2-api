package com.jyellow.tp2api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ConferenceDTO;
import com.jyellow.tp2api.dto.CourseDTO;
import com.jyellow.tp2api.dto.PsychologistDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.StudyDTO;
import com.jyellow.tp2api.dto.WorkExperienceDTO;
import com.jyellow.tp2api.model.WorkExperience;
import com.jyellow.tp2api.service.ConferenceService;
import com.jyellow.tp2api.service.CourseService;
import com.jyellow.tp2api.service.PsychologistService;
import com.jyellow.tp2api.service.StudyService;
import com.jyellow.tp2api.service.WorkExperienceService;

@CrossOrigin
@RestController
@RequestMapping(path = "/psychologist")
public class PsychologistController {

	@Autowired
	PsychologistService psychologistService;

	@Autowired
	ConferenceService conferenceService;

	@Autowired
	CourseService courseService;

	@Autowired
	StudyService studyService;

	@Autowired
	WorkExperienceService workExperienceService;

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody PsychologistDTO psychologistDTO) {
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

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody PsychologistDTO psychologistDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			int result = psychologistService.update(psychologistDTO, psychologistDTO.getUserLoginDTO());
			if (result == -1) {
				responseDTO.setMessage("Email ya registrado");
				responseDTO.setStatus(0);
			} else if (result == -2) {
				responseDTO.setMessage("CPSP ya registrado");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Actualización exitosa");
				responseDTO.setStatus(1);
			}

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> listByDni(@RequestParam String dni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			PsychologistDTO psychologistDTO = psychologistService.listByDni(dni);
			responseDTO.setMessage("Psicólogo");
			responseDTO.setStatus(1);
			responseDTO.setPsychologistDTO(psychologistDTO);
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PostMapping(path = "/conferences/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createConference(@RequestBody ConferenceDTO conferenceDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			conferenceService.create(conferenceDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/conferences/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateConference(@RequestBody ConferenceDTO conferenceDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			conferenceService.update(conferenceDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping(path = "/conferences/", produces = "application/json")
	public ResponseEntity<?> deleteConference(@RequestParam int idConference) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			conferenceService.delete(idConference);
			responseDTO.setMessage("Eliminación exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/courses/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			courseService.create(courseDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/courses/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateCourse(@RequestBody CourseDTO courseDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			courseService.update(courseDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping(path = "/courses/", produces = "application/json")
	public ResponseEntity<?> deleteCouse(@RequestParam int idCourse) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			courseService.delete(idCourse);
			responseDTO.setMessage("Eliminación exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/studies/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createStudy(@RequestBody StudyDTO studyDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			studyService.create(studyDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/studies/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateStudy(@RequestBody StudyDTO studyDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			studyService.update(studyDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping(path = "/studies/", produces = "application/json")
	public ResponseEntity<?> deleteStudy(@RequestParam int idStudy) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			studyService.delete(idStudy);
			responseDTO.setMessage("Eliminación exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/workExperience/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			workExperienceService.create(workExperienceDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(path = "/workExperience/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateWorkExperience(@RequestBody WorkExperienceDTO workExperienceDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			workExperienceService.update(workExperienceDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping(path = "/workExperience/", produces = "application/json")
	public ResponseEntity<?> deleteWorkExperience(@RequestParam int idWorkExperience) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			workExperienceService.delete(idWorkExperience);
			responseDTO.setMessage("Eliminación exitosa");
			responseDTO.setStatus(1);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
