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


  @Autowired
  private MemberRepository memberRepository;



  @BeforeEach
  public void setup(){
  
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
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

    member = memberRepository.saveAndFlush(member);

    Optional<Member> found = memberRepository.findById(member.getId());
    assertEquals("สรสิช อิ่มวิเศษ", found.get().getName());
    assertEquals("0952259191", found.get().getTel());
    assertEquals(dateFormat.parse(date), found.get().getBirth());
    assertEquals("moosorasich@hotmail.com", found.get().getMail());
    assertEquals(ten, found.get().getSaveDate());
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
    Set<ConstraintViolation<Member>> result = validator.validate(member);

    assertEquals(1, result.size());

    ConstraintViolation<Member> vi = result.iterator().next();
    assertEquals("must be a past date", vi.getMessage());
    assertEquals("birth", vi.getPropertyPath().toString());
  }  

  
}