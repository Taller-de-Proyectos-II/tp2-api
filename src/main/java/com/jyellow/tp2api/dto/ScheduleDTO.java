package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
	private int idSchedule;
	private int hour;
	private int day;
	private String patientDni;
}
