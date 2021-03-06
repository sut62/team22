package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="MENUTYPE")
public class MenuType {

  @Id
  @SequenceGenerator(name="type_gen",sequenceName="type_num")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="type_gen")

  @Column(name = "TYPE_ID", unique = true, nullable = true)
  private @NonNull Long tid;

  @Column(name = "TYPE_NAME")
  private @NonNull String tname;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ManageMenu> create_data;

}
