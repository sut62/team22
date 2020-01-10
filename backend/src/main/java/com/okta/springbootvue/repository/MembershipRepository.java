package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Membership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MembershipRepository extends JpaRepository<Membership, Long> {
    Membership findById(long id);
}
