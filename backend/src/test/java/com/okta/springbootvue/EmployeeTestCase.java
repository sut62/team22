package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.json.JsonParseException;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat; 


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;


@DataJpaTest
class EmployeeTestCase {

  private Validator validator;
 

  @Autowired
  private EmployeeRepository employeeRepository;



  @BeforeEach
  public void setup(){
    
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
  @Test 
  
  void B6010799_addEmployeeSuccessTest() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS("Angthong");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(ten);

    employee = employeeRepository.saveAndFlush(employee);

    Optional<Employee> found = employeeRepository.findById(employee.getId());
    assertEquals("Phonlaphat Namthong", found.get().getE_name());
    assertEquals("0879267920", found.get().getE_TEL());
    assertEquals("Angthong", found.get().getE_ADDRESS());
    assertEquals(dateFormat.parse(date), found.get().getE_BIRTH());
    assertEquals("1179900377114", found.get().getE_NUM());
    assertEquals(ten, found.get().getE_REGDATE());
  }

  

  @Test 
  
  void B6010799_EmployeeTelMustBe10Digit() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("000005151515151515");
    employee.setE_ADDRESS("Angthong");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(ten);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must match \"\\d{10}\"", vi.getMessage());
    assertEquals("E_TEL", vi.getPropertyPath().toString());
  }

  @Test
  
  void B6010799_EmployeeNumMustBe13Digit() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS("Angthong");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("117");
    employee.setE_REGDATE(ten);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must match \"\\d{13}\"", vi.getMessage());
    assertEquals("E_NUM", vi.getPropertyPath().toString());
  }

  @Test 
  
  void B6010799_EmployeeNameMustBetween10and50() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS("Angthong");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(ten);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must match \"\\D{1,50}\"", vi.getMessage());
    assertEquals("e_name", vi.getPropertyPath().toString());
  }

  @Test
  
  void B6010799_EmployeeADDRESSNotBeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS(null);
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(ten);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must not be null", vi.getMessage());
    assertEquals("E_ADDRESS", vi.getPropertyPath().toString());
  }

  @Test
  
  void B6010799_EmployeeBirthNotPresentOrFuture() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "2050-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS("ANGTHONG");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(ten);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must be a past date", vi.getMessage());
    assertEquals("E_BIRTH", vi.getPropertyPath().toString());
  }

  
}
