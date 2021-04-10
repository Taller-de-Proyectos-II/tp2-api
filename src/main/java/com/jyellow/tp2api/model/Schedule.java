package com.jyellow.tp2api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSchedule;
	private int hour;
	private int day;
	
	@ManyToMany(mappedBy = "schedules")
	private List<Psychologist> psychologists;
}
