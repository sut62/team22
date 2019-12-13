package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="MENU_TYPE")
public class MenuType {
  @Id
  @SequenceGenerator(name="type_seq",sequenceName="type_seq")
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="type_seq")

  @Column(name="MENU_ID",unique = true, nullable = true)
  private @NonNull String mID;

  private @NonNull String mType;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ManageMenu> ManageSeq ;
}
