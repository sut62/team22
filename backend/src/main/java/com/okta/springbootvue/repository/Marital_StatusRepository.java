package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Marital_Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface Marital_StatusRepository extends JpaRepository<Marital_Status, Long> {
	Marital_Status findById(long id);
}