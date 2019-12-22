package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.DishSize;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DishSizeRepository extends JpaRepository<DishSize, Long> {
	DishSize findById(long id);
}
