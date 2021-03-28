package com.jyellow.tp2api.service;

import com.jyellow.tp2api.dto.CourseDTO;

public interface CourseService {
	void create(CourseDTO courseDTO);
	void update(CourseDTO courseDTO);
	void delete(int idCourse);
}
