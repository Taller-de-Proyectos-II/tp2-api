package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Conference;
import com.jyellow.tp2api.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	List<Course> findByPsychologistUserLoginDni(String dni);
}
