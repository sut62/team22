package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="MEMBERSHIP")
public class Membership {
    @Id
    @SequenceGenerator(name="MEMBERSHIP_SEQ",sequenceName="MEMBERSHIP_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEMBERSHIP_SEQ")
    @Column(name="MEMBERSHIP_ID_SEQ",unique = true, nullable = true)

    private @NonNull Long idmbs;
  
    private @NonNull String namembs;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Payment> payment;
    
}
