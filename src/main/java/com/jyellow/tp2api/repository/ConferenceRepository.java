package com.jyellow.tp2api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
	List<Conference> findByPsychologistUserLoginDni(String dni);
}
