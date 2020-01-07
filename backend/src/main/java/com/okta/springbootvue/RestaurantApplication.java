package com.okta.springbootvue;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;

@SpringBootApplication
public class RestaurantApplication  {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	ApplicationRunner init(PrefixRepository prefixRepository, GenderRepository genderRepository, MemTypeRepository memtypeRepository) {
		return args -> {
			Stream.of("บัตรนักเรียน/นักศึกษา", "ทั่วไป", "VIP").forEach(name -> {
				MemType memtype = new MemType(); // สร้าง Object MemType
				memtype.setName(name); // set ชื่อ (type) ให้ Object ชื่อ Foodtype
				memtypeRepository.save(memtype); // บันทึก Objcet ชื่อ Foodtype
			});
			Stream.of("ชาย","หญิง","ไม่ระบุ").forEach(name -> {
				Gender gender = new Gender(); // สร้าง Object Quantity
				gender.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Quantity
				genderRepository.save(gender); // บันทึก Objcet ชื่อ Quantity
			});
			
			Stream.of("เด็กชาย","เด็กหญิง","นาย","นาง","นางสาว").forEach(name -> {
				Prefix prefix = new Prefix(); // สร้าง Object Quantity
				prefix.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Quantity
				prefixRepository.save(prefix); // บันทึก Objcet ชื่อ Quantity
			});
			
		};
	}
	


}
