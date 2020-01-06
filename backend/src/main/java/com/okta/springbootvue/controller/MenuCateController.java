package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import com.okta.springbootvue.entity.MenuCate;
import com.okta.springbootvue.repository.MenuCateRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MenuCateController {

    @Autowired
    private final MenuCateRepository CateRep;

    public MenuCateController(MenuCateRepository CateRep) {
        this.CateRep = CateRep;
    }

    @GetMapping("/menuCates")
    public Collection<MenuCate> MenuCates() {
        return CateRep.findAll().stream().collect(Collectors.toList());
    }

}
