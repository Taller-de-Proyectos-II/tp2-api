package com.jyellow.tp2api;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp2ApiApplication {

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
        System.out.println(new Date());
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Tp2ApiApplication.class, args);
	}

}
