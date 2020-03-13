package com.okta.springbootvue.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;



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
    @Column(name = "Money")
    private Double Money;

    @Min(value = 0,message = "must not below 0")
    @NotNull(message = "must not be null")
    @Column(name = "Change")
    private Double Change;

    @Min(value = 0,message = "must not below 0")
    @NotNull(message = "must not be null") 
    @Column(name = "Total")
    private Double Total;

    @PastOrPresent(message = "must not be future")
    @Column(name = "CreateDate")
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = tables.class)
    @JoinColumn(name = "TABLE_ID", insertable = true)
    @NotNull
    private tables selecttable;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_ID", insertable = true)
    private  Member selectmember;
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    @NotNull
    private Employee selectemployee;


   


    
}
