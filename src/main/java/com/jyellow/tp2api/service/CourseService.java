package com.jyellow.tp2api.service;

import java.util.List;

import com.jyellow.tp2api.dto.CourseDTO;

public interface CourseService {
	void create(CourseDTO courseDTO);
	void update(CourseDTO courseDTO);
	void delete(int idCourse);
	List<CourseDTO> listByDni(String dni);
}
