package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.DateValidator;

@DataJpaTest
class ReservationTestCase {
<<<<<<< HEAD
  private Validator validator;
  private DateValidator dateValidator;

  @Autowired
  private ReservationsRepository reservationsRepository;



  @BeforeEach
  public void setup(){
    dateValidator = new DateValidator();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  //save success
  @Test
  void B6015145_testResercationSaveSuccess() {
    reservations res = new reservations();
    LocalDateTime datetime = LocalDateTime.parse("2020-04-04"+"T"+"14:14:10");
    res.setReservedateandtime(datetime);
    res.setReserveseats(4);


    reservationsRepository.saveAndFlush(res);

    reservations rev = reservationsRepository.findById(res.getId());
    assertEquals(datetime, rev.getReservedateandtime());
    assertEquals(4, rev.getReserveseats());


  }

  //Date is not LocalDateTime format
  @Test
  void B6015145_testDateIsNotLocaDateTimeFormat(){
    reservations res = new reservations();
    String date = "2020-00-04T13:12:22";
    assertFalse(dateValidator.isThisDateValid(date));






  }
  //Seat is not positive number
  @Test
  void B6015145_testSeatMustBePositiveNumber(){
    reservations res = new reservations();
    LocalDateTime datetime = LocalDateTime.parse("2020-04-04"+"T"+"14:14:10");
    res.setReservedateandtime(datetime);
    res.setReserveseats(-1); //negative number
    Set<ConstraintViolation<reservations>> result = validator.validate(res);

    //must has 1 error
    assertEquals(1, result.size());
    //error message and path must be correct
    ConstraintViolation<reservations> vi = result.iterator().next();
    assertEquals("Seat must be positive number", vi.getMessage());
    assertEquals("reserveseats", vi.getPropertyPath().toString());

  }
=======
    private Validator validator;
    private DateValidator dateValidator;

	@Autowired
    private ReservationsRepository reservationsRepository;

    
    

	@BeforeEach
	public void setup(){
        dateValidator = new DateValidator();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }    
    
    //save success
	@Test
	void B6015145_testResercationSaveSuccess() {
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.parse("2020-04-04"+"T"+"14:14:10");
        res.setReservedateandtime(datetime);
        res.setReserveseats(4);
        

        reservationsRepository.saveAndFlush(res);

       reservations rev = reservationsRepository.findById(res.getId());
        assertEquals(datetime, rev.getReservedateandtime());
        assertEquals(4, rev.getReserveseats());


    }
    
    //Seat is not positive number
    @Test
    void B6015145_testSeatMustBePositiveNumber(){
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.parse("2020-04-04"+"T"+"14:14:10");
        res.setReservedateandtime(datetime);
        res.setReserveseats(-1); //negative number
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //must has 1 error
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        assertEquals("Seat must be positive number", vi.getMessage());
        assertEquals("reserveseats", vi.getPropertyPath().toString());

    }
    //Seat must not be null
    @Test
    void B6015145_testSeatMustNotBeNull(){
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.parse("2020-04-04"+"T"+"14:14:10");
        res.setReservedateandtime(datetime);
        res.setReserveseats(null);//null 
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //must has 1 error
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        assertEquals("must not be null", vi.getMessage());
        assertEquals("reserveseats", vi.getPropertyPath().toString());
    }
    //TimeandDate must not be present or past
    @Test
    void B6015145_testLocalTimeDateMustNotBePresentOrPast(){
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.parse("2010-04-04"+"T"+"14:14:10");
        res.setReservedateandtime(datetime);
        res.setReserveseats(5);
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //must has 1 error
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        assertEquals("Must not be present or past", vi.getMessage());
        assertEquals("reservedateandtime", vi.getPropertyPath().toString());
    }
<<<<<<< HEAD
>>>>>>> 37da935e842e5c3b694f0ff1b9a7d33a08c6a959
=======
>>>>>>> 37da935e842e5c3b694f0ff1b9a7d33a08c6a959

}
