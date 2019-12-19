package com.cpe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.entity.Prefix;
import com.cpe.backend.repository.PrefixRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PrefixController {

    @Autowired
    private final PrefixRepository prefixRepository;

    public PrefixController(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    @GetMapping("/prefixs")
    public Collection<Prefix> Prefixs() {
        return prefixRepository.findAll().stream().collect(Collectors.toList());
    }

}