package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.MenuCategory;
import com.okta.springbootvue.repository.MenuCategoryRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MenuCategoryController {

    @Autowired
    private final MenuCategoryRepository MenuCategoryRepository;

    public MenuCategoryController(MenuCategoryRepository MenuCategoryRepository) {
        this.MenuCategoryRepository = MenuCategoryRepository;
    }

    @GetMapping("/MenuCategorys")
    public Collection<MenuCategory> MenuCategorys() {
        return MenuCategoryRepository.findAll().stream().collect(Collectors.toList());
    }

}
