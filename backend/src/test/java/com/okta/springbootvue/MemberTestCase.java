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
class MemberTestCase {

  private Validator validator;
  Prefix prefix = new Prefix();
  MemType memType = new MemType();
  Gender gender = new Gender();
  Position position = new Position();
  Marital_Status marital_Status = new Marital_Status();
  Employee employee = new Employee();


  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private GenderRepository genderRepository;
  @Autowired
  private PositionRepository positionRepository;
  @Autowired
  private Marital_StatusRepository marital_StatusRepository;
  @Autowired
  private PrefixRepository prefixRepository;
  @Autowired
  private MemTypeRepository memTypeRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  

  @BeforeEach
  public void setup()throws ParseException{
  
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    
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
  }
  @Test
  
  void B6010621_addMemberSuccessTest() throws ParseException{
    

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);

    member = memberRepository.saveAndFlush(member);

    Optional<Member> found = memberRepository.findById(member.getId());
    assertEquals("สรสิช อิ่มวิเศษ", found.get().getName());
    assertEquals("0952259191", found.get().getTel());
    assertEquals(dateFormat.parse(date), found.get().getBirth());
    assertEquals("moosorasich@hotmail.com", found.get().getMail());
    assertEquals(ten, found.get().getSaveDate());
    assertEquals(prefix, found.get().getSelect_prefix());
    assertEquals(gender, found.get().getSelect_gender());
    assertEquals(employee, found.get().getSelect_employee());
    assertEquals(memType, found.get().getSelect_memtype());
  }
  
  @Test
  
  void B6010621_MembernameNotBeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName(null);
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("name", vi.getPropertyPath().toString());
  }

  @Test
  
  void B6010621_MemberTelMustBe10Digit() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("095123456789");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();

    assertEquals("must match \"\\d{10}\"", vi.getMessage());
    assertEquals("tel", vi.getPropertyPath().toString());
  }
  
  @Test
  
  void B6010621_MemberEmailTest() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must be a well-formed email address", vi.getMessage());
    assertEquals("mail", vi.getPropertyPath().toString());
  }

  @Test //Add Menu Success
  
  void B6010621_MemberNameMustBetween10and30() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("size must be between 10 and 30", vi.getMessage());
    assertEquals("name", vi.getPropertyPath().toString());
  }  

  @Test //Add Menu Success
  
  void B6010621_MemberBirthMustnotBePresentorFuture() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "2050-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must be a past date", vi.getMessage());
    assertEquals("birth", vi.getPropertyPath().toString());
  } 

  @Test
  void B6010621_ComboboxMemTypeMustNotbeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(null);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("select_memtype", vi.getPropertyPath().toString());
  }  
  @Test
  void B6010621_ComboboxEmployeeMustNotbeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(gender);
    member.setSelect_employee(null);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("select_employee", vi.getPropertyPath().toString());
  }
  @Test
  void B6010621_ComboboxGenderMustNotbeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(prefix);
    member.setSelect_gender(null);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("select_gender", vi.getPropertyPath().toString());
  }
  @Test
  void B6010621_ComboboxPrefixMustNotbeNull() throws ParseException{
      
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = "1999-11-10";
    Date ten = new Date();
    Member member = new Member();
    member.setName("สรสิช อิ่มวิเศษ");
    member.setTel("0952259191");
    member.setBirth(dateFormat.parse(date));
    member.setMail("moosorasich@hotmail.com");
    member.setSaveDate(ten);
    member.setSelect_prefix(null);
    member.setSelect_gender(gender);
    member.setSelect_employee(employee);
    member.setSelect_memtype(memType);
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("select_prefix", vi.getPropertyPath().toString());
  }
  @Test
  void B6010621_MemTypeCorrect() {
    MemType memType1 = new MemType();
    memType1.setName("ทั่วไป");

    memType1 = memTypeRepository.saveAndFlush(memType1);

    Optional<MemType> found = memTypeRepository.findById(memType1.getId());
    assertEquals("ทั่วไป", found.get().getName());

  }
  @Test
  void B6010621_MemTypeInCorrect() {
    MemType memType1 = new MemType();
    memType1.setName(null);

    Set<ConstraintViolation<MemType>> result = validator.validate(memType1);

    assertEquals(1, result.size());

    ConstraintViolation<MemType> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("name", vi.getPropertyPath().toString());

  }
  @Test
  void B6010621_PrefixCorrect() {
    Prefix prefix1 = new Prefix();
    prefix1.setName("นาง");

    prefix1 = prefixRepository.saveAndFlush(prefix1);

    Optional<Prefix> found = prefixRepository.findById(prefix1.getId());
    assertEquals("นาง", found.get().getName());
    
  }
  @Test
  void B6010621_PrefixInCorrect() {
    Prefix prefix1 = new Prefix();
    prefix1.setName(null);

    Set<ConstraintViolation<Prefix>> result = validator.validate(prefix1);

    assertEquals(1, result.size());

    ConstraintViolation<Prefix> vi = result.iterator().next();
    assertEquals("must not be null", vi.getMessage());
    assertEquals("name", vi.getPropertyPath().toString());

  }
}