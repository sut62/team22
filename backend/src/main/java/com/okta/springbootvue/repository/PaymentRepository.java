package com.okta.springbootvue.repository;

import java.util.List;

import com.okta.springbootvue.entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);

}