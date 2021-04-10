package com.jyellow.tp2api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManifestationDTO {
	private int idManifestation;
	private String name;
	private String description;
	private List<SymptomDTO> symptoms;
}
