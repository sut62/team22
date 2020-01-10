package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Membership;
import com.okta.springbootvue.repository.MembershipRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MembershipController {

    @Autowired
    private final MembershipRepository membershipRepository;

    public MembershipController(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @GetMapping("/memberships")
    public Collection<Membership> Memberships() {
        return membershipRepository.findAll().stream().collect(Collectors.toList());
    }

}