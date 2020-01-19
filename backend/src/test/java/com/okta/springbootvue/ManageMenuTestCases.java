package com.okta.springbootvue;

import com.okta.springbootvue.entity.ManageMenu;
import com.okta.springbootvue.repository.ManageMenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class ManageMenuTestCases {

  private Validator validator;

  @Autowired
  private ManageMenuRepository ManageMenuRep;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test //Add Menu Success
  void B6027315_addMenuSuccessTest() {
    ManageMenu mn1 = new ManageMenu();
    mn1.setM_name("Tom Yum Kung");
    mn1.setM_price("12");

    mn1 = ManageMenuRep.saveAndFlush(mn1);

    Optional<ManageMenu> found = ManageMenuRep.findById(mn1.getMnid());
    assertEquals("Tom Yum Kung", found.get().getM_name());
    assertEquals("12", found.get().getM_price());
  }

  @Test //Add Menu Failed by null data in textfield
  void B6027315_addMenuFailedTest() {
    ManageMenu mn1 = new ManageMenu();
    mn1.setM_name("Tom Yum Kung");
    mn1.setM_price(null);

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(mn1);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

    // error message ตรงชนิด และถูก field
    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must not be null", v.getMessage());
    assertEquals("m_price", v.getPropertyPath().toString());
  }

  @Test
  void B6027315_nameSizeMaxIs30MinIs1() {
    ManageMenu mn1 = new ManageMenu();
    mn1.setM_name("A123");
    mn1.setM_price("1234");

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(mn1);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must match \"\\D{1,30}\"", v.getMessage());
    assertEquals("m_name", v.getPropertyPath().toString());
  }

  @Test
  void B6027315_priceIsOnlyNumber1To4Digits() {
    ManageMenu mn1 = new ManageMenu();
    mn1.setM_name("Tom Yum Kung");
    mn1.setM_price("12345");

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(mn1);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must match \"\\d{1,4}\"", v.getMessage());
    assertEquals("m_price", v.getPropertyPath().toString());
  }
}
