package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.WorkExperienceDTO;

public interface WorkExperienceService {
	void create(WorkExperienceDTO workExperienceDTO);
	void update(WorkExperienceDTO workExperienceDTO);
	void delete(int idConference);
	List<WorkExperienceDTO> listByDni(String dni);
}
