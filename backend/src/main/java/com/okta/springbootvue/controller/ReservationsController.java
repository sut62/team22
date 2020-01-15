package com.okta.springbootvue.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;

import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

import com.okta.springbootvue.entity.*;
import com.okta.springbootvue.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReservationsController {

    @Autowired
    private final ReservationsRepository ReservationsRepository;
    @Autowired
    private  MemberRepository MemberRepository;
    @Autowired
    private  TablesRepository tablesRepository;
    @Autowired
    private  ServicesRepository servicesRepository;

    public ReservationsController(ReservationsRepository ReservationsRepository) {
        this.ReservationsRepository = ReservationsRepository;
    }

    @GetMapping("/reservationses")
    public Collection<reservations> newresavations() {
        return ReservationsRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/reservationses/{id}/{date}/{time}")
    public Collection<reservations> newresavationID(
      @PathVariable Long id,
      @PathVariable String date,
      @PathVariable String time
    ) {
      
        return ReservationsRepository.findReserveByTableAndDateTime(id,date+" "+time).stream().collect(Collectors.toList());
    }

    @PostMapping("/reservationses/{member_id}/{table_id}/{service_id}/{reservs_date}/{reservs_time}/{seats}")
    public reservations newresavations(reservations newresavations,
  @PathVariable Long member_id,
  @PathVariable Long table_id,
  @PathVariable Long service_id,
  @PathVariable String reservs_date,
  @PathVariable String reservs_time,
  @PathVariable Integer seats
  
  ) {
    Optional<Member> optreservefor = MemberRepository.findById(member_id);
    Member reservefor = optreservefor.get();
    Optional<tables> opthas = tablesRepository.findById(table_id);
    tables has = opthas.get();
    Optional<services> optserviceto = servicesRepository.findById(service_id);
    services serviceto = optserviceto.get();
    
    LocalDateTime datetime = LocalDateTime.parse(reservs_date+"T"+reservs_time);
    
    newresavations.setReservefor(reservefor);
    newresavations.setHas(has);
    newresavations.setServiceto(serviceto);
    newresavations.setReserveseats(seats);
    newresavations.setReservedateandtime(datetime);
    return ReservationsRepository.save(newresavations); 
  }
}