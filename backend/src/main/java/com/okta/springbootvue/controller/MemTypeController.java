package com.cpe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.entity.MemType;
import com.cpe.backend.repository.MemTypeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MemTypeController {

    @Autowired
    private final MemTypeRepository memtypeRepository;

    public MemTypeController(MemTypeRepository memtypeRepository) {
        this.memtypeRepository = memtypeRepository;
    }

    @GetMapping("/memtypes")
    public Collection<MemType> MemTypes() {
        return memtypeRepository.findAll().stream().collect(Collectors.toList());
    }

}