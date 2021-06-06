package com.jyellow.tp2api.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.AlertDTO;
import com.jyellow.tp2api.dto.DashboardDTO;
import com.jyellow.tp2api.dto.OptionDashboardDTO;
import com.jyellow.tp2api.dto.QuestionDTO;
import com.jyellow.tp2api.dto.QuestionTypeDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.SymptomDTO;
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.dto.TestDashboardDTO;
import com.jyellow.tp2api.service.AlertService;
import com.jyellow.tp2api.service.QuestionTypeService;
import com.jyellow.tp2api.service.SymptomService;
import com.jyellow.tp2api.service.TestService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/dashboard")
@Log4j2
public class DashboardController {

	@Autowired
	private TestService testService;

	@Autowired
	private QuestionTypeService questionTypeService;

	@Autowired
	private SymptomService symptomService;

	@Autowired
	private AlertService alertService;

	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> listDashboard(@RequestParam String patientDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listDashboard");
		ResponseDTO responseDTO = new ResponseDTO();

		List<TestDashboardDTO> testsDTO = testService.listByPatientDniAndDates(patientDni, startDate, endDate);
		if (testsDTO == null || testsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Pruebas");
			responseDTO.setStatus(0);
		} else {
			int[] resultAnsiedad = new int[4];
			int[] resultDepresion = new int[4];
			List<TestDashboardDTO> testDashboardAnsiedad = new ArrayList<TestDashboardDTO>();
			List<TestDashboardDTO> testDashboardDepresion = new ArrayList<TestDashboardDTO>();

			for (int i = 0; i < 4; i++) {
				resultAnsiedad[i] = 0;
				resultDepresion[i] = 0;
			}

			for (TestDashboardDTO testDTO : testsDTO) {
				if (testDTO.getTestType().equals("Depresión")) {
					if (testDTO.getEndDate() != null) {
						testDashboardDepresion.add(testDTO);
						switch (testDTO.getDiagnostic()) {
						case "No hay depresión presente":
							resultDepresion[0]++;
							break;
						case "Depresión leve":
							resultDepresion[1]++;
							break;
						case "Depresión severa":
							resultDepresion[2]++;
							break;
						case "Depresión grave":
							resultDepresion[3]++;
							break;
						default:
							break;
						}
					}
				} else if (testDTO.getTestType().equals("Ansiedad")) {
					if (testDTO.getEndDate() != null) {
						testDashboardAnsiedad.add(testDTO);
						switch (testDTO.getDiagnostic()) {
						case "No hay ansiedad presente":
							resultAnsiedad[0]++;
							break;
						case "Ansiedad leve":
							resultAnsiedad[1]++;
							break;
						case "Ansiedad severa":
							resultAnsiedad[2]++;
							break;
						case "Ansiedad grave":
							resultAnsiedad[3]++;
							break;
						default:
							break;
						}
					}
				}
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			dashboardDTO.setResultAnsiedad(resultAnsiedad);
			dashboardDTO.setResultDepresion(resultDepresion);
			// dashboardDTO.setResultManifestacion(resultManifestacion);
			dashboardDTO.setTestDashboardDepresion(testDashboardDepresion);
			dashboardDTO.setTestDashboardAnsiedad(testDashboardAnsiedad);
			// dashboardDTO.setTestDashboardManifestacion(testDashboardManifestacion);
			responseDTO.setMessage("Dashboard Pruebas");
			responseDTO.setStatus(1);
			responseDTO.setDashboardDTO(dashboardDTO);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listManifestations/", produces = "application/json")
	public ResponseEntity<?> listManifestations(@RequestParam String patientDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listManifestations");
		ResponseDTO responseDTO = new ResponseDTO();
		OptionDashboardDTO optionDashboardDTO = new OptionDashboardDTO();
		int[] result = new int[4];
		String[] resultString = new String[4];

		for (int i = 0; i < 4; i++) {
			result[i] = 0;
			resultString[i] = "";
		}

		QuestionTypeDTO questionTypeDTO = questionTypeService.listByName("Manifestaciones");
		List<QuestionDTO> questionsDTO = questionTypeDTO.getQuestionsDTO();
		resultString[0] = questionsDTO.get(0).getDescription();
		resultString[1] = questionsDTO.get(1).getDescription();
		resultString[2] = questionsDTO.get(2).getDescription();
		resultString[3] = questionsDTO.get(3).getDescription();

		List<TestDTO> testsDTO = testService.listByPatientDniAndDatesManifestation(patientDni, startDate, endDate);

		if (testsDTO == null || testsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Maniefstaciones");
			responseDTO.setStatus(0);
		} else {
			for (TestDTO testDTO : testsDTO) {
				if (testDTO.getTestType().equals("Manifestaciones")) {
					result[0] += testDTO.getAnswersDTO().get(0).getScore();
					result[1] += testDTO.getAnswersDTO().get(1).getScore();
					result[2] += testDTO.getAnswersDTO().get(2).getScore();
					result[3] += testDTO.getAnswersDTO().get(3).getScore();
				}
			}

			optionDashboardDTO.setResult(result);
			optionDashboardDTO.setResultString(resultString);
			responseDTO.setOptionDashboardDTO(optionDashboardDTO);
			responseDTO.setMessage("Dashboard Manifestaciones");
			responseDTO.setStatus(1);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAlerts/", produces = "application/json")
	public ResponseEntity<?> listAlerts(@RequestParam String patientDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listAlerts");
		ResponseDTO responseDTO = new ResponseDTO();
		OptionDashboardDTO optionDashboardDTO = new OptionDashboardDTO();
		int[] result = new int[6];
		String[] resultString = new String[6];

		for (int i = 0; i < 6; i++) {
			result[i] = 0;
			resultString[i] = "";
		}

		List<SymptomDTO> symptomsDTO = symptomService.listAll();
		resultString[0] = symptomsDTO.get(0).getDescription();
		resultString[1] = symptomsDTO.get(1).getDescription();
		resultString[2] = symptomsDTO.get(2).getDescription();
		resultString[3] = symptomsDTO.get(3).getDescription();
		resultString[4] = symptomsDTO.get(4).getDescription();
		resultString[5] = symptomsDTO.get(5).getDescription();

		List<AlertDTO> alertsDTO = alertService.listByPatientDniAndDates(patientDni, startDate, endDate);
		if (alertsDTO == null || alertsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Alertas");
			responseDTO.setStatus(0);
		} else {

			for (AlertDTO alertDTO : alertsDTO) {
				result[0] += alertDTO.getAlertAnswersDTO().get(0).getScore();
				result[1] += alertDTO.getAlertAnswersDTO().get(1).getScore();
				result[2] += alertDTO.getAlertAnswersDTO().get(2).getScore();
				result[3] += alertDTO.getAlertAnswersDTO().get(3).getScore();
				result[4] += alertDTO.getAlertAnswersDTO().get(4).getScore();
				result[5] += alertDTO.getAlertAnswersDTO().get(5).getScore();
			}

			optionDashboardDTO.setResult(result);
			optionDashboardDTO.setResultString(resultString);
			responseDTO.setOptionDashboardDTO(optionDashboardDTO);
			responseDTO.setStatus(1);
			responseDTO.setMessage("Dashboard Alertas");
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAllDonut/", produces = "application/json")
	public ResponseEntity<?> listAllDonut(@RequestParam String psychologistDni,
			@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listAllDonut");
		ResponseDTO responseDTO = new ResponseDTO();

		int[] resultAlert = new int[2];
		int[] resultAnsiedad = new int[4];
		int[] resultDepresion = new int[4];
		int[] resultManifestacion = new int[2];

		// Lista de Pruebas
		List<TestDashboardDTO> testsDTO = testService.listByPsychologistDniAndDates(psychologistDni, startDate,
				endDate);
		if (testsDTO == null || testsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Pruebas");
			responseDTO.setStatus(0);
		} else {

			for (int i = 0; i < 4; i++) {
				resultAnsiedad[i] = 0;
				resultDepresion[i] = 0;
			}
			for (int i = 0; i < 2; i++) {
				resultManifestacion[i] = 0;
			}

			for (TestDashboardDTO testDTO : testsDTO) {
				if (testDTO.getEndDate() != null) {
					if (testDTO.getTestType().equals("Depresión")) {
						switch (testDTO.getDiagnostic()) {
						case "No hay depresión presente":
							resultDepresion[0]++;
							break;
						case "Depresión leve":
							resultDepresion[1]++;
							break;
						case "Depresión severa":
							resultDepresion[2]++;
							break;
						case "Depresión grave":
							resultDepresion[3]++;
							break;
						default:
							break;

						}
					} else if (testDTO.getTestType().equals("Ansiedad")) {
						switch (testDTO.getDiagnostic()) {
						case "No hay ansiedad presente":
							resultAnsiedad[0]++;
							break;
						case "Ansiedad leve":
							resultAnsiedad[1]++;
							break;
						case "Ansiedad severa":
							resultAnsiedad[2]++;
							break;
						case "Ansiedad grave":
							resultAnsiedad[3]++;
							break;
						default:
							break;

						}
					} else {
						switch (testDTO.getDiagnostic()) {
						case "No necesita asignación de prueba":
							resultManifestacion[0]++;
							break;
						case "Necesita asignación de prueba":
							resultManifestacion[1]++;
							break;
						default:
							break;
						}
					}
				}
			}
		}

		// Lista de Alertas
		List<AlertDTO> alertsDTO = alertService.listByPsychologistDniAndDates(psychologistDni, startDate, endDate);

		if (alertsDTO == null || alertsDTO.size() == 0) {
			if (responseDTO.getMessage() == null || responseDTO.getMessage().isEmpty()) {
				responseDTO.setMessage("No se encontraron Alertas");
			} else {
				responseDTO.setMessage("ni Alertas");
			}
			responseDTO.setStatus(0);
		} else {
			resultAlert[0] = 0;
			resultAlert[1] = 0;

			for (AlertDTO alertDTO : alertsDTO) {
				if (alertDTO.isImportant()) {
					resultAlert[0]++;
				} else {
					resultAlert[1]++;
				}
			}
		}

		DashboardDTO dashboardDTO = new DashboardDTO();
		dashboardDTO.setResultAnsiedad(resultAnsiedad);
		dashboardDTO.setResultDepresion(resultDepresion);
		dashboardDTO.setResultManifestacion(resultManifestacion);
		dashboardDTO.setResultAlert(resultAlert);
		responseDTO.setMessage("Dashboard General");
		responseDTO.setStatus(1);
		responseDTO.setDashboardDTO(dashboardDTO);
		return ResponseEntity.ok(responseDTO);
	}
	
	@GetMapping(path = "/listDashboardAllPatients/", produces = "application/json")
	public ResponseEntity<?> listDashboardAllPatients(@RequestParam String psychologistDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listDashboardAllPatients");
		ResponseDTO responseDTO = new ResponseDTO();

		List<TestDashboardDTO> testsDTO = testService.listByPsychologistDniAndDates(psychologistDni, startDate, endDate);
		if (testsDTO == null || testsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Pruebas");
			responseDTO.setStatus(0);
		} else {
			int[] resultAnsiedad = new int[4];
			int[] resultDepresion = new int[4];
			List<TestDashboardDTO> testDashboardAnsiedad = new ArrayList<TestDashboardDTO>();
			List<TestDashboardDTO> testDashboardDepresion = new ArrayList<TestDashboardDTO>();

			for (int i = 0; i < 4; i++) {
				resultAnsiedad[i] = 0;
				resultDepresion[i] = 0;
			}

			for (TestDashboardDTO testDTO : testsDTO) {
				if (testDTO.getTestType().equals("Depresión")) {
					if (testDTO.getEndDate() != null) {
						testDashboardDepresion.add(testDTO);
						switch (testDTO.getDiagnostic()) {
						case "No hay depresión presente":
							resultDepresion[0]++;
							break;
						case "Depresión leve":
							resultDepresion[1]++;
							break;
						case "Depresión severa":
							resultDepresion[2]++;
							break;
						case "Depresión grave":
							resultDepresion[3]++;
							break;
						default:
							break;
						}
					}
				} else if (testDTO.getTestType().equals("Ansiedad")) {
					if (testDTO.getEndDate() != null) {
						testDashboardAnsiedad.add(testDTO);
						switch (testDTO.getDiagnostic()) {
						case "No hay ansiedad presente":
							resultAnsiedad[0]++;
							break;
						case "Ansiedad leve":
							resultAnsiedad[1]++;
							break;
						case "Ansiedad severa":
							resultAnsiedad[2]++;
							break;
						case "Ansiedad grave":
							resultAnsiedad[3]++;
							break;
						default:
							break;
						}
					}
				}
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			dashboardDTO.setResultAnsiedad(resultAnsiedad);
			dashboardDTO.setResultDepresion(resultDepresion);
			dashboardDTO.setTestDashboardDepresion(testDashboardDepresion);
			dashboardDTO.setTestDashboardAnsiedad(testDashboardAnsiedad);
			responseDTO.setMessage("Dashboard Pruebas");
			responseDTO.setStatus(1);
			responseDTO.setDashboardDTO(dashboardDTO);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listManifestationsAllPatients/", produces = "application/json")
	public ResponseEntity<?> listManifestationsAllPatients(@RequestParam String psychologistDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listManifestationsAllPatients");
		ResponseDTO responseDTO = new ResponseDTO();
		OptionDashboardDTO optionDashboardDTO = new OptionDashboardDTO();
		int[] result = new int[4];
		String[] resultString = new String[4];

		for (int i = 0; i < 4; i++) {
			result[i] = 0;
			resultString[i] = "";
		}

		QuestionTypeDTO questionTypeDTO = questionTypeService.listByName("Manifestaciones");
		List<QuestionDTO> questionsDTO = questionTypeDTO.getQuestionsDTO();
		resultString[0] = questionsDTO.get(0).getDescription();
		resultString[1] = questionsDTO.get(1).getDescription();
		resultString[2] = questionsDTO.get(2).getDescription();
		resultString[3] = questionsDTO.get(3).getDescription();

		List<TestDTO> testsDTO = testService.listByPsychologistDniAndDatesManifestation(psychologistDni, startDate, endDate);

		if (testsDTO == null || testsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Maniefstaciones");
			responseDTO.setStatus(0);
		} else {
			for (TestDTO testDTO : testsDTO) {
				if (testDTO.getTestType().equals("Manifestaciones")) {
					result[0] += testDTO.getAnswersDTO().get(0).getScore();
					result[1] += testDTO.getAnswersDTO().get(1).getScore();
					result[2] += testDTO.getAnswersDTO().get(2).getScore();
					result[3] += testDTO.getAnswersDTO().get(3).getScore();
				}
			}

			optionDashboardDTO.setResult(result);
			optionDashboardDTO.setResultString(resultString);
			responseDTO.setOptionDashboardDTO(optionDashboardDTO);
			responseDTO.setMessage("Dashboard Manifestaciones");
			responseDTO.setStatus(1);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listAlertsAllPatients/", produces = "application/json")
	public ResponseEntity<?> listAlertsAllPatients(@RequestParam String psychologistDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		log.info("DashboardController: method listAlertsAllPatients");
		ResponseDTO responseDTO = new ResponseDTO();
		OptionDashboardDTO optionDashboardDTO = new OptionDashboardDTO();
		int[] result = new int[6];
		String[] resultString = new String[6];

		for (int i = 0; i < 6; i++) {
			result[i] = 0;
			resultString[i] = "";
		}

		List<SymptomDTO> symptomsDTO = symptomService.listAll();
		resultString[0] = symptomsDTO.get(0).getDescription();
		resultString[1] = symptomsDTO.get(1).getDescription();
		resultString[2] = symptomsDTO.get(2).getDescription();
		resultString[3] = symptomsDTO.get(3).getDescription();
		resultString[4] = symptomsDTO.get(4).getDescription();
		resultString[5] = symptomsDTO.get(5).getDescription();

		List<AlertDTO> alertsDTO = alertService.listByPsychologistDniAndDates(psychologistDni, startDate, endDate);
		if (alertsDTO == null || alertsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Alertas");
			responseDTO.setStatus(0);
		} else {

			for (AlertDTO alertDTO : alertsDTO) {
				result[0] += alertDTO.getAlertAnswersDTO().get(0).getScore();
				result[1] += alertDTO.getAlertAnswersDTO().get(1).getScore();
				result[2] += alertDTO.getAlertAnswersDTO().get(2).getScore();
				result[3] += alertDTO.getAlertAnswersDTO().get(3).getScore();
				result[4] += alertDTO.getAlertAnswersDTO().get(4).getScore();
				result[5] += alertDTO.getAlertAnswersDTO().get(5).getScore();
			}

			optionDashboardDTO.setResult(result);
			optionDashboardDTO.setResultString(resultString);
			responseDTO.setOptionDashboardDTO(optionDashboardDTO);
			responseDTO.setStatus(1);
			responseDTO.setMessage("Dashboard Alertas");
		}
		return ResponseEntity.ok(responseDTO);
	}

}
