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
  private MenuCateRepository CateRep;
  @Autowired
  private MenuIngreRepository IngreRep;
  @Autowired
  private MenuTypeRepository TypeRep;

  ManageMenuController(ManageMenuRepository ManageMenuRep) {
    this.ManageMenuRep = ManageMenuRep;
  }

  @GetMapping("/manageMenus")
  public Collection<ManageMenu> ManageMenus() {
    return ManageMenuRep.findAll().stream().collect(Collectors.toList());
  }

  @PostMapping("/manageMenus/{m_name}/{m_price}/{cate_name}/{ingre_name}/{type_name}")
  public ManageMenu newMenu(ManageMenu newMenu,

  @PathVariable String m_name,
  @PathVariable String m_price,
  @PathVariable long cate_name,
  @PathVariable long ingre_name,
  @PathVariable long type_name
  ) {

    MenuCate selectCate = CateRep.findById(cate_name);
    MenuIngre selectIngre = IngreRep.findById(ingre_name);
    MenuType selectType = TypeRep.findById(type_name);

    newMenu.setM_name(m_name);
    newMenu.setM_price(m_price);
    newMenu.setSel_cate(selectCate);
    newMenu.setSel_ingre(selectIngre);
    newMenu.setSel_type(selectType);

    return ManageMenuRep.save(newMenu);
  }
}
