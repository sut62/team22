package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.entity.MenuIngre;
import com.okta.springbootvue.repository.MenuIngreRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MenuIngreController {

  @Autowired
  private final MenuIngreRepository IngreRep;

  public MenuIngreController(MenuIngreRepository IngreRep) {
    this.IngreRep = IngreRep;
  }

  @GetMapping("/menuIngres")
  public Collection<MenuIngre> MenuIngres() {
    return IngreRep.findAll().stream().collect(Collectors.toList());
  }

}
