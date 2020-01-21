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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@Table(name="MENU")
public class ManageMenu {

  @Id
  @SequenceGenerator(name="mn_gen",sequenceName="menu_num")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mn_gen")
  @Column(name = "MENU_ID", unique = true, nullable = true)
  private @NonNull Long mnid;

  @NotNull
  @Pattern(regexp = "\\D{1,30}")
  @Column(name="MENU_NAME",unique=true)
  private String m_name;

  @NotNull
  @Pattern(regexp = "\\d{1,4}")
  @Column(name="MENU_PRICE")
  private String m_price;

  //====================================================================

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuCate.class)
  @JoinColumn(name = "CATE_ID", insertable = true)
  private MenuCate sel_cate;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuIngre.class)
  @JoinColumn(name = "INGRE_ID", insertable = true)
  private MenuIngre sel_ingre;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MenuType.class)
  @JoinColumn(name = "TYPE_ID", insertable = true)
  private MenuType sel_type;


}
