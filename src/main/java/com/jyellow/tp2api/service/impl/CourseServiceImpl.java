package com.jyellow.tp2api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyellow.tp2api.dto.CourseDTO;
import com.jyellow.tp2api.model.Course;
import com.jyellow.tp2api.model.Psychologist;
import com.jyellow.tp2api.repository.CourseRepository;
import com.jyellow.tp2api.repository.PsychologistRepository;
import com.jyellow.tp2api.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	PsychologistRepository psychologistRepository;

	@Override
	public void create(CourseDTO courseDTO) {
		Course course = new Course();
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		course.setStudyCenter(courseDTO.getStudyCenter());
		Psychologist psychologist = psychologistRepository.findByUserLoginDni(courseDTO.getPsychologistDni());
		course.setPsychologist(psychologist);
		courseRepository.save(course);
	}

	@Override
	public void update(CourseDTO courseDTO) {
		Course course = courseRepository.findById(courseDTO.getIdCourse()).get();
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		course.setStudyCenter(courseDTO.getStudyCenter());
		courseRepository.save(course);
	}

	@Override
	public void delete(int idCourse) {
		courseRepository.deleteById(idCourse);
	}

	@Override
	public List<CourseDTO> listByDni(String dni) {
		List<Course> courses = courseRepository.findByPsychologistUserLoginDni(dni);
		List<CourseDTO> coursesDTO = new ArrayList<CourseDTO>();
		CourseDTO courseDTO = new CourseDTO();
		for(Course course: courses) {
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
