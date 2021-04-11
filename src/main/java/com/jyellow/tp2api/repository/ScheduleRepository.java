package com.jyellow.tp2api.repository;

import org.springframework.stereotype.Repository;
import com.jyellow.tp2api.model.Schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	List<Schedule> findByPsychologistsUserLoginDni(String dni);
}
