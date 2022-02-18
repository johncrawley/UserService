package com.jcrawley.userservice.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String email;
	
	@Column
	private String name;

	@Column
	private String password;
	
	@CreationTimestamp
	@Column(updatable=false)
	private Date dateCreated;
	
	@Column
	private String tagline;
	
}
