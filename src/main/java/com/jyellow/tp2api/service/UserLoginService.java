package com.jyellow.tp2api.service;

public interface UserLoginService {

	int loginSuccessful(String dni, String password);

	boolean existEmailPsychologist(String email);

	boolean existEmailPatient(String email);

	void changePasswordPsychologist(String email, String password);

	void changePasswordPatient(String email, String password);

	int loginSuccessfulAdmin(String password);

	void convertPasswords();

	int loginSuccessfulPsychologist(String dni, String password);
}
