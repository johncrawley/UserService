package com.jcrawley.userservice.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Temp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@NotBlank(message = "X should not be blank!")
	private String x;
	
	
	public Temp() {}
	
	public void setX(String x) {
		this.x = x;
	}
	
	public String getX() {
		return this.x;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

}
