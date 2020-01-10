package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.OrderType;
import com.okta.springbootvue.repository.OrderTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderTypeController {

    @Autowired
    private final OrderTypeRepository ordertypeRepository;

    public OrderTypeController(OrderTypeRepository ordertypeRepository) {
        this.ordertypeRepository = ordertypeRepository;
    }

    @GetMapping("/OrderType")
    public Collection<OrderType> OrderTypes() {
        return ordertypeRepository.findAll().stream().collect(Collectors.toList());
    }

}