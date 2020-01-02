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
  @SequenceGenerator(name="in_gen",sequenceName="in_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="in_gen")
  @Column(name="INGREDIENT_NO",unique = true, nullable = true)
  private @NonNull Long mid;

  @Column(name="INGREDIENT")
  private @NonNull String ingredientname;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ManageMenu> createMenu;
}
