package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.MenuIngredient;
import com.okta.springbootvue.repository.MenuIngredientRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MenuIngredientController {

    @Autowired
    private final MenuIngredientRepository MenuIngredientRepository;

    public MenuIngredientController(MenuIngredientRepository MenuIngredientRepository) {
        this.MenuIngredientRepository = MenuIngredientRepository;
    }

    @GetMapping("/MenuIngredients")
    public Collection<MenuIngredient> MenuIngredients() {
        return MenuIngredientRepository.findAll().stream().collect(Collectors.toList());
    }

}
