package com.okta.springbootvue;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.constraints.*;
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

  // ==================== Main Test ====================

  // --------- AddMenuSuccess ----------

  @Test
  void B6027315_testSuccessByRecievingAllData() {
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name("Tom Yum Kung");
    menu1.setM_price(12);

    menu1 = ManageMenuRep.saveAndFlush(menu1);

    Optional<ManageMenu> found = ManageMenuRep.findById(menu1.getMnid());
    assertEquals("Tom Yum Kung", found.get().getM_name());
    assertEquals(12, found.get().getM_price());
  }

  // ==================== FAILED CASES ====================

  // ==================== Menu Name ====================

  // ---------- AddMenuFailed by Name is null ----------

  @Test
  void B6027315_testFailedByNameIsNull() {
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name(null);
    menu1.setM_price(12);

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(menu1);

    // result ต้องมี error 1 ค่าเท่านั้น
    assertEquals(1, result.size());

    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must not be null", v.getMessage());
    assertEquals("m_name", v.getPropertyPath().toString());
  }

  // ---------- Menu Name Max Min Size ----------

  @Test
  void B6027315_testFailedByNameIsLongerThan30() {
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGH");
    menu1.setM_price(12);

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(menu1);

    assertEquals(1, result.size());

    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must match \".{1,30}\"", v.getMessage());
    assertEquals("m_name", v.getPropertyPath().toString());
  }

  // ---------- Menu Name Must be unique ----------

  @Test
  void B6027315_testFailedByNameIsUnique() {
    // สร้าง person object
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name("Tom Yum Kung");
    menu1.setM_price(12);
    menu1 = ManageMenuRep.saveAndFlush(menu1);

    // คาดหวังว่า DataIntegrityViolationException จะถูก throw
    assertThrows(DataIntegrityViolationException.class, () -> {
      // สร้าง person object ตัวที่ 2
      ManageMenu menu2 = new ManageMenu();
      menu2.setM_name("Tom Yum Kung");
      menu2.setM_price(12);
      menu2 = ManageMenuRep.saveAndFlush(menu2);
    });

  }

  // ==================== Menu Price ====================

  // ---------- Add Menu Failed by Price is null ----------

  @Test
  void B6027315_testFailedByPriceIsNull() {
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name("Tom Yum Kung");
    menu1.setM_price(null);

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(menu1);

    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must not be null", v.getMessage());
    assertEquals("m_price", v.getPropertyPath().toString());
  }

  // ---------- Add Menu Failed by Price more than 100000 ----------

  @Test
  void B6027315_testFailedByPriceIsMoreThan100000() {
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name("Tom Yum Kung");
    menu1.setM_price(123456);

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(menu1);

    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must less than equal 100000", v.getMessage());
    assertEquals("m_price", v.getPropertyPath().toString());
  }

  // ---------- Add Menu Failed by Input Negative number ----------

  @Test
  void B6027315_testFailedByPriceIsNegativeNumber() {
    ManageMenu menu1 = new ManageMenu();
    menu1.setM_name("Tom Yum Kung");
    menu1.setM_price(-123);

    Set<ConstraintViolation<ManageMenu>> result = validator.validate(menu1);

    assertEquals(1, result.size());
    // error message ตรงชนิด และถูก field
    ConstraintViolation<ManageMenu> v = result.iterator().next();
    assertEquals("must be positive number", v.getMessage());
    assertEquals("m_price", v.getPropertyPath().toString());
  }

}
