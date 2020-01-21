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

        //must has 1 errors
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
    //TimeandDate must not be past
    @Test
    void B6015145_testLocalTimeDateMustNotBePast(){
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
    //TimeandDate must not be present
    @Test
    void B6015145_testLocalTimeDateMustNotBePresent(){
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.now();
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
    //seat number must not exceed 8
    @Test
    void B6015145_testSeatMustNotExceed8(){
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.parse("2020-04-04"+"T"+"14:14:10");
        res.setReservedateandtime(datetime);
        res.setReserveseats(9);//more than 8 
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //must has 1 error 
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        
        assertEquals("Seat must not be more than 8", vi.getMessage());
        assertEquals("reserveseats", vi.getPropertyPath().toString());

    }
    
    //table seat must not exceed 8
    @Test
    void B6015145_testTableSeatMustNotExceed8(){
        tables tab = new tables();
       
        tab.setSeats(9);//more than 8 
        Set<ConstraintViolation<tables>> result = validator.validate(tab);

        //must has 1 error 
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<tables> vi = result.iterator().next();
        
        assertEquals("Seat must not be more than 8", vi.getMessage());
        assertEquals("Seats", vi.getPropertyPath().toString());

    }
    //table seat must not be null
    void B6015145_testTableSeatMustNotBeNull(){
        tables tab = new tables();
        
        tab.setSeats(null);//null 
        Set<ConstraintViolation<tables>> result = validator.validate(tab);

        //must has 1 error 
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<tables> vi = result.iterator().next();
        
        assertEquals("must not be null", vi.getMessage());
        assertEquals("Seats", vi.getPropertyPath().toString());
    }
    //table seat must not be negative number
    @Test
    void B6015145_testTableSeatMustBePositiveNumber(){
        tables tab = new tables();
        
        tab.setSeats(-1); //negative number
        Set<ConstraintViolation<tables>> result = validator.validate(tab);

        //must has 1 errors
        assertEquals(1, result.size());
        //error message and path must be correct
        ConstraintViolation<tables> vi = result.iterator().next();
        
        assertEquals("Seat must be positive number", vi.getMessage());
        assertEquals("Seats", vi.getPropertyPath().toString());

    }

    //service must not be blank
    @Test
    void B6015145_testServiceMustNotBeBlank(){
        services serv = new services();

        serv.setServiceName(" ");
        Set<ConstraintViolation<services>> result = validator.validate(serv);

        //must be 1 error
        assertEquals(1, result.size());

        //error message and path must be correct
        ConstraintViolation<services> vi = result.iterator().next();
        assertEquals("SERVICES can not be null or blank", vi.getMessage());
        assertEquals("ServiceName", vi.getPropertyPath().toString());
    }

    //service must not be null
    @Test
    void B6015145_testServiceMustNotBeNull(){
        services serv = new services();

        serv.setServiceName(null);
        Set<ConstraintViolation<services>> result = validator.validate(serv);

        //must be 1 error
        assertEquals(1, result.size());

        //error message and path must be correct
        ConstraintViolation<services> vi = result.iterator().next();
        assertEquals("SERVICES can not be null or blank", vi.getMessage());
        assertEquals("ServiceName", vi.getPropertyPath().toString());
    }

    //service must not be number
    @Test
    void B6015145_testServiceMustNotBeNumber(){
        services serv = new services();

        serv.setServiceName("0");
        Set<ConstraintViolation<services>> result = validator.validate(serv);

        //must be 1 error
        assertEquals(1, result.size());

        //error message and path must be correct
        ConstraintViolation<services> vi = result.iterator().next();
        assertEquals("Must be Character", vi.getMessage());
        assertEquals("ServiceName", vi.getPropertyPath().toString());
    }
}
