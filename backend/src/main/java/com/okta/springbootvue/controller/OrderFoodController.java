package com.okta.springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.net.URLDecoder;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OrderFoodController {
    @Autowired
    private final OrderFoodRepository orderfoodRepository;
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private ManageMenuRepository managemenuRepository;
    @Autowired
    private OrderTypeRepository ordertypeRepository;
    @Autowired
    private OrderStatusRepository orderstatusRepository;

    OrderFoodController(OrderFoodRepository orderfoodRepository) {
        this.orderfoodRepository = orderfoodRepository;
    }

    @GetMapping("/Order")
    public Collection<OrderFood> OrderFoods() {
        return orderfoodRepository.findAll().stream().collect(Collectors.toList());

    }

    @PutMapping("/Order/{id}/{status}")
    public void updateOrderfood(@PathVariable long id,@PathVariable long status) {
    OrderFood orderid = orderfoodRepository.findById(id);
    OrderStatus status_id = orderstatusRepository.findById(status); 
    orderid.setOrderstatus(status_id);

    orderfoodRepository.save(orderid);
    }

    @DeleteMapping("/Order/{id}")
    public void deleteOrderfood(@PathVariable long id) {
    orderfoodRepository.deleteById(id);
    }

    @PostMapping("/Order/{tables_id}/{menu_id}/{dishquantity}/{ordertype_id}/{status_id}")
    public OrderFood newOrder(OrderFood newOrderFood,
    @PathVariable long tables_id,
    @PathVariable long menu_id,
    @PathVariable Integer dishquantity,
    @PathVariable long ordertype_id,
    @PathVariable long status_id ) {

    

    tables tables = tablesRepository.findById(tables_id);
    Optional<ManageMenu> menu = managemenuRepository.findById(menu_id);
    ManageMenu managemenu = menu.get();
    OrderType ordertype = ordertypeRepository.findById(ordertype_id);
    OrderStatus status = orderstatusRepository.findById(status_id); 

   
    newOrderFood.setTables(tables);
    newOrderFood.setManagemenu(managemenu);
    newOrderFood.setDishquantity(dishquantity);
    newOrderFood.setOrdertype(ordertype);
    newOrderFood.setOrderstatus(status);
    
    

    return orderfoodRepository.save(newOrderFood);
    
    }
}
