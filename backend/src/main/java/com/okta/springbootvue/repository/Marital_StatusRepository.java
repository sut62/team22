package com.cpe.backend.repository;

import com.cpe.backend.entity.Marital_Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface Marital_StatusRepository extends JpaRepository<Marital_Status, Long> {
	Marital_Status findById(long id);
}