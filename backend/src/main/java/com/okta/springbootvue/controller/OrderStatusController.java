package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.OrderStatus;
import com.okta.springbootvue.repository.OrderStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderStatusController {

    @Autowired
    private final  OrderStatusRepository orderstatusRepository;

    public OrderStatusController(OrderStatusRepository orderstatusRepository) {
        this.orderstatusRepository = orderstatusRepository;
    }

    @GetMapping("/OrderStatus")
    public Collection<OrderStatus> OrderStatus() {
        return orderstatusRepository.findAll().stream().collect(Collectors.toList());
    }

}