package com.jyellow.tp2api.service;

import com.jyellow.tp2api.dto.StudyDTO;

public interface StudyService {
	void create(StudyDTO studyDTO);
	void update(StudyDTO studyDTO);
	void delete(int idStudy);
}
