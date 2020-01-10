package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.OrderType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface OrderTypeRepository extends JpaRepository<OrderType, Long> {
	OrderType findById(long id);
}
