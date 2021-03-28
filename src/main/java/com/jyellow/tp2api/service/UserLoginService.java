package com.jyellow.tp2api.service;

public interface UserLoginService {
	
	int loginSuccessful(String dni, String password);

	boolean existEmailPsychologist(String email);

	boolean existEmailGuardian(String email);
	
	void changePasswordPsychologist(String email, String password);

	void changePasswordGuardian(String email, String password);
}
