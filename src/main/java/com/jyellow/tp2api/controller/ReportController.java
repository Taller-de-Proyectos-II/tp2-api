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

import com.jyellow.tp2api.dto.ReportCreateDTO;
import com.jyellow.tp2api.dto.ReportDTO;
import com.jyellow.tp2api.dto.ReportUpdateDTO;
import com.jyellow.tp2api.dto.ResponseDTO;
import com.jyellow.tp2api.service.ReportService;

@CrossOrigin
@RestController
@RequestMapping(path = "/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping(path = "/listByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listByPatientDni(@RequestParam String patientDni) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ReportDTO> reportsDTO = reportService.listByPatientDni(patientDni);
			if (reportsDTO == null || reportsDTO.size() == 0) {
				responseDTO.setMessage("No se encontraron Informes");
				responseDTO.setStatus(0);
			} else {
				responseDTO.setMessage("Informes");
				responseDTO.setStatus(1);
				responseDTO.setReportsDTO(reportsDTO);
			}
		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody ReportCreateDTO reportCreateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ReportDTO reportDTO = reportService.create(reportCreateDTO);
			responseDTO.setMessage("Registro exitoso");
			responseDTO.setStatus(1);
			responseDTO.setReportDTO(reportDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody ReportUpdateDTO reportUpdateDTO) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ReportDTO reportDTO = reportService.update(reportUpdateDTO);
			responseDTO.setMessage("Actualización exitosa");
			responseDTO.setStatus(1);
			responseDTO.setReportDTO(reportDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
	
	@DeleteMapping(path = "/", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam int idReport) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ReportDTO reportDTO = reportService.delete(idReport);
			responseDTO.setMessage("Eliminación exitosa");
			responseDTO.setStatus(1);
			responseDTO.setReportDTO(reportDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
