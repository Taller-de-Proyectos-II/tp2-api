package com.jyellow.tp2api.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

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

	public static String getDiagnostic(List<Answer> answers, String questionTypeName) {
		int score = 0;
		String chain = "";
		for (Answer answer : answers) {
			score += answer.getRealScore();
			chain = chain + "," + answer.getRealScore();
		}
		if (questionTypeName.equals("Ansiedad")) {
			if (score >= 75)
				return "Ansiedad grave";
			else if (score <= 74 && score >= 60)
				return "Ansiedad severa";
			else if (score <= 59 && score >= 45)
				return "Ansiedad leve";
			else
				return "No hay ansiedad presente";
		} else if (questionTypeName.equals("Depresión")) {
			if (score >= 53)
				return "Depresión grave";
			else if (score <= 52 && score >= 42)
				return "Depresión severa";
			else if (score <= 41 && score >= 28)
				return "Depresión leve";
			else
				return "No hay depresión presente";
		} else {
			final String uri = "https://app-tp2-ia.herokuapp.com/manifestations";
			chain = chain.substring(1, chain.length());
			RestTemplate restTemplate = new RestTemplate();
			String reqBody = "{ \"manifestations\": [" + chain + "] }";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> result = new HttpEntity<String>(reqBody, headers);
			String resultString = restTemplate.postForObject(uri, result, String.class);
			return resultString;
		}
	}

	public static boolean getDiagnosticAlert(List<AlertAnswer> alertAnswers) {
		String chain = "";
		for (AlertAnswer alertAnswer : alertAnswers)
			chain = chain + "," + alertAnswer.getScore();

		final String uri = "https://app-tp2-ia.herokuapp.com/alerts";
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
	
	public static String getColor(String diagnostic) {
		String color = "white";
		switch (diagnostic) {
		case "Necesita asignación de prueba":
			color = "red";
			break;
		case "Ansiedad grave":
			color = "red";
			break;
		case "Depresión grave":
			color = "red";
			break;
		case "Ansiedad severa":
			color = "orange";
			break;
		case "Depresión severa":
			color = "orange";
			break;
		case "Ansiedad leve":
			color = "yellow";
			break;
		case "Depresión leve":
			color = "yellow";
			break;
		case "No hay ansiedad presente":
			color = "green";
			break;
		case "No hay depresión presente":
			color = "green";
			break;
		case "No necesita asignación de prueba":
			color = "green";
			break;
		default:
			break;
		}
		return color;
	}
}
