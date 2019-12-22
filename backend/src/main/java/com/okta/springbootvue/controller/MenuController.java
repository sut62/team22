package com.okta.springbootvue.controller;

import com.okta.springbootvue.entity.Menu;
import com.okta.springbootvue.repository.MenuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MenuController {

    @Autowired
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping("/Menu")
    public Collection<Menu> Menus() {
        return menuRepository.findAll().stream().collect(Collectors.toList());
    }

}