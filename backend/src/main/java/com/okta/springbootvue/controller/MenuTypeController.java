package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.MenuType;
import com.okta.springbootvue.repository.MenuTypeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MenuTypeController {

    @Autowired
    private final MenuTypeRepository TypeRep;

    public MenuTypeController(MenuTypeRepository TypeRep) {
        this.TypeRep = TypeRep;
    }

    @GetMapping("/menuTypes")
    public Collection<MenuType> MenuTypes() {
        return TypeRep.findAll().stream().collect(Collectors.toList());
    }

}
