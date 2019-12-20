package com.cpe.backend.controller;

import com.cpe.backend.entity.DishSize;
import com.cpe.backend.repository.DishSizeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DishSizeController {

    @Autowired
    private final DishSizeRepository dishsizeRepository;

    public DishSizeController(DishSizeRepository dishsizeRepository) {
        this.dishsizeRepository = dishsizeRepository;
    }

    @GetMapping("/DishSize")
    public Collection<DishSize> DishSizes() {
        return dishsizeRepository.findAll().stream().collect(Collectors.toList());
    }

}