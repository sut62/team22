package com.okta.springbootvue.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;



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

    @Column(name="Name")
    private @NonNull String Name;

    

}