package com.jyellow.tp2api.dto;

public class GuardianDTO {

	private String names;

	private String lastNames;

	private String phone;

	private String email;

	private String birthday;
	
	private UserLoginDTO userLoginDTO;

	public GuardianDTO() {
		super();
	}

	public GuardianDTO(String names, String lastNames, String phone, String email, String birthday) {
		super();
		this.names = names;
		this.lastNames = lastNames;
		this.phone = phone;
		this.email = email;
		this.birthday = birthday;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public UserLoginDTO getUserLoginDTO() {
		return userLoginDTO;
	}

	public void setUserLoginDTO(UserLoginDTO userLoginDTO) {
		this.userLoginDTO = userLoginDTO;
	}

}
