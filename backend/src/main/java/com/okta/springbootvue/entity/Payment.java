package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Date;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name="PAYMENT")
public class Payment {

    @Id
    @SequenceGenerator(name="PAYMENT_SEQ",sequenceName="PAYMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_SEQ")
    @Column(name = "PAYMENT_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
    @Min(value = 0,message = "must not below 0")
    @NotNull(message = "must not be null")
    @Positive(message = "must be positive number")
    @Column(name = "Money")
    private Integer Money;

    @Min(value = 0,message = "Must not below 0")
    @NotNull(message = "Must not be null")
    @Positive(message = "must be positive number")
    @Column(name = "Change")
    private Integer Change;

    @Min(value = 0,message = "Must not below 0")
    @NotNull(message = "Must not be null")
    @Positive(message = "must be positive number")
    @Column(name = "Total")
    private Double Total;


    @PastOrPresent(message = "must not be past")
    @Column(name = "CreateDate")
    private Date CreateDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = tables.class)
    @JoinColumn(name = "TABLE_ID", insertable = true)
    private tables selecttable;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_ID", insertable = true)
    private  Member selectmember;
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee selectemployee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Membership.class)
    @JoinColumn(name = "MEMBERSHIP_ID", insertable = true)
    private Membership selectmembership;

   


    
}