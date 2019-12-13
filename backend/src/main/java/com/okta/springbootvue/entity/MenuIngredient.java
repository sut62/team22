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
@Table(name="MENU_INGREDIENT")
public class MenuIngredient {
  @Id
  @SequenceGenerator(name="ing_seq",sequenceName="ing_seq")
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ing_seq")

  @Column(name="MENU_ID",unique = true, nullable = true)
  private @NonNull String mID;

  private @NonNull String mIngredient;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ManageMenu> ManageSeq ;
}
