package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="OrderFood")
public class OrderFood {

    @Id
    @SequenceGenerator(name="OrderFood_seq",sequenceName="OrderFood_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="OrderFood_seq")
    @Column(name = "OrderFood_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="DishNumber")
    private @NonNull Long dishnumber;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = tables.class)
    @JoinColumn(name = "tables_ID", insertable = true)
    private tables tables;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ManageMenu.class)
    @JoinColumn(name = "Menu_ID", insertable = true)
    private ManageMenu managemenu;
 
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = OrderType.class)
    @JoinColumn(name = "OrderType_ID", insertable = true)
    private OrderType ordertype;

    
}