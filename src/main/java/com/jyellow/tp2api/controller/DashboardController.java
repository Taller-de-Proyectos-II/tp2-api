package com.jyellow.tp2api.controller;

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
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.service.TestService;

@CrossOrigin
@RestController
@RequestMapping(path = "/dashboard")
public class DashboardController {

	@Autowired
	private TestService testService;
	
	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> listDashboard(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<TestDTO> testsDTO = testService.listByPatientDni(patientDni);
			if (testsDTO == null || testsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Pruebas");
				responseDTO.setStatus(0);
			} else {
				int[] resultManifestacion = new int[5];
				int[] resultAnsiedad = new int[5];
				int[] resultDepresion = new int[5];
				for(int i=0; i<5; i++) {
					resultManifestacion[i] = 0;
					resultAnsiedad[i] = 0;
					resultDepresion[i] = 0;
				}
				for(TestDTO testDTO: testsDTO) {
					if(testDTO.getTestType().equals("Depresión")) {
						if(testDTO.getEndDate() == null) {
							resultDepresion[0]++;
						} else {
							switch (testDTO.getDiagnostic()) {
							case "No hay depresión presente":
								resultDepresion[1]++;
								break;
							case "Depresión leve":
								resultDepresion[2]++;
								break;
							case "Depresión severa":
								resultDepresion[3]++;
								break;
							case "Depresión grave":
								resultDepresion[4]++;
								break;
							default:
								break;
							}
						}
					} else if (testDTO.getTestType().equals("Ansiedad")) {
						if(testDTO.getEndDate() == null) {
							resultAnsiedad[0]++;
						} else {
							switch (testDTO.getDiagnostic()) {
							case "No hay ansiedad presente":
								resultAnsiedad[1]++;
								break;
							case "Ansiedad leve":
								resultAnsiedad[2]++;
								break;
							case "Ansiedad severa":
								resultAnsiedad[3]++;
								break;
							case "Ansiedad grave":
								resultAnsiedad[4]++;
								break;
							default:
								break;
							}
						}
					} else {
						if(testDTO.getEndDate() == null) {
							resultManifestacion[0]++;
						} else {
							switch (testDTO.getDiagnostic()) {
							case "No necesita asignación de prueba":
								resultManifestacion[1]++;
								break;
							case "Necesita asignación de prueba":
								resultManifestacion[2]++;
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
				dashboardDTO.setResultManifestacion(resultManifestacion);
				responseDTO.setMessage("Dashboard");
				responseDTO.setStatus(1);
				responseDTO.setDashboardDTO(dashboardDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
