package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.MenuCate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MenuCateRepository extends JpaRepository<MenuCate, Long> {
	MenuCate findById(long mnid);
}
