package com.cpe.backend.controller;

import com.cpe.backend.entity.TableNumber;
import com.cpe.backend.repository.TableNumberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TableNumberController {

    @Autowired
    private final TableNumberRepository tableNumberRepository;

    public TableNumberController(TableNumberRepository tableNumberRepository) {
        this.tableNumberRepository = tableNumberRepository;
    }

    @GetMapping("/TableNumber")
    public Collection<TableNumber> TableNumbers() {
        return tableNumberRepository.findAll().stream().collect(Collectors.toList());
    }

}
