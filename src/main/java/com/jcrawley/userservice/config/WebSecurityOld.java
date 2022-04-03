package com.jcrawley.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

	@Configuration
	@EnableWebSecurity
	public class WebSecurityOld extends WebSecurityConfigurerAdapter {

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	 //CSRF default support method: GET|HEAD|TRACE|OPTIONS, does not support POST, not what we want, so cancel CSRF defense
	        http.csrf().disable();
	        
	        http.cors().and().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
			.antMatchers(HttpMethod.GET,"/api/**").denyAll();
	    }
	 

	}