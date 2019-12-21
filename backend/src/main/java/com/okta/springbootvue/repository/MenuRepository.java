package com.cpe.backend.repository;

import com.cpe.backend.entity.Menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MenuRepository extends JpaRepository<Menu, Long> {
	Menu findById(long id);
}
