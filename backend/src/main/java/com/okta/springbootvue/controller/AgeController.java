package com.cpe.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.entity.Age;
import com.cpe.backend.repository.AgeRepository;

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