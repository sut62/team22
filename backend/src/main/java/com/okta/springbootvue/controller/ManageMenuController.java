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
  private final ManageMenuRepository ManageMenuRep;
  @Autowired
  private MenuCategoryRepository MenuCategoryRep;
  @Autowired
  private MenuIngredientRepository MenuIngredientRep;
  @Autowired
  private MenuTypeRepository MenuTypeRep;

  ManageMenuController(ManageMenuRepository ManageMenuRep) {
    this.ManageMenuRep = ManageMenuRep;
  }

  @GetMapping("/manageMenus")
  public Collection<ManageMenu> ManageMenus() {
    return ManageMenuRep.findAll().stream().collect(Collectors.toList());
  }

  @PostMapping("/manageMenus/{mname}/{mprice}/{categoryid}/{ingredientid}/{typeid}")
  public ManageMenu newManageMenu(ManageMenu newMenu,
  @PathVariable String mname,
  @PathVariable Integer mprice,
  @PathVariable long categoryid,
  @PathVariable long ingredientid,
  @PathVariable long typeid
  ) {

    MenuCategory selectCategory = MenuCategoryRep.findById(categoryid);
    MenuIngredient selectIngredient = MenuIngredientRep.findById(ingredientid);
    MenuType selectType = MenuTypeRep.findById(typeid);

    newMenu.setMname(mname);
    newMenu.setMprice(mprice);
    newMenu.setSel_category(selectCategory);
    newMenu.setSel_ingredient(selectIngredient);
    newMenu.setSel_type(selectType);

    return ManageMenuRep.save(newMenu);
  }
}
