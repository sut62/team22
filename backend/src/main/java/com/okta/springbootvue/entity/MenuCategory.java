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
@Table(name="MENU_CATEGORY")
public class MenuCategory {

  @Id
  @SequenceGenerator(name="cate_gen",sequenceName="cate_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cate_gen")
  @Column(name="CATEGORY_NO",unique = true, nullable = true)
  private @NonNull Long mid;

  @Column(name="CATEGORY_NAME")
  private @NonNull String cname;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ManageMenu> createMenu;
}
