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
  @Autowired
  private GenderRepository genderRepository;
  @Autowired
  private PositionRepository positionRepository;
  @Autowired
  private Marital_StatusRepository marital_StatusRepository;

  Gender gender = new Gender();
  Position position = new Position();
  Marital_Status marital_Status = new Marital_Status();

  @BeforeEach
  public void setup(){
    
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    gender.setName("ชาย");
    genderRepository.saveAndFlush(gender);

    position.setName("พนักงานร้านอาหาร");
    positionRepository.saveAndFlush(position);

    marital_Status.setName("โสด");
    marital_StatusRepository.saveAndFlush(marital_Status);

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
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);

    employee = employeeRepository.saveAndFlush(employee);

    Optional<Employee> found = employeeRepository.findById(employee.getId());
    assertEquals("Phonlaphat Namthong", found.get().getE_name());
    assertEquals("0879267920", found.get().getE_TEL());
    assertEquals("Angthong", found.get().getE_ADDRESS());
    assertEquals(dateFormat.parse(date), found.get().getE_BIRTH());
    assertEquals("1179900377114", found.get().getE_NUM());
    assertEquals(ten, found.get().getE_REGDATE());
    assertEquals(20, found.get().getE_AGE());
    assertEquals(gender, found.get().getGender());
    assertEquals(position, found.get().getPosition());
    assertEquals(marital_Status, found.get().getMarital_Status());
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
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);

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
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);
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
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);
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
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);
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
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must be a past date", vi.getMessage());
    assertEquals("E_BIRTH", vi.getPropertyPath().toString());
  }

  @Test
  
  void B6010799_EmployeeAgeMoreThanZero() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS("ANGTHONG");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(ten);
    employee.setE_AGE(-1);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must be greater than or equal to 0", vi.getMessage());
    assertEquals("E_AGE", vi.getPropertyPath().toString());
  }

  @Test
  void B6010799_testGenderSuccessTest(){
      Gender gender = new Gender();
      gender.setName("กะเทย");

      genderRepository.saveAndFlush(gender);

      Optional<Gender>  found = genderRepository.findById(gender.getId());
      assertEquals("กะเทย", found.get().getName());
     
  }

  @Test
  void B6010799_testGenderNullTest(){
    Gender gender = new Gender();
    gender.setName(null);

      Set<ConstraintViolation<Gender>> result = validator.validate(gender);

      assertEquals(1, result.size());

      ConstraintViolation<Gender> v = result.iterator().next();
      assertEquals("must not be null", v.getMessage());
      assertEquals("name", v.getPropertyPath().toString());

  }
  
  @Test
  void B6010799_testMarital_StatusSuccessTest(){
      Marital_Status marital_Status = new Marital_Status();
      marital_Status.setName("อย่าร้าง");

      marital_StatusRepository.saveAndFlush(marital_Status);

      Optional<Marital_Status>  found = marital_StatusRepository.findById(marital_Status.getId());
      assertEquals("อย่าร้าง", found.get().getName());
     
  }

  @Test
  void B6010799_testMarital_StatusNullTest(){
    Marital_Status marital_Status = new Marital_Status();
    marital_Status.setName(null);

      Set<ConstraintViolation<Marital_Status>> result = validator.validate(marital_Status);

      assertEquals(1, result.size());

      ConstraintViolation<Marital_Status> v = result.iterator().next();
      assertEquals("must not be null", v.getMessage());
      assertEquals("name", v.getPropertyPath().toString());

  }

  @Test
  void B6010799_testPositionSuccessTest(){
      Position position = new Position();
      position.setName("พนักงานล้างจาน");

      positionRepository.saveAndFlush(position);

      Optional<Position>  found = positionRepository.findById(position.getId());
      assertEquals("พนักงานล้างจาน", found.get().getName());
     
  }

  @Test
  void B6010799_testPositionNullTest(){
    Position position = new Position();
    position.setName(null);

      Set<ConstraintViolation<Position>> result = validator.validate(position);

      assertEquals(1, result.size());

      ConstraintViolation<Position> v = result.iterator().next();
      assertEquals("must not be null", v.getMessage());
      assertEquals("name", v.getPropertyPath().toString());

  }

  void B6010799_EmployeeREGDATENotBeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Employee employee = new Employee();
    employee.setE_name("Phonlaphat Namthong");
    employee.setE_TEL("0879267920");
    employee.setE_ADDRESS("Angthong");
    employee.setE_BIRTH(dateFormat.parse(date));
    employee.setE_NUM("1179900377114");
    employee.setE_REGDATE(null);
    employee.setE_AGE(20);
    employee.setGender(gender);
    employee.setPosition(position);
    employee.setMarital_Status(marital_Status);
    Set<ConstraintViolation<Employee>> result = validator.validate(employee);

    assertEquals(1, result.size());

    ConstraintViolation<Employee> vi = result.iterator().next();

    assertEquals("must not be null", vi.getMessage());
    assertEquals("E_REGDATE", vi.getPropertyPath().toString());
  }
}
