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
import java.time.LocalDateTime;
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
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(1.00);
        payment.setTotal((double)29.00);
        payment.setChange(25.00);
        payment.setCreateDate(datetime);

        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment>  found = paymentRepository.findById(payment.getId());
        assertEquals(1, found.get().getMoney());
    }

    

    
    @Test
    void B6010669_testPaymentDataMoneyNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(null);
        payment.setTotal((double)29.00);
        payment.setChange(25.00);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Money", v.getPropertyPath().toString());

    }
    @Test
    void B6010669_testPaymentDataTotalNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(29.00);
        payment.setTotal(null);
        payment.setChange(25.00);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Total", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataChangeNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(null);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Change", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataChangeNegativeCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(-5.00);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not below 0", v.getMessage());
        assertEquals("Change", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataTotalNegativeCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(25.00);
        payment.setTotal((double)-29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not below 0", v.getMessage());
        assertEquals("Total", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataMoneyNegativeCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        payment.setMoney(-25.00);
        payment.setTotal((double)29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not below 0", v.getMessage());
        assertEquals("Money", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataDateFutureCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.parse("2020-04-14"+"T"+"14:14:10");
        payment.setMoney(25.00);
        payment.setTotal((double)29);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be future", v.getMessage());
        assertEquals("createDate", v.getPropertyPath().toString());
        
    }
   
    
    

    
}
