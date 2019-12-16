package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GenerationType;

import javax.persistence.GeneratedValue;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="Menu")
public class Menu {
	@Id
	@SequenceGenerator(name="Menu_seq",sequenceName="Menu_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Menu_seq")
	@Column(name="Menu_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

    
}
