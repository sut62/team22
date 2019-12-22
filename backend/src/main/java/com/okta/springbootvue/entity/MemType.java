package com.cpe.backend.entity;

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
@Table(name="MEMTYPE")
public class MemType {
    @Id
    @SequenceGenerator(name="memtype_SEQ",sequenceName="memtype_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="memtype_SEQ")
    @Column(name="MEMTYPE_ID",unique = true, nullable = true)

    private @NonNull Long id;
  
    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<MemType> Member;
    
}