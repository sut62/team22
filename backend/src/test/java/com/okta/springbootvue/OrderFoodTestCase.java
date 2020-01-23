package com.okta.springbootvue;

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

import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;

@DataJpaTest
public class OrderFoodTestCase {

    private Validator validator;

    @Autowired
    private OrderFoodRepository orderfoodRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6014292_testOrderFoodSaveSuccess() {
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishnumber(1);

        orderfood = orderfoodRepository.saveAndFlush(orderfood);

        Optional<OrderFood>  found = orderfoodRepository.findById(orderfood.getId());
        assertEquals(1, found.get().getDishnumber());
    }

    @Test
    void B6014292_testOrderFoodPositiveNumberCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishnumber(-1);

        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must be positive number", v.getMessage());
        assertEquals("dishnumber", v.getPropertyPath().toString());

    }

    
    @Test
    void B6014292_testOrderFoodDataNullCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishnumber(null);

        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("dishnumber", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderFoodMaxNumberCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishnumber(1000);

        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be more 100", v.getMessage());
        assertEquals("dishnumber", v.getPropertyPath().toString());

    }

    
}