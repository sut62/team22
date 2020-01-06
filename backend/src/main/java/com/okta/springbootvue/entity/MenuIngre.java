package com.okta.springbootvue.entity;

import lombok.*;

import java.util.Date;

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
@Table(name="MENUINGREDIENT")
public class MenuIngre {

  @Id
  @SequenceGenerator(name="ingre_gen",sequenceName="ingre_num")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ingre_gen")

  @Column(name = "INGRE_ID", unique = true, nullable = true)
  private @NonNull Long id;

  @Column(name = "INGREDIENT_NAME")
  private @NonNull String iname;

  @OneToMany(fetch = FetchType.EAGER)

  private Collection<ManageMenu> create_data;

}
