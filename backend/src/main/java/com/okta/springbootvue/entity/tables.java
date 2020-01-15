package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TABLES")
public class tables {
	@Id
	@SequenceGenerator(name="TABLES_ID_SEQ",sequenceName="TABLES_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TABLES_ID_SEQ")
	@Column(name="TABLES_ID",unique = true, nullable = true)
	private Long id;

	@Pattern(regexp = "\\d+",message = "Must not be ZERO or Character")
	@Min(value = 1 , message = "Seats can not be EMPTY!")
	@Positive(message = "Cant not be NEGATIVE!")
	@Column(name = "SEATS",unique = false,nullable = false)
	private @NonNull Integer Seats;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<reservations> reservations;
}

