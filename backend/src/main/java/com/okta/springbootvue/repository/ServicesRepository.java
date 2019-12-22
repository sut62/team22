package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public
interface ServicesRepository extends JpaRepository<services, Long> {
	services findById(long id);
}