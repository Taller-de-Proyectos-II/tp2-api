package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer> {
	List<Study> findByPsychologistUserLoginDni(String dni);
}
