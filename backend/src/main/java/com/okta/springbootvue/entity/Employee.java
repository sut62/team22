package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;



import org.springframework.format.annotation.DateTimeFormat;

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

    @Pattern(regexp = "\\D{1,50}")
    private String e_name;
    
    @Pattern(regexp = "\\d{10}")
    private String E_TEL;

    private @NotNull String E_ADDRESS;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date E_BIRTH;
    
    @Pattern(regexp = "\\d{13}")
    private String E_NUM;

    @Column(name = "REGDATE")
    private Date E_REGDATE;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Age.class)
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
    private Marital_Status marital_Status;
    
}
