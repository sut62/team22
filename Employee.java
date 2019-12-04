package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.backend.entity.Age;
import com.cpe.backend.entity.Gender;
import com.cpe.backend.entity.Position;
import com.cpe.backend.entity.Marital_Status;

@Data
@Entity
@NoArgsConstructor
@Table(name="Employee")
public class Employee {

    @Id
    @SequenceGenerator(name="Employee_seq",sequenceName="Employee_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Employee_seq")
    @Column(name = "Employee_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String E_NAME;
     
    private @NonNull String E_TEL;

    private @NonNull String E_ADDRESS;

    private @NonNull Date E_BIRTH;
    
    private @NonNull String E_NUM;

    private @NonNull String E_EMID;

    private @NonNull Date E_REGDATE;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prefix.class)
    @JoinColumn(name = "Age_ID", insertable = true)
    private Age age;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "Position_ID", insertable = true)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Marital_Status.class)
    @JoinColumn(name = "Marital_Status_ID", insertable = true)
    private Marital_Status marital_status;
    
}