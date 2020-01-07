package com.okta.springbootvue.repository;

import com.okta.springbootvue.entity.Prefix;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public
interface PrefixRepository extends JpaRepository<Prefix, Long> {
    Prefix findById(long id);
}