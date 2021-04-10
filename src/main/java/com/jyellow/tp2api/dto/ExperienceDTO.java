package com.jyellow.tp2api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDTO {
	private List<CourseDTO> courses;
	private List<ConferenceDTO> conferences;
	private List<StudyDTO> studies;
	private List<WorkExperienceDTO> workExperiences;
}
