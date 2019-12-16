package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="TableNumber")
public class TableNumber {

    @Id
    @SequenceGenerator(name="TableNumber_seq",sequenceName="TableNumber_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TableNumber_seq")  
    @Column(name = "TableNumber_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull long number;

}
