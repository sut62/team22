
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

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PaymentController {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private OrderFoodRepository orderfoodRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MembershipRepository membershipRepository;

    PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/payment")
    public Collection<Payment> Payments() {
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/payment/{orderfood_id}/{member_id}/{employee_id}/{membership_id}/{statusname}")
    public Payment newPayment(Payment newpayment,

    @PathVariable long orderfood_id,
    @PathVariable long member_id,
    @PathVariable long membership_id,
    @PathVariable long employee_id,
    @PathVariable Integer statusname  ) {
    

    OrderFood od = orderfoodRepository.findById(orderfood_id);
    Member mb = memberRepository.findById(member_id);
    Membership mbs = membershipRepository.findById(membership_id);
    Employee ep = employeeRepository.findById(employee_id);

    newpayment.setSelectorderfood(od);
    newpayment.setSelectmember(mb);
    newpayment.setSelectmembership(mbs);
    newpayment.setSelectemployee(ep);
    newpayment.setStatusname(statusname);


    return paymentRepository.save(newpayment); 
    
    }
}