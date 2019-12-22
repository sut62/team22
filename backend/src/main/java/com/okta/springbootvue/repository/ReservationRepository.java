package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.reservations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReservationRepository extends JpaRepository<reservations, Long> {
	reservations findById(long id);
}