package com.okta.springbootvue.entity;

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
@Table(name="Position")
public class Position {
    @Id
    @SequenceGenerator(name="Position_SEQ",sequenceName="Position_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Position_SEQ")
    @Column(name="Position_ID",unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name = "Position")
    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Employee> employee;
}
