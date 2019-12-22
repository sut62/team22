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
public class MemberController {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private PrefixRepository prefixRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MemTypeRepository memtypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    

    MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/Member")
    public Collection<Member> Members() {
        return memberRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Member/{prefix_id}/{name}/{birth}/{tel}/{gender_id}/{mail}/{memtype_id}/{employee_id}")
    public Member newMember(Member newMember,
    @PathVariable long prefix_id,
    @PathVariable long gender_id,
    @PathVariable long memtype_id,
    @PathVariable long employee_id,
    @PathVariable String name,
    @PathVariable String tel,
    @PathVariable String mail,
    @PathVariable String birth
    )
    throws ParseException
 {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    Gender select_gender = genderRepository.findById(gender_id);
    Prefix select_prefix = prefixRepository.findById(prefix_id);
    MemType select_memtype = memtypeRepository.findById(memtype_id);
    Employee select_employee = employeeRepository.findById(employee_id);
    
    newMember.setSelect_gender(select_gender);
    newMember.setSelect_prefix(select_prefix);
    newMember.setSelect_employee(select_employee);
    newMember.setSelect_memtype(select_memtype);

    newMember.setName(name);
    newMember.setTel(tel);
    newMember.setMail(mail);
    newMember.setBirth(dateFormat.parse(birth));
    newMember.setSaveDate(new Date());
    

    return memberRepository.save(newMember); 
    
    }
}