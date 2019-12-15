package main.java.com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.services;
import com.okta.springbootvue.repository.ServicesRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ServiesController {

    @Autowired
    private final ServicesRepository ServicesRepository;

    public ServiesController(ServicesRepository ServicesRepository) {
        this.ServicesRepository = ServicesRepository;
    }

    @GetMapping("/services")
    public Collection<services> services() {
        return ServiesController.findAll().stream().collect(Collectors.toList());
    }

}