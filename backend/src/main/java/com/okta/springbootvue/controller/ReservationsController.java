package com.okta.springbootvue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
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

    public ReservationsController(ReservationsRepository ReservationsRepository) {
        this.ReservationsRepository = ReservationsRepository;
    }

    @GetMapping("/reservs")
    public Collection<reservations> newresavations() {
        return ReservationsRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/reservs/{member_id}/{table_id}/{service_id}/{reservs_date}/{reservs_time}/{seats}")
    public reservations newresavations(reservations newresavations,
  @PathVariable long member_id,
  @PathVariable long table_id,
  @PathVariable long service_id,
  @PathVariable String reservs_date,
  @PathVariable String reservs_time,
  @PathVariable String seats
  
  ) {
  
    Member reservefor = MemberRepository.findById(member_id);
    tables has = TablesRepository.findById(table_id);
    services serviceto = ServicesRepository.findById(service_id);
    LocalDateTime datetime = LocalDateTime.parse(reservs_date+"T"+reservs_time);
    
    newresavations.setReservefor(reservefor);
    newresavations.setHas(has);
    newresavations.setServiceto(serviceto);
    newresavations.setSeats(seats);
    newresavations.setReservedateandtime(datetime);
    return ReservationsRepository.save(newresavations); 
  }
}