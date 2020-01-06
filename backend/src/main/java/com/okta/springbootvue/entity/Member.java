package com.okta.springbootvue.entity;

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
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Entity
@NoArgsConstructor
@Table(name="MEMBER")
public class Member {

    @Id
    @SequenceGenerator(name="member_seq",sequenceName="member_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="member_seq")
    @Column(name = "MEMBER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private @NonNull Date birth;
    
    private @NonNull String tel;

    private @NonNull String mail;

    @Column(name="DATE")
    private @NonNull Date SaveDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Prefix.class)
    @JoinColumn(name = "PREFIX_ID", insertable = true)
    private Prefix select_prefix;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender select_gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MemType.class)
    @JoinColumn(name = "MemType_ID", insertable = true)
    private MemType select_memtype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private Employee select_employee;

    

}
