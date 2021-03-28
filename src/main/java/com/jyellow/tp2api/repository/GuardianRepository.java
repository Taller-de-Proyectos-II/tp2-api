package com.jyellow.tp2api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Guardian;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
	Guardian findByEmail(String email);
	Guardian findByUserLoginDni(String dni);
}
