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
public class AlertDTO {
	private int idAlert;
	private String date;
	private boolean important;
	List<AlertAnswerDTO> alertAnswersDTO;
}
