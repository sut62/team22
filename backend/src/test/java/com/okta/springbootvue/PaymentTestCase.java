package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;

@DataJpaTest
public class PaymentTestCase {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PrefixRepository prefixRepository;
    @Autowired
    private MemTypeRepository memTypeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Marital_StatusRepository marital_StatusRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TablesRepository tablesRepository;
   

    private Prefix prefix = new Prefix();
    private MemType memType = new MemType();
    private Gender gender = new Gender();
    private Position position = new Position();
    private Marital_Status marital_Status = new Marital_Status();
    private Employee employee = new Employee();
    private Member member = new Member();
    private tables tab = new tables();

    @BeforeEach
    public void setup() throws ParseException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
       
       
        tab.setSeats(4);
        tablesRepository.saveAndFlush(tab);
        prefix.setName("นาย");
        prefixRepository.saveAndFlush(prefix);

        
        memType.setName("VIP");
        memTypeRepository.saveAndFlush(memType);

        
        gender.setName("ชาย");
        genderRepository.saveAndFlush(gender);

        
        position.setName("พนักงานตำแหน่งหน้าเคาน์เตอร์");
        positionRepository.saveAndFlush(position);

        
        marital_Status.setName("โสด");
        marital_StatusRepository.saveAndFlush(marital_Status);

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dates = "1999-11-10";
        Date tens = new Date();
        employee.setE_name("Phonlaphat Namthong");
        employee.setE_TEL("0879267920");
        employee.setE_ADDRESS("Angthong");
        employee.setE_BIRTH(dateFormat.parse(dates));
        employee.setE_NUM("1179900377114");
        employee.setE_REGDATE(tens);
        employee.setGender(gender);
        employee.setPosition(position);
        employee.setMarital_Status(marital_Status);
        employeeRepository.saveAndFlush(employee);
        
        member.setName("สรสิช อิ่มวิเศษ");
        member.setTel("0952259191");
        member.setBirth(dateFormat.parse(dates));
        member.setMail("moosorasich@hotmail.com");
        member.setSaveDate(tens);
        member.setSelect_prefix(prefix);
        member.setSelect_gender(gender);
        member.setSelect_employee(employee);
        member.setSelect_memtype(memType);
        
        member = memberRepository.saveAndFlush(member);
        


    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6010669_testPaymentSaveSuccess() {
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(5.00);
        payment.setTotal((double)29.00);
        payment.setChange(25.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        payment = paymentRepository.saveAndFlush(payment);

        Optional<Payment>  found = paymentRepository.findById(payment.getId());
        assertEquals(5.00, found.get().getMoney());
        assertEquals(29.00, found.get().getTotal());
        assertEquals(25.00, found.get().getChange());
        assertEquals(datetime ,found.get().getCreateDate());
        assertEquals(employee, found.get().getSelectemployee());
        assertEquals(member, found.get().getSelectmember());
        assertEquals(tab, found.get().getSelecttable());
    }

    

    
    @Test
    void B6010669_testPaymentDataMoneyNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(null);
        payment.setTotal((double)29.00);
        payment.setChange(25.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Money", v.getPropertyPath().toString());

    }
    @Test
    void B6010669_testPaymentDataTotalNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(29.00);
        payment.setTotal(null);
        payment.setChange(25.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Total", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataChangeNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(null);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Change", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataChangeNegativeCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(-5.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not below 0", v.getMessage());
        assertEquals("Change", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataTotalNegativeCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(25.00);
        payment.setTotal((double)-29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not below 0", v.getMessage());
        assertEquals("Total", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataMoneyNegativeCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(-25.00);
        payment.setTotal((double)29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not below 0", v.getMessage());
        assertEquals("Money", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataDateFutureCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be future", v.getMessage());
        assertEquals("createDate", v.getPropertyPath().toString());
        
    }
    @Test
    void B6010669_testPaymentDataEmployeeNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(null);
        payment.setSelectmember(member);
        payment.setSelecttable(tab);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("selectemployee", v.getPropertyPath().toString());

    }
    
    

    @Test
    void B6010669_testPaymentDataTableNullCase(){
        Payment  payment = new Payment();
        LocalDateTime datetime = LocalDateTime.now();
        payment.setMoney(25.00);
        payment.setTotal((double)29.00);
        payment.setChange(5.00);
        payment.setCreateDate(datetime);
        payment.setSelectemployee(employee);
        payment.setSelectmember(member);
        payment.setSelecttable(null);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("selecttable", v.getPropertyPath().toString());

    }

    
    

    
}
