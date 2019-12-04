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
  @SequenceGenerator(name="menu_seq",sequenceName="menu_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="menu_seq")

  //==========ATTRIBUTE==========

  @Column(name = "MENU_ID", unique = true, nullable = true)
  private @NonNull String mID;

  @Column(name="MENU_NAME")
  private @NonNull String mName;

  @Column(name="MENU_PRICE")
  private @NonNull Int mPrice;

  //==========JOIN COLUMN==========

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuType.class)
  @JoinColumn(name = "PATIENT_ID", insertable = true)
  private MenuType slct_Type;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuIngredient.class)
  @JoinColumn(name = "MENU_NAME", insertable = true)
  private MenuIngredient slct_Ingredient;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuCategory.class)
  @JoinColumn(name = "MENU_CAGATETORY", insertable = true)
  private MenuCategory slct_Category;
}
