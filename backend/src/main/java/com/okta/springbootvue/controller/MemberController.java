package com.okta.springbootvue.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Member;
import com.okta.springbootvue.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MemberController {
    @Autowired
    private final MemberRepository memberRepository;
    
    

    MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    public Collection<Member> Members() {
        return memberRepository.findAll().stream().collect(Collectors.toList());
    }
}