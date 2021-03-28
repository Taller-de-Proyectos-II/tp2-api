package com.jyellow.tp2api.service;

import com.jyellow.tp2api.dto.GuardianDTO;
import com.jyellow.tp2api.dto.UserLoginDTO;

public interface GuardianService {
	
	int create(GuardianDTO guardianDTO, UserLoginDTO userLoginDTO);
	int update(GuardianDTO guardianDTO, UserLoginDTO userLoginDTO);
	GuardianDTO listByDni(String dni);
}
