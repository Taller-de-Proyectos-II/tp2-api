package com.jyellow.tp2api.dto;

public class UserLoginDTO {
	
	private String dni;
	private String password;
	
	public UserLoginDTO() {
		super();
	}

	public UserLoginDTO(String dni, String password) {
		super();
		this.dni = dni;
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
