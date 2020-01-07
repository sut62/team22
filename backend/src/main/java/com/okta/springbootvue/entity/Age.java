package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="Age")
public class Age {
    @Id
    @SequenceGenerator(name="Age_SEQ",sequenceName="Age_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Age_SEQ")
    @Column(name="Age_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull Integer Age;



    @OneToMany(fetch = FetchType.EAGER)
    //mappedBy  = "type"
    private Set<Employee> employee;
}
