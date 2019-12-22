package com.okta.springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderFoodController {
    @Autowired
    private final OrderFoodRepository orderfoodRepository;
    @Autowired
    private TablesRepository tablenumberRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DishSizeRepository dishsizeRepository;

    OrderFoodController(OrderFoodRepository orderfoodRepository) {
        this.orderfoodRepository = orderfoodRepository;
    }

    @GetMapping("/Order")
    public Collection<OrderFood> OrderFoods() {
        return orderfoodRepository.findAll().stream().collect(Collectors.toList());

    }
    
    @PostMapping("/Order/{tablenumber_id}/{menu_id}/{dishsize_id}")
    public OrderFood newOrder(OrderFood newOrderFood,
    @PathVariable long tablenumber_id,
    @PathVariable long menu_id,
    @PathVariable long dishnumber,
    @PathVariable long dishsize_id) {

    TableNumber tablenumber = tablenumberRepository.findById(tablenumber_id);
    Menu menu = menuRepository.findById(menu_id);
    DishSize dishsize = dishsizeRepository.findById(dishsize_id);   

   
    newOrderFood.setTablenumber(tablenumber);
    newOrderFood.setMenu(menu);
    newOrderFood.setDishnumber(dishnumber);
    newOrderFood.setDishsize(dishsize);
    

    return orderfoodRepository.save(newOrderFood); //บันทึก Objcet ชื่อ Delivery
    
    }
}