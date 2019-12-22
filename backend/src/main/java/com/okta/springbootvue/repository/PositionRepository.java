package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PositionRepository extends JpaRepository<Position, Long> {
    Position findById(long id);
}