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
import java.util.Date;

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
        payment.setMoney(1);
        payment.setTotal(29);
        payment.setChange((double)25);
        paymnet.setCreateDate(new Date());

        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment>  found = paymentRepository.findById(payment.getId());
        assertEquals(1, found.get().getMoney());
    }

    

    
    @Test
    void B6010669_testPaymentDataMoneyNullCase(){
        Payment  payment = new Payment();
        payment.setMoney(null);
        payment.setTotal((double)29);
        payment.setChange(25);
        paymnet.setCreateDate(new Date());

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Money", v.getPropertyPath().toString());

    }
    @Test
    void B6010669_testPaymentDataTotalNullCase(){
        Payment  payment = new Payment();
        payment.setMoney(29);
        payment.setTotal(null);
        payment.setChange((double)25);
        paymnet.setCreateDate(new Date());

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Total", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataChangeNullCase(){
        Payment  payment = new Payment();
        payment.setMoney(25);
        payment.setTotal((double)29);
        payment.setChange(null);
        paymnet.setCreateDate(new Date());

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Change", v.getPropertyPath().toString());
        
    }
   
    
    

    
}
