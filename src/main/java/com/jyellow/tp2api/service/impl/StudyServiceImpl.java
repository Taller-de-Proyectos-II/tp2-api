package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.dto.StudyDTO;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.model.Study;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.repository.StudyRepository;
import com.jyellow.tp2api.service.StudyService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class StudyServiceImpl implements StudyService {
	@Autowired
	StudyRepository studyRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Transactional
	@Override
	public void create(StudyDTO studyDTO) {
		log.info("StudyServiceImpl: method create");
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

	@Transactional
	@Override
	public void update(StudyDTO studyDTO) {
		log.info("StudyServiceImpl: method update");
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

	@Transactional
	@Override
	public void delete(int idStudy) {
		log.info("StudyServiceImpl: method delete");
		studyRepository.deleteById(idStudy);
	}

	@Transactional
	@Override
	public List<StudyDTO> listByDni(String dni) {
		log.info("StudyServiceImpl: method listByDni");
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
