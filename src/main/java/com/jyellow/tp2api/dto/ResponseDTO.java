package com.jyellow.tp2api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDTO {
	private int status;
	private String message;
	private GuardianDTO guardianDTO;
	private PsychologistDTO psychologistDTO;
	private PatientDTO patientDTO;
	private List<PatientDTO> patientsDTO;
	private ExperienceDTO experienceDTO;
	private List<GuardianDTO> guardiansDTO;
	private List<PsychologistDTO> psychologistsDTO;
	private ManifestationsDTO manifestationsDTO;
	private TestDTO testDTO;
	private List<TestDTO> testsDTO;
	private List<ReportDTO> reportsDTO;
	private ReportDTO reportDTO;
	private List<ScheduleDTO> schedulesDTO;
	private List<SymptomDTO> symptomsDTO;
	private SessionDTO sessionDTO;
	private List<SessionDTO> sessionsDTO;
	private List<QuestionDTO> questionsDTO;
	private List<QuestionTypeDTO> questionTypesDTO;
	private QuestionTypeDTO questionTypeDTO;
	private List<AlertDTO> alertsDTO;
	private AlertDTO alertDTO;
}
