package com.jyellow.tp2api.util;

import java.util.List;

import com.jyellow.tp2api.model.Answer;

public class ScoreOperation {
	public static List<Answer> asignRealScore(List<Answer> answers, String questionTypeName) {
		if (questionTypeName.equals("Depresión")) {
			for (int i = 0; i < answers.size(); i++) {
				if(i == 1 || i == 4 || i == 5 || i == 10 || i == 11 || i == 13 || i == 15 || i == 16 || i == 17 || i == 19) {
					switch(answers.get(i).getScore()) {
					case 1: answers.get(i).setRealScore(4);
					case 2: answers.get(i).setRealScore(3);
					case 3: answers.get(i).setRealScore(2);
					case 4: answers.get(i).setRealScore(1);
					}
				}
			}
		}
		else {
			for (int i = 0; i < answers.size(); i++) {
				if(i == 4 || i == 8 || i == 12 || i == 16 || i == 19) {
					switch(answers.get(i).getScore()) {
					case 1: answers.get(i).setRealScore(4);
					case 2: answers.get(i).setRealScore(3);
					case 3: answers.get(i).setRealScore(2);
					case 4: answers.get(i).setRealScore(1);
					}
				}
			}
		}
		return answers;
	}
	
	public static String getDiagnostic(List<Answer> answers, String questionTypeName) {
		int score = 0;
		for (Answer answer : answers)
			score += answer.getRealScore(); 
		if (questionTypeName.equals("Depresión")) {
			if(score >= 75) return "Ansiedad grave";
			else if (score <= 74 && score >=60) return "Ansiedad severa";
			else if(score <= 59 && score >=45) return "Ansiedad leve";
			else return "No hay ansiedad presente";
		}
		else {
			if(score >= 53) return "Depresión grave";
			else if (score <= 52 && score >=42) return "Depresión severa";
			else if(score <= 41 && score >=28) return "Depresión leve";
			else return "No hay depresión presente";
		}
	}
}
