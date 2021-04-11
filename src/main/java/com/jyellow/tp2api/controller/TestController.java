package com.jyellow.tp2api.controller;

import java.util.List;

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

import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.dto.TestCreateDTO;
import com.jyellow.tp2api.dto.TestDTO;
import com.jyellow.tp2api.dto.TestUpdateDTO;
import com.jyellow.tp2api.service.TestService;

@CrossOrigin
@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping(path = "/listByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<TestDTO> testsDTO = testService.listByPatientDni(patientDni);
			if (testsDTO == null || testsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Pruebas");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Pruebas");
				responseDTO.setStatus(1);
				responseDTO.setTestsDTO(testsDTO);
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
			List<TestDTO> testsDTO = testService.listByPatientDniAndFinished(patientDni, true);
			if (testsDTO == null || testsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Pruebas");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Pruebas");
				responseDTO.setStatus(1);
				responseDTO.setTestsDTO(testsDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping(path = "/listUnfinishedByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listUnfinishedByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<TestDTO> testsDTO = testService.listByPatientDniAndFinished(patientDni, false);
			if (testsDTO == null || testsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Pruebas");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Pruebas");
				responseDTO.setStatus(1);
				responseDTO.setTestsDTO(testsDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody TestCreateDTO testCreateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			TestDTO testDTO = testService.create(testCreateDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);
			responseDTO.setTestDTO(testDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody TestUpdateDTO testUpdateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			TestDTO testDTO = testService.update(testUpdateDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setTestDTO(testDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam int idTest) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			TestDTO testDTO = testService.cancel(idTest);
			responseDTO.setMessage("Eliminación exitosa");
			responseDTO.setStatus(1);
			responseDTO.setTestDTO(testDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
