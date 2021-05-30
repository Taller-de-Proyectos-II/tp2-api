package com.jyellow.tp2api;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jyellow.tp2api.config.JWTAuthorizationFilter;

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
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login/**").permitAll()
				.antMatchers(HttpMethod.GET, "/patient/image/**").permitAll()
				.antMatchers(HttpMethod.POST, "/patient/image/**").permitAll()
				.antMatchers(HttpMethod.GET, "/guardian/image/**").permitAll()
				.antMatchers(HttpMethod.POST, "/guardian/image/**").permitAll()
				.anyRequest().authenticated();
			http.cors();
		}
	}


}
