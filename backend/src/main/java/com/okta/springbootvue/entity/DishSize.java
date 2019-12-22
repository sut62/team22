package com.okta.springbootvue.entity;

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
@Table(name="DishSize")
public class DishSize {
	@Id
	@SequenceGenerator(name="DishSize_seq",sequenceName="DishSize_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DishSize_seq")
	@Column(name="DishSize_ID",unique = true, nullable = true)
	private @NonNull Long id;

	private @NonNull String size;

}
