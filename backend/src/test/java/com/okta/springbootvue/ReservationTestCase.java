package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.validation.ConstraintViolation;
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
    @Autowired
    private ServicesRepository servicesRepository;

    private Prefix prefix = new Prefix();
    private MemType memType = new MemType();
    private Gender gender = new Gender();
    private Position position = new Position();
    private Marital_Status marital_Status = new Marital_Status();
    private Employee employee = new Employee();
    private Member member = new Member();
    @BeforeEach
    public void setup() throws ParseException {
        
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
    
    //save success
	@Test
	void B6015145_testResercationSaveSuccess() {
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);

        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);

        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(4);
        res.setServiceto(service);
        res.setHas(table);
        res.setReservefor(member);

        reservationsRepository.saveAndFlush(res);

        reservations rev = reservationsRepository.findById(res.getId());
        assertEquals(datetime, rev.getReservedateandtime());
        assertEquals(4, rev.getReserveseats());
        assertEquals(table, rev.getHas());
        assertEquals(4, rev.getHas().getSeats());
        assertEquals(member, rev.getReservefor());
        assertEquals(service, rev.getServiceto());


    }
    
    //Seat is not positive number
    @Test
    void B6015145_testSeatMustBePositiveNumber(){
        
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(-1); //negative number
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);

        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);
        res.setHas(table);
        res.setReservefor(member);
        res.setServiceto(service);
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
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(null);//null 
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);
        
        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);
        res.setServiceto(service);
        res.setHas(table);
        res.setReservefor(member);
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
        LocalDateTime datetime = LocalDateTime.now().plusDays(-1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(5);
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);

        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);
        res.setServiceto(service);
        res.setHas(table);
        res.setReservefor(member);
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
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);

        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);
        res.setServiceto(service);
        res.setHas(table);
        res.setReservefor(member);
        res.setReserveseats(4);
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
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(9);//more than 8 
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);
        
        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);
        res.setServiceto(service);
        res.setHas(table);
        res.setReservefor(member);
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
    //Table cant be null
    @Test
	void B6015145_testTableCantBeNull() {
        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);

        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(4);
        res.setServiceto(service);
        res.setHas(null);
        res.setReservefor(member);

        
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //mustbe 1 error
        assertEquals(1,result.size());

        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        assertEquals("table must not be null", vi.getMessage());
        assertEquals("has", vi.getPropertyPath().toString());


    }

    //Member cant be null
    @Test
	void B6015145_testMemberCantBeNull() {
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);

        services service = new services();
        service.setServiceName("BJ");
        servicesRepository.saveAndFlush(service);

        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(4);
        res.setServiceto(service);
        res.setHas(table);
        res.setReservefor(null);

        
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //mustbe 1 error
        assertEquals(1,result.size());

        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        assertEquals("member must not be null", vi.getMessage());
        assertEquals("reservefor", vi.getPropertyPath().toString());


    }

    //Service cant be null
    @Test
	void B6015145_testServiceCantBeNull() {
        tables table = new tables();
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);
        reservations res = new reservations();
        LocalDateTime datetime = LocalDateTime.now().plusDays(1);
        res.setReservedateandtime(datetime);
        res.setReserveseats(4);
        res.setServiceto(null);
        res.setHas(table);
        res.setReservefor(member);

        
        Set<ConstraintViolation<reservations>> result = validator.validate(res);

        //mustbe 1 error
        assertEquals(1,result.size());

        //error message and path must be correct
        ConstraintViolation<reservations> vi = result.iterator().next();
        assertEquals("service must not be null", vi.getMessage());
        assertEquals("serviceto", vi.getPropertyPath().toString());


    }

}
