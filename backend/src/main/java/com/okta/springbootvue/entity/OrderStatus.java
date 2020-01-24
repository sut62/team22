package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="OrderStatus")
public class OrderStatus{
    @Id
    @SequenceGenerator(name="OrderStatus_SEQ",sequenceName="OrderStatus_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="OrderStatus_SEQ")
    @Column(name="OrderStatus_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull(message = "must not be status null")
    @Column(name = "status")
    private String status;

}
