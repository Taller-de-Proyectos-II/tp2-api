package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistDTO {
	private String names;
	private String lastNames;
	private String phone;
	private String email;
	private String description;
	private String birthday;
	private String cpsp;
	private UserLoginDTO userLoginDTO;
}
