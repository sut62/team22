package com.okta.springbootvue.entity;



import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

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
	@Column(name = "SERVICE_NAME",unique = false,nullable = false)
	private @NonNull String ServiceName;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<reservations> reservations;
}

