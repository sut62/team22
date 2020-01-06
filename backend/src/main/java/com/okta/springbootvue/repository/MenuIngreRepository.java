package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.MenuIngre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MenuIngreRepository extends JpaRepository<MenuIngre, Long> {
	MenuIngre findById(long mnid);
}
