package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymptomDTO {
	private int idSymptom;
	private String name;
	private String description;
	private int idManifestation;
}
