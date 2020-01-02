package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
  MenuCategory findById(long mid);
}
