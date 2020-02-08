
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import java.util.Set;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.controller.dto.PaymentData;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentController {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MembershipRepository membershipRepository;

    PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    
    @PostMapping("/Savepayment")
    public void savePayment(@RequestBody PaymentData paymentData){
        Payment payment = new Payment();
        System.out.println("\n"+paymentData.getMoney()+" "+paymentData.getTable()+"\n");
        if(paymentData.getMember()!=0){
            Optional<Member> mem = memberRepository.findById(paymentData.getMember());
            Member membs = mem.get();
            payment.setSelectmember(membs);
        }
        else{
        Member mem= memberRepository.findById((long)0);
        payment.setSelectmember(mem);
        }
        
        Optional<Membership> mems = membershipRepository.findById(paymentData.getMemberships());
            Membership memss = mems.get();
        Optional<Employee> emp = employeeRepository.findById(paymentData.getEmployee());
        Employee emps = emp.get();
        System.out.println("\n"+emps+"\n");
        System.out.println("\n"+paymentData.getDate()+"\n");
        
        
        
        
        Optional<tables> sor = tablesRepository.findById(paymentData.getTable());
        tables or = sor.get();
        System.out.println("\n"+or+"\n");
        LocalDateTime date = paymentData.getDate();
        
        payment.setSelecttable(or);
        payment.setSelectemployee(emps);
        payment.setSelectmembership(memss);
        payment.setMoney(paymentData.getMoney());
        payment.setChange(paymentData.getChange());
        payment.setTotal(paymentData.getTotal());
        payment.setCreateDate(date);
        paymentRepository.save(payment);
        
                
    }

    
}
