package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.WorkExperienceDTO;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.WorkExperience;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.WorkExperienceRepository;
import com.jyellow.tp2api.service.WorkExperienceService;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
	@Autowired
	WorkExperienceRepository workExperienceRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public void create(WorkExperienceDTO workExperienceDTO) {
		WorkExperience workExperience = new WorkExperience();
		workExperience.setCurrent(workExperienceDTO.isCurrent());
		workExperience.setDescription(workExperienceDTO.getDescription());
		workExperience.setOccupation(workExperienceDTO.getOccupation());
		workExperience.setPlace(workExperienceDTO.getPlace());
		workExperience.setEndDate(workExperienceDTO.getEndDate());
		workExperience.setStartDate(workExperienceDTO.getStartDate());
		workExperience.setWorkingDayType(workExperienceDTO.getWorkingDayType());
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(workExperienceDTO.getPsychologistDni());
		workExperience.setPsychologist(psychologist);
		workExperienceRepository.save(workExperience);
	}

	@Override
	public void update(WorkExperienceDTO workExperienceDTO) {
		WorkExperience workExperience = workExperienceRepository.findById(workExperienceDTO.getIdWorkExperience()).get();
		workExperience.setCurrent(workExperienceDTO.isCurrent());
		workExperience.setDescription(workExperienceDTO.getDescription());
		workExperience.setOccupation(workExperienceDTO.getOccupation());
		workExperience.setPlace(workExperienceDTO.getPlace());
		workExperience.setEndDate(workExperienceDTO.getEndDate());
		workExperience.setStartDate(workExperienceDTO.getStartDate());
		workExperience.setWorkingDayType(workExperienceDTO.getWorkingDayType());
		workExperienceRepository.save(workExperience);
	}

	@Override
	public void delete(int idWorkExperience) {
		workExperienceRepository.deleteById(idWorkExperience);
	}

	@Override
	public List<WorkExperienceDTO> listByDni(String dni) {
		List<WorkExperience> workExperiences = workExperienceRepository.findByPsychologistUserLoginDni(dni);
		List<WorkExperienceDTO> workExperiencesDTO = new ArrayList<WorkExperienceDTO>();
		WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
		for(WorkExperience workExperience: workExperiences) {
			workExperienceDTO.setIdWorkExperience(workExperience.getIdWorkExperience());
			workExperienceDTO.setPlace(workExperience.getPlace());
			workExperienceDTO.setOccupation(workExperience.getOccupation());
			workExperienceDTO.setDescription(workExperience.getDescription());
			workExperienceDTO.setWorkingDayType(workExperience.getWorkingDayType());
			workExperienceDTO.setStartDate(workExperience.getStartDate());
			workExperienceDTO.setEndDate(workExperience.getEndDate());
			workExperienceDTO.setCurrent(workExperience.isCurrent());
			workExperienceDTO.setPsychologistDni(dni);
			workExperiencesDTO.add(workExperienceDTO);
		}
		return workExperiencesDTO;
	}
}
