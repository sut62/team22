package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="PREFIX")
public class Prefix {
    @Id
    @SequenceGenerator(name="prefix_SEQ",sequenceName="prefix_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="prefix_SEQ")
    @Column(name="PREFIX_ID",unique = true, nullable = true)

    private @NonNull Long id;
    
    @NotNull(message = "must not be null")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Member> Member;
    
}
