package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.StudyDTO;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.Study;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.StudyRepository;
import com.jyellow.tp2api.service.StudyService;

@Service
public class StudyServiceImpl implements StudyService {
	@Autowired
	StudyRepository studyRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public void create(StudyDTO studyDTO) {
		Study study = new Study();
		study.setAcademicDiscipline(studyDTO.getAcademicDiscipline());
		study.setComplete(studyDTO.isComplete());
		study.setDescription(studyDTO.getDescription());
		study.setEndDate(studyDTO.getEndDate());
		study.setStartDate(studyDTO.getStartDate());
		study.setTitle(studyDTO.getTitle());
		study.setStudyCenter(studyDTO.getStudyCenter());
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(studyDTO.getPsychologistDni());
		study.setPsychologist(psychologist);
		studyRepository.save(study);
	}

	@Override
	public void update(StudyDTO studyDTO) {
		Study study = studyRepository.findById(studyDTO.getIdStudy()).get();
		study.setAcademicDiscipline(studyDTO.getAcademicDiscipline());
		study.setComplete(studyDTO.isComplete());
		study.setDescription(studyDTO.getDescription());
		study.setEndDate(studyDTO.getEndDate());
		study.setStartDate(studyDTO.getStartDate());
		study.setTitle(studyDTO.getTitle());
		study.setStudyCenter(studyDTO.getStudyCenter());
		studyRepository.save(study);
	}

	@Override
	public void delete(int idStudy) {
		studyRepository.deleteById(idStudy);
	}

	@Override
	public List<StudyDTO> listByDni(String dni) {
		List<Study> studies = studyRepository.findByPsychologistUserLoginDni(dni);
		List<StudyDTO> studiesDTO = new ArrayList<StudyDTO>();
		StudyDTO studyDTO = new StudyDTO();
		for (Study study : studies) {
			studyDTO = new StudyDTO();
			studyDTO.setIdStudy(study.getIdStudy());
			studyDTO.setAcademicDiscipline(study.getAcademicDiscipline());
			studyDTO.setTitle(study.getTitle());
			studyDTO.setComplete(study.isComplete());
			studyDTO.setStartDate(study.getStartDate());
			studyDTO.setEndDate(study.getEndDate());
			studyDTO.setDescription(study.getDescription());
			studyDTO.setStudyCenter(study.getStudyCenter());
			studyDTO.setPsychologistDni(dni);
			studiesDTO.add(studyDTO);
		}
		return studiesDTO;
	}
}
