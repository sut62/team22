package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="OrderType")
public class OrderType {
	@Id
	@SequenceGenerator(name="OrderType_seq",sequenceName="OrderType_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OrderType_seq")
	@Column(name="OrderType_ID",unique = true, nullable = true)
	private @NonNull Long id;

	
    @NotNull(message = "must not be type null")
	private String type;

}
