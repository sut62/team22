package com.cpe.backend.repository;

import com.cpe.backend.entity.TableNumber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface TableNumberRepository extends JpaRepository<TableNumber, Long> {
	TableNumber findById(long id);
}
