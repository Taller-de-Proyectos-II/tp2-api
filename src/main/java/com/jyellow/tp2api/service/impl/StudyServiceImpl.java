package com.jyellow.tp2api.service.impl;

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
}
