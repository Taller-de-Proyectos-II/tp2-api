package com.jyellow.tp2api.dto;

import java.util.List;

public class ExperienceDTO {
	private List<CourseDTO> courses;
	private List<ConferenceDTO> conferences;
	private List<StudyDTO> studies;
	private List<WorkExperienceDTO> workExperiences;
	
	public ExperienceDTO() {
		super();
	}

	public ExperienceDTO(List<CourseDTO> courses, List<ConferenceDTO> conferences, List<StudyDTO> studies,
			List<WorkExperienceDTO> workExperiences) {
		super();
		this.courses = courses;
		this.conferences = conferences;
		this.studies = studies;
		this.workExperiences = workExperiences;
	}

	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}

	public List<ConferenceDTO> getConferences() {
		return conferences;
	}

	public void setConferences(List<ConferenceDTO> conferences) {
		this.conferences = conferences;
	}

	public List<StudyDTO> getStudies() {
		return studies;
	}

	public void setStudies(List<StudyDTO> studies) {
		this.studies = studies;
	}

	public List<WorkExperienceDTO> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<WorkExperienceDTO> workExperiences) {
		this.workExperiences = workExperiences;
	}
}
