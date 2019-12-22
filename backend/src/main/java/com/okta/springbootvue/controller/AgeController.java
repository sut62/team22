package com.okta.springbootvue.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Age;
import com.okta.springbootvue.repository.AgeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AgeController {

    @Autowired
    private final AgeRepository ageRepository;

    public AgeController(AgeRepository ageRepository) {
        this.ageRepository = ageRepository;
    }

    @GetMapping("/Age")
    public Collection<Age> Ages() {
        return ageRepository.findAll().stream().collect(Collectors.toList());
    }

}