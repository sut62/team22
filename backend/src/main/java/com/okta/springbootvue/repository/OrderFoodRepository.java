package com.okta.springbootvue.repository;


import java.util.Optional;


import com.okta.springbootvue.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface OrderFoodRepository extends JpaRepository<OrderFood, Long> {
	OrderFood findById(long id);
	/*@Query(value = "SELECT * FROM RESERVATIONS as r where r.table_id = :tableid AND r.RESERVE_DATE_AND_TIME = :datetime ",nativeQuery = true)
	public void updateOrder();*/
	//OrderFood updateById(long id);
	//OrderFood findByDishnumber(int i);
	//OrderFood updateDishnumber(int i);

	
	
}

