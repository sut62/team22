
package com.okta.springbootvue.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import com.okta.springbootvue.controller.dto.PaymentData;
import com.okta.springbootvue.entity.Employee;
import com.okta.springbootvue.entity.Member;
import com.okta.springbootvue.entity.Payment;
import com.okta.springbootvue.entity.tables;
import com.okta.springbootvue.repository.EmployeeRepository;
import com.okta.springbootvue.repository.MemberRepository;
import com.okta.springbootvue.repository.PaymentRepository;
import com.okta.springbootvue.repository.TablesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        payment.setMoney(paymentData.getMoney());
        payment.setChange(paymentData.getChange());
        payment.setTotal(paymentData.getTotal());
        payment.setCreateDate(date);
        paymentRepository.save(payment);
        
                
    }

    
}
