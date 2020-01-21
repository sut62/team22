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
public class PaymentTestCase {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010669_testPaymentSaveSuccess() {
        Payment  payment = new Payment();
        payment.setStatusname(1);

        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment>  found = paymentRepository.findById(payment.getId());
        assertEquals(1, found.get().getStatusname());
    }

    @Test
    void B6010669_testPaymentpositivenumberCase(){
        Payment  payment = new Payment();
        payment.setStatusname(-1);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must be positive number", v.getMessage());
        assertEquals("statusname", v.getPropertyPath().toString());

    }

    
    @Test
    void B6010669_testPaymentDataNullCase(){
        Payment  payment = new Payment();
        payment.setStatusname(null);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("statusname", v.getPropertyPath().toString());

    }
    
    @Test
    void B6010669_testPaymentMaxNumberCase(){
        Payment  payment = new Payment();
        payment.setStatusname(1000);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be more 100", v.getMessage());
        assertEquals("statusname", v.getPropertyPath().toString());

    }

    
}
