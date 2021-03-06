package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.ManageMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ManageMenuRepository extends JpaRepository<ManageMenu, Long> {

}
