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
@Table(name="MENUCATEGORY")
public class MenuCate {

  @Id
  @SequenceGenerator(name="c_gen",sequenceName="cate_num")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="c_gen")

  @Column(name = "CATE_ID", unique = true, nullable = true)
  private @NonNull Long cid;

  private @NonNull String cname;

  @OneToMany(fetch = FetchType.EAGER)

  private Collection<ManageMenu> create_data;

}
