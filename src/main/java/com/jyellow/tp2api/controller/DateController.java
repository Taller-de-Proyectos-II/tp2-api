package com.jyellow.tp2api.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyellow.tp2api.dto.ResponseDTO;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequestMapping(path = "/dates")
@Log4j2
public class DateController {
	@GetMapping(path = "/listWeekDays/", produces = "application/json")
	public ResponseEntity<?> listWeekDays(@RequestParam String date) {
		log.info("DateController: method listWeekDays");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Calendar now = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
			Date dateAux = format.parse(date);
			now.setTime(dateAux);
			now.add(Calendar.DAY_OF_MONTH, -1);

			String[] days = new String[7];
			int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1;
			now.add(Calendar.DAY_OF_MONTH, delta);
			for (int i = 0; i < 7; i++) {
				days[i] = format.format(now.getTime());
				now.add(Calendar.DAY_OF_MONTH, 1);
			}
			responseDTO.setMessage("Días de la semana");
			responseDTO.setStatus(1);
			responseDTO.setDays(days);

		} catch (Exception e) {
			responseDTO.setMessage("Error");
			responseDTO.setStatus(0);
		}
		return ResponseEntity.ok(responseDTO);
	}
}
