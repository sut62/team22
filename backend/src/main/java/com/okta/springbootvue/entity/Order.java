package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="Order")
public class Order {

    @Id
    @SequenceGenerator(name="Order_seq",sequenceName="Order_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Order_seq")
    @Column(name = "Order_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageMenu.class)
    @JoinColumn(name = "ManageMenu_ID",insertable = true)
    private ManageMenu manageMenu;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Dishsize.class)
    @JoinColumn(name = "Dishsize_ID", insertable = true)
    private Dishsize dishsize;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Dishnumber.class)
    @JoinColumn(name = "Dishnumber_ID",insertable = true)
    private Dishnumber dishnumber;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Tablenumber.class)
    @JoinColumn(name = "Tablenumber_ID",insertable = true)
    private Tablenumber tablenumber;

}