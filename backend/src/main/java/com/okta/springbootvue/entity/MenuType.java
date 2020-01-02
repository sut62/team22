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
  @SequenceGenerator(name="type_gen",sequenceName="type_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="type_gen")
  @Column(name="TYPE_NO",unique = true, nullable = true)
  private @NonNull Long mid;

  @Column(name="TYPE")
  private @NonNull String typename;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ManageMenu> createMenu;
}
