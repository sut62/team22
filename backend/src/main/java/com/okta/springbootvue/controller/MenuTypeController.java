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
    private final MenuTypeRepository MenuTypeRepository;

    public MenuTypeController(MenuTypeRepository MenuTypeRepository) {
        this.MenuTypeRepository = MenuTypeRepository;
    }

    @GetMapping("/MenuTypes")
    public Collection<MenuType> MenuTypes() {
        return MenuTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}
