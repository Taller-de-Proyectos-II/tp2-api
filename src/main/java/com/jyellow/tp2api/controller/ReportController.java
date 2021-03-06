package com.jyellow.tp2api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.jyellow.tp2api.util.ReportPDFExporter;
import com.lowagie.text.DocumentException;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/report")
@Log4j2
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportPDFExporter reportExporterService;

	@GetMapping(path = "/listByPatientDni/", produces = "application/json")
	public ResponseEntity<?> listByPatientDni(@RequestParam String patientDni) {
		log.info("ReportController: method listByPatientDni");
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

	@GetMapping(path = "/listByPatientDniAndType/", produces = "application/json")
	public ResponseEntity<?> listByPatientDniAndType(@RequestParam String patientDni, @RequestParam String type) {
		log.info("ReportController: method listByPatientDniAndType");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<ReportDTO> reportsDTO = reportService.listByPatientDniAndType(patientDni, type);
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
		log.info("ReportController: method create");
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
		log.info("ReportController: method update");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ReportDTO reportDTO = reportService.update(reportUpdateDTO);
			responseDTO.setMessage("Actualizaci??n exitosa");
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
		log.info("ReportController: method delete");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			ReportDTO reportDTO = reportService.delete(idReport);
			responseDTO.setMessage("Eliminaci??n exitosa");
			responseDTO.setStatus(1);
			responseDTO.setReportDTO(reportDTO);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping("/export/")
	public void export(@RequestParam int idReport, HttpServletResponse response)
			throws DocumentException, IOException {
		log.info("ReportController: method export");
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=report.pdf";
		response.setHeader(headerKey, headerValue);

		reportExporterService.export(response, idReport);

	}
}
