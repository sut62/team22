package com.okta.springbootvue.repository;

import java.util.Collection;

import com.okta.springbootvue.entity.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReservationsRepository extends JpaRepository<reservations, Long> {
	reservations findById(long id);

	@Query(value = "SELECT * FROM RESERVATIONS as r where r.tables_id = :tableid AND r.RESERVE_DATE_AND_TIME = :datetime ",nativeQuery = true)
	Collection<reservations> findReserveByTableAndDateTime(@Param("tableid") long tableid,@Param("datetime") String datetime );
}