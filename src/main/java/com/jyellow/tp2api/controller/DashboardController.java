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

import com.jyellow.tp2api.dto.DashboardDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.TestDashboardDTO;
import com.jyellow.tp2api.service.TestService;

@CrossOrigin
@RestController
@RequestMapping(path = "/dashboard")
public class DashboardController {

	@Autowired
	private TestService testService;

	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> listDashboard(@RequestParam String patientDni, @RequestParam String startDate,
			@RequestParam String endDate) throws ParseException {
		ResponseDTO responseDTO = new ResponseDTO();

		List<TestDashboardDTO> testsDTO = testService.listByPatientDniAndDates(patientDni, startDate, endDate);
		if (testsDTO == null || testsDTO.size() == 0) {
			responseDTO.setMessage("No se encontraron Pruebas");
			responseDTO.setStatus(0);
		} else {
			// int[] resultManifestacion = new int[4];
			int[] resultAnsiedad = new int[4];
			int[] resultDepresion = new int[4];
			// List<TestDashboardDTO> testDashboardManifestacion = new
			// ArrayList<TestDashboardDTO>();
			List<TestDashboardDTO> testDashboardAnsiedad = new ArrayList<TestDashboardDTO>();
			List<TestDashboardDTO> testDashboardDepresion = new ArrayList<TestDashboardDTO>();

			for (int i = 0; i < 4; i++) {
				// resultManifestacion[i] = 0;
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
				} /*
					 * else { if (testDTO.getEndDate() != null) {
					 * testDashboardManifestacion.add(testDTO); switch (testDTO.getDiagnostic()) {
					 * case "No necesita asignación de prueba": resultManifestacion[0]++; break;
					 * case "Necesita asignación de prueba": resultManifestacion[1]++; break;
					 * default: break; } } }
					 */
			}

			DashboardDTO dashboardDTO = new DashboardDTO();
			dashboardDTO.setResultAnsiedad(resultAnsiedad);
			dashboardDTO.setResultDepresion(resultDepresion);
			// dashboardDTO.setResultManifestacion(resultManifestacion);
			dashboardDTO.setTestDashboardDepresion(testDashboardDepresion);
			dashboardDTO.setTestDashboardAnsiedad(testDashboardAnsiedad);
			// dashboardDTO.setTestDashboardManifestacion(testDashboardManifestacion);
			responseDTO.setMessage("Dashboard");
			responseDTO.setStatus(1);
			responseDTO.setDashboardDTO(dashboardDTO);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
