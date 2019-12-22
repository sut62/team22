package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Age;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface AgeRepository extends JpaRepository<Age, Long> {
    Age findById(long id);
}