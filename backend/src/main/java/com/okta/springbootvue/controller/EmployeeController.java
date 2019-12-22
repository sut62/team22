package com.okta.springbootvue.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.text.SimpleDateFormat; 
import java.text.ParseException;


import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;



import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private AgeRepository ageRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private Marital_StatusRepository marital_StatusRepository;
    @Autowired
    private PositionRepository positionRepository;
    

    EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/Employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Employee/{Age_ID}/{Gender_ID}/{Position_ID}/{Marital_Status_ID}/{E_NAME}/{E_TEL}/{E_ADDRESS}/{E_BIRTH}/{E_NUM}/{E_EMID}")
    public Employee newEmployee(Employee newEmployee,
    @PathVariable long Age_ID,
    @PathVariable long Gender_ID,
    @PathVariable long Position_ID,
    @PathVariable long Marital_Status_ID,
    @PathVariable String E_NAME,
    @PathVariable String E_TEL,
    @PathVariable String E_ADDRESS,
    @PathVariable String E_BIRTH,
    @PathVariable String E_NUM,
    @PathVariable String E_EMID ) throws ParseException

 {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    Gender gender = genderRepository.findById(Gender_ID);
    Marital_Status marital_Status = marital_StatusRepository.findById(Marital_Status_ID);
    Position position = positionRepository.findById(Position_ID);
    Age age = ageRepository.findById(Age_ID);
    
    newEmployee.setAge(age);
    newEmployee.setGender(gender);
    newEmployee.setPosition(position);
    newEmployee.setMarital_Status(marital_Status);

    newEmployee.setE_NAME(E_NAME);
    newEmployee.setE_TEL(E_TEL);
    newEmployee.setE_ADDRESS(E_ADDRESS);
    newEmployee.setE_BIRTH(dateFormat.parse(E_BIRTH));
    newEmployee.setE_NUM(E_NUM);
    newEmployee.setE_EMID(E_EMID);
    newEmployee.setE_REGDATE(new Date());
    
    

    return employeeRepository.save(newEmployee); 
    
    }
}