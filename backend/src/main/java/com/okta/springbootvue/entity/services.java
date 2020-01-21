package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SERVICES")
public class services {
	@Id
	@SequenceGenerator(name="SERVICES_ID_SEQ",sequenceName="SERVICES_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICES_ID_SEQ")
	@Column(name="SERVICES_ID",unique = true, nullable = true)
	private Long id;

	@NotBlank(message = "SERVICES can not be null or blank")
	@Pattern(regexp = "\\D+",message = "Must be Character")
	@Column(name = "SERVICE_NAME",unique = false,nullable = false)
	private  String ServiceName;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<reservations> reservations;
}

