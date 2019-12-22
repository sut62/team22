package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.tables;
import com.okta.springbootvue.repository.TablesRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TablesController {

    @Autowired
    private final TablesRepository TableRepository;

    public TablesController(TablesRepository TableRepository) {
        this.TableRepository = TableRepository;
    }

    @GetMapping("/tableses")
    public Collection<tables> tables() {
        return TableRepository.findAll().stream().collect(Collectors.toList());
    }

}