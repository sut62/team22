package com.MillRsDevide.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;

@DataJpaTest
class ReservationTestCase {
	private Validator validator;

	@Autowired
    private ReservationsRepository reservationsRepository;
    
    

	@BeforeEach
	public void setup(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        // //create tables
        // tables tab = new tables();
        // tab.setSeats(4);
        // tablesRepository.save(tab);

        // //create services
        // services ser = new services();
        // ser.setServiceName("Regular");
        // servicesRepository.save(ser);

        
        // //create members
        // Member mem = new Member();
        //     //create name
        //     mem.setName("Joey");
        //     //create birth
        //     mem.setBirth(new Date());
        //     //create tel
        //     mem.setTel("0911819211");
        //     //create mail
        //     mem.setMail("Mail@mail.com");
        //     //create savedate
        //     mem.setSaveDate(new Date());
        //     //create memtype
        //     MemType met = new MemType();
        //         //create name
        //         met.setName("Student");
        //         memTypeRepository.save(met);
        //     //create prefix
        //     Prefix pre = new Prefix();
        //         //create name
        //         pre.setName("Mrs.");
        //         prefixRepository.save(pre);
        //     //create gender
        //     Gender gen = new Gender();
        //         //create name
        //         gen.setName("Male");
        //         genderRepository.save(gen);
        //     mem.setSelect_gender(gen);
        //     mem.setSelect_memtype(met);
        //     mem.setSelect_prefix(pre);
        //     //create employee
        //     Employee emp = new Employee();
        //         //create e_name
        //         emp.setE_name("Jashon");
        //         //create e_tell
        //         emp.setE_TEL("0918819211");
        //         //create e_address
        //         emp.setE_ADDRESS("SUT");
        //         //create e_birth
        //         emp.setE_BIRTH(new Date());
        //         //create e_num
        //         emp.setE_NUM("540");
        //         //create e_date
        //         emp.setE_REGDATE(new Date());
        //         //create age
        //         Age age = new Age();
        //         age.setAge(15);
        //         ageRepository.save(age);
        //         emp.setAge(age);
        //         emp.setGender(gen);
        //         //create position
        //         Position post = new Position();
        //             //create name
        //             post.setName("Receptionist");
        //             positionRepository.save(post);

        //         emp.setPosition(post);
        //         //create marital_status
        //         Marital_Status mart = new Marital_Status();
        //         marital_StatusRepository.save(mart);
        //             //create name
        //             mart.setName("Single");
        //         emp.setMarital_Status(mart);
        //         employeeRepository.save(emp);
        //     mem.setSelect_employee(emp);
        //     memberRepository.save(mem);
	}
    
    //Save is success
	@Test
	void B6015145_ReservationSaveSuccessTest() {
        reservations revs = new reservations();
        revs.setReservedateandtime(LocalDateTime.parse("2020-12-04 14:00:00"));
        revs.setReserveseats(14);
        reservationsRepository.saveAndFlush(revs);

        reservations getres = reservationsRepository.getOne(revs.getId());
        assertEquals("2020-12-04 14:00:00",getres.getReservedateandtime());
	}

}
