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
    private final MenuIngredientRepository MenuIngredientRep;

    public MenuIngredientController(MenuIngredientRepository MenuIngredientRep) {
        this.MenuIngredientRep = MenuIngredientRep;
    }

    @GetMapping("/menuIngredients")
    public Collection<MenuIngredient> MenuIngredients() {
        return MenuIngredientRep.findAll().stream().collect(Collectors.toList());
    }

}
