package com.jyellow.tp2api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManifestationsDTO {
	private ManifestationDTO physical;
	private ManifestationDTO emotional;
	private ManifestationDTO conductual;
	private ManifestationDTO cognitive;
}
