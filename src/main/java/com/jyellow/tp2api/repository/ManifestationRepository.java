package com.jyellow.tp2api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jyellow.tp2api.model.Manifestation;

@Repository
public interface ManifestationRepository extends JpaRepository<Manifestation, Integer>{

}
