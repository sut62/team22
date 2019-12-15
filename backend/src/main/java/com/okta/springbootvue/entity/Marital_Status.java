package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="Marital_Status")
public class Marital_Status {
    @Id
    @SequenceGenerator(name="Marital_Status_SEQ",sequenceName="Marital_Status_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Marital_Status_SEQ")
    @Column(name="Marital_Status_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    
    private Collection<Employee> employee;
}