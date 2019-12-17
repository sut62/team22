package com.cpe.backend.repository;

import com.cpe.backend.entity.MemType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MemTypeRepository extends JpaRepository<MemType, Long> {
    MemType findById(long id);
}