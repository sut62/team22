package com.okta.springbootvue.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import java.net.URLDecoder;
import java.text.SimpleDateFormat; 
import java.text.ParseException;

import com.okta.springbootvue.entity.MemType;
import com.okta.springbootvue.entity.Gender;
import com.okta.springbootvue.entity.Prefix;
import com.okta.springbootvue.entity.Employee;
import com.okta.springbootvue.entity.Member;


import com.okta.springbootvue.repository.MemTypeRepository;
import com.okta.springbootvue.repository.GenderRepository;
import com.okta.springbootvue.repository.PrefixRepository;
import com.okta.springbootvue.repository.EmployeeRepository;
import com.okta.springbootvue.repository.MemberRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MemberController {
    @Autowired
    private final MemberRepository memberRepository;
    
    

    MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    public Collection<Member> Members() {
        return memberRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/members/{id}")
    public Optional<Member> Members(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }

    @PostMapping("/members/{prefix_id}/{name}/{gender_id}/{birth}/{tel}/{mail}/{memtype_id}/{employee_id}")
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
