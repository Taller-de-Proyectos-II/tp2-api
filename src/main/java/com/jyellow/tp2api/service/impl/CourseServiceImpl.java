package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyellow.tp2api.dto.CourseDTO;
import com.jyellow.tp2api.model.Course;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.repository.CourseRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.service.CourseService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Transactional
	@Override
	public void create(CourseDTO courseDTO) {
		log.info("CourseServiceImpl: method create");
		Course course = new Course();
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		course.setStudyCenter(courseDTO.getStudyCenter());
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(courseDTO.getPsychologistDni());
		course.setPsychologist(psychologist);
		courseRepository.save(course);
	}

	@Transactional
	@Override
	public void update(CourseDTO courseDTO) {
		log.info("CourseServiceImpl: method update");
		Course course = courseRepository.findById(courseDTO.getIdCourse()).get();
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		course.setStudyCenter(courseDTO.getStudyCenter());
		courseRepository.save(course);
	}

	@Transactional
	@Override
	public void delete(int idCourse) {
		log.info("CourseServiceImpl: method delete");
		courseRepository.deleteById(idCourse);
	}

	@Transactional
	@Override
	public List<CourseDTO> listByDni(String dni) {
		log.info("CourseServiceImpl: method listByDni");
		List<Course> courses = courseRepository.findByPsychologistUserLoginDni(dni);
		List<CourseDTO> coursesDTO = new ArrayList<CourseDTO>();
		CourseDTO courseDTO = new CourseDTO();
		for(Course course: courses) {
			courseDTO = new CourseDTO();
			courseDTO.setIdCourse(course.getIdCourse());
			courseDTO.setDescription(course.getDescription());
			courseDTO.setName(course.getName());
			courseDTO.setStudyCenter(course.getStudyCenter());
			courseDTO.setPsychologistDni(dni);
			coursesDTO.add(courseDTO);
		}
		return coursesDTO;
	}
}
