package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.okta.springbootvue.entity.*;


@Data
@Entity
@NoArgsConstructor
@Table(name="PAYMENT")
public class Payment {

    @Id
    @SequenceGenerator(name="PAYMENT_SEQ",sequenceName="PAYMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_SEQ")
    @Column(name = "PAYMENT_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
    @Max(value = 100,message = "must not be more 100")
    @NotNull(message = "must not be null")
    @Positive(message = "must be positive number")
    @Column(name = "Status")
    private Integer statusname; 

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = OrderFood.class)
    @JoinColumn(name = "ORDER_ID", insertable = true)
    private OrderFood selectorderfood;


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