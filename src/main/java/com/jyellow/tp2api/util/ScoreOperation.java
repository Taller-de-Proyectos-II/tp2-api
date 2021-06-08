package com.jyellow.tp2api.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.jyellow.tp2api.dto.DataScoreDTO;
import com.jyellow.tp2api.model.AlertAnswer;
import com.jyellow.tp2api.model.Answer;

public class ScoreOperation {
	public static List<Answer> asignRealScore(List<Answer> answers, String questionTypeName) {
		if (questionTypeName.equals("Depresión")) {
			for (int i = 0; i < answers.size(); i++) {
				if (i == 1 || i == 4 || i == 5 || i == 10 || i == 11 || i == 13 || i == 15 || i == 16 || i == 17
						|| i == 19) {
					switch (answers.get(i).getScore()) {
					case 1:
						answers.get(i).setRealScore(4);
						break;
					case 2:
						answers.get(i).setRealScore(3);
						break;
					case 3:
						answers.get(i).setRealScore(2);
						break;
					case 4:
						answers.get(i).setRealScore(1);
						break;
					}
				} else {
					switch (answers.get(i).getScore()) {
					case 1:
						answers.get(i).setRealScore(1);
						break;
					case 2:
						answers.get(i).setRealScore(2);
						break;
					case 3:
						answers.get(i).setRealScore(3);
						break;
					case 4:
						answers.get(i).setRealScore(4);
						break;
					}
				}
			}
		} else if (questionTypeName.equals("Ansiedad")) {
			for (int i = 0; i < answers.size(); i++) {
				if (i == 4 || i == 8 || i == 12 || i == 16 || i == 19) {
					switch (answers.get(i).getScore()) {
					case 1:
						answers.get(i).setRealScore(4);
						break;
					case 2:
						answers.get(i).setRealScore(3);
						break;
					case 3:
						answers.get(i).setRealScore(2);
						break;
					case 4:
						answers.get(i).setRealScore(1);
						break;
					}
				} else {
					switch (answers.get(i).getScore()) {
					case 1:
						answers.get(i).setRealScore(1);
						break;
					case 2:
						answers.get(i).setRealScore(2);
						break;
					case 3:
						answers.get(i).setRealScore(3);
						break;
					case 4:
						answers.get(i).setRealScore(4);
						break;
					}
				}
			}
		} else if (questionTypeName.equals("Manifestaciones")) {
			for (int i = 0; i < answers.size(); i++) {
				switch (answers.get(i).getScore()) {
				case 1:
					answers.get(i).setRealScore(1);
					break;
				case 2:
					answers.get(i).setRealScore(2);
					break;
				case 3:
					answers.get(i).setRealScore(3);
					break;
				case 4:
					answers.get(i).setRealScore(4);
					break;
				}
			}
		}
		return answers;
	}

	public static DataScoreDTO getDiagnostic(List<Answer> answers, String questionTypeName) {
		int score = 0;
		String result = "";
		String chain = "";
		String color = "";
		for (Answer answer : answers) {
			score += answer.getRealScore();
			chain = chain + "," + answer.getRealScore();
		}
		if (questionTypeName.equals("Ansiedad")) {
			if (score >= 75) {
				result = "Ansiedad grave";
				color = "red";
			} else if (score <= 74 && score >= 60) {
				result = "Ansiedad severa";
				color = "orange";
			} else if (score <= 59 && score >= 45) {
				result = "Ansiedad leve";
				color = "yellow";
			} else {
				result = "No hay ansiedad presente";
				color = "green";
			}
		} else if (questionTypeName.equals("Depresión")) {
			if (score >= 53) {
				result = "Depresión grave";
				color = "red";
			} else if (score <= 52 && score >= 42) {
				result = "Depresión severa";
				color = "orange";
			} else if (score <= 41 && score >= 28) {
				result = "Depresión leve";
				color = "yellow";
			} else {
				result = "No hay depresión presente";
				color = "green";
			}
		} else {
			final String uri = "http://18.209.7.204:8081/manifestations";
			chain = chain.substring(1, chain.length());
			RestTemplate restTemplate = new RestTemplate();
			String reqBody = "{ \"manifestations\": [" + chain + "] }";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> resultString = new HttpEntity<String>(reqBody, headers);
			result = restTemplate.postForObject(uri, resultString, String.class);
			if (result.equals("No necesita asignación de prueba")) {
				color = "green";
			} else {
				color = "red";
			}
		}
		return new DataScoreDTO(result, score, color);

	}

	public static boolean getDiagnosticAlert(List<AlertAnswer> alertAnswers) {
		String chain = "";
		for (AlertAnswer alertAnswer : alertAnswers)
			chain = chain + "," + alertAnswer.getScore();

		final String uri = "http://18.209.7.204:8081/alerts";
		chain = chain.substring(1, chain.length());
		RestTemplate restTemplate = new RestTemplate();
		String reqBody = "{ \"alerts\": [" + chain + "] }";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> result = new HttpEntity<String>(reqBody, headers);
		String resultString = restTemplate.postForObject(uri, result, String.class);

		if (resultString.equals("Sí"))
			return true;
		else
			return false;
	}

}
