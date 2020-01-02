package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="MANAGE_MENU")
public class ManageMenu {

  @Id
  @SequenceGenerator(name="menu_gen",sequenceName="menu_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="menu_gen")
  @Column(name = "MENU_ID", unique = true, nullable = true)
  private @NonNull Long mid;

  @Column(name="MENU_NAME")
  private @NonNull String mname;

  @Column(name="MENU_PRICE")
  private @NonNull Integer mprice;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuCategory.class)
  @JoinColumn(name = "CATEGORY_NAME", insertable = true)
  private MenuCategory sel_category;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuIngredient.class)
  @JoinColumn(name = "INGREDIENT", insertable = true)
  private MenuIngredient sel_ingredient;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuType.class)
  @JoinColumn(name = "TYPE", insertable = true)
  private MenuType sel_type;

}
