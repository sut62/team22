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
import java.util.Date;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ManageMenuController {
  @Autowired
  private final ManageMenuRepository ManageMenuRepository;
  @Autowired
  // private MenuCategoryRepository MenuCategoryRepository;
  // @Autowired
  // private MenuIngredientRepository MenuIngredientRepository;
  // @Autowired
  // private MenuTypeRepository MenuTypeRepository;

  ManageMenuController(ManageMenuRepository ManageMenuRepository) {
    this.ManageMenuRepository = ManageMenuRepository;
  }

  // @GetMapping("/ManageMenus")
  // public Collection<ManageMenu> ManageMenus() {
  //   return ManageMenuRepository.findAll().stream().collect(Collectors.toList());
  // }

  // @PostMapping("/ManageMenus/{patient_id}/{foodmenu_id}/{sendtime_id}")
  // public ManageMenu newManageMenu(ManageMenu newManageMenu,
  // @PathVariable long patient_id,
  // @PathVariable long foodmenu_id,
  // @PathVariable long sendtime_id
  // ) {
  //
  //   Patient selectPatient = PatientRepository.findById(patient_id);
  //   FoodMenu selectFood = FoodMenuRepository.findById(foodmenu_id);
  //   SendTime selectTime = SendTimeRepository.findById(sendtime_id);
  //
  //   newManageMenu.setSelectPatient(selectPatient);
  //   newManageMenu.setSelectFood(selectFood);
  //   newManageMenu.setSelectTime(selectTime);
  //   newManageMenu.setCreateDate(new Date());
  //   return ManageMenuRepository.save(newManageMenu); //บันทึก Objcet ชื่อ VideoRental
  // }
}
