package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TablesRepository extends JpaRepository<tables, Long> {
	tables findById(long id);
}