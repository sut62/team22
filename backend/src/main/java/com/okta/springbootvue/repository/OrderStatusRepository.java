package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
	OrderStatus findById(long id);

	
	
}

