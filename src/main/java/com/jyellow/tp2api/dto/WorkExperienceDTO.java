package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceDTO {
	private int idWorkExperience;
	private String place;
	private String occupation;
	private String description;
	private String workingDayType;
	private String startDate;
	private String endDate;
	private boolean isCurrent;
	private String psychologistDni;

}
