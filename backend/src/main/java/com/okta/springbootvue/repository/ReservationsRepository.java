package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.reservations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReservationsRepository extends JpaRepository<reservations, Long> {
	reservations findById(long id);
}