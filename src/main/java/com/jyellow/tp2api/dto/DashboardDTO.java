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
public class DashboardDTO {
	// private int[] resultManifestacion;
	private int[] resultAnsiedad;
	private int[] resultDepresion;
	// private List<TestDashboardDTO> testDashboardManifestacion;
	private List<TestDashboardDTO> testDashboardAnsiedad;
	private List<TestDashboardDTO> testDashboardDepresion;
}
