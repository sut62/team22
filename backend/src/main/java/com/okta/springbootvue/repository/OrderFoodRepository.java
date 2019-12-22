package com.okta.springbootvue.repository;


import com.okta.springbootvue.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
	OrderFood findById(long id);
}

