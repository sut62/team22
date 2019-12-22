package com.okta.springbootvue.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.Marital_Status;
import com.okta.springbootvue.repository.Marital_StatusRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Marital_StatusController {

    @Autowired
    private final Marital_StatusRepository marital_StatusRepository;

    public Marital_StatusController(Marital_StatusRepository marital_StatusRepository) {
        this.marital_StatusRepository = marital_StatusRepository;
    }

    @GetMapping("/Marital_Status")
    public Collection<Marital_Status> Marital_Statuss() {
        return marital_StatusRepository.findAll().stream().collect(Collectors.toList());
    }

}