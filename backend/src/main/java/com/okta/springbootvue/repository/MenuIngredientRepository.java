package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.MenuIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MenuIngredientRepository extends JpaRepository<MenuIngredient, Long> {
  MenuIngredient findById(long mid);
}
