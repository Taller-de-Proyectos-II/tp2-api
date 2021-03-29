package com.jyellow.tp2api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDTO {
	private int status;
	private String message;
	private GuardianDTO guardianDTO;
	private PsychologistDTO psychologistDTO;
	private PatientDTO patientDTO;
	private List<PatientDTO> patientsDTO;
	private ExperienceDTO experienceDTO;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GuardianDTO getGuardianDTO() {
		return guardianDTO;
	}

	public void setGuardianDTO(GuardianDTO guardianDTO) {
		this.guardianDTO = guardianDTO;
	}

	public PsychologistDTO getPsychologistDTO() {
		return psychologistDTO;
	}

	public void setPsychologistDTO(PsychologistDTO psychologistDTO) {
		this.psychologistDTO = psychologistDTO;
	}

	public PatientDTO getPatientDTO() {
		return patientDTO;
	}

	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}

	public List<PatientDTO> getPatientsDTO() {
		return patientsDTO;
	}

	public void setPatientsDTO(List<PatientDTO> patientsDTO) {
		this.patientsDTO = patientsDTO;
	}

	public ExperienceDTO getExperienceDTO() {
		return experienceDTO;
	}

	public void setExperienceDTO(ExperienceDTO experienceDTO) {
		this.experienceDTO = experienceDTO;
	}
}
