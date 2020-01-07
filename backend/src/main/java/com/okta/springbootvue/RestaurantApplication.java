package com.okta.springbootvue;

import java.util.Collections;
import java.util.stream.Stream;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;

@SpringBootApplication
public class RestaurantApplication  {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	ApplicationRunner init(PrefixRepository prefixRepository, GenderRepository genderRepository, MemTypeRepository memtypeRepository,
	AgeRepository ageRepository,Marital_StatusRepository marital_StatusRepository,PositionRepository positionRepository,
	MenuCateRepository CateRep, MenuIngreRepository IngreRep, MenuTypeRepository TypeRep
	
	
	) {
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

			Stream.of(15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60).forEach(name -> {
				Age age = new Age(); 
				age.setAge(name); 
				ageRepository.save(age); 
			});

			Stream.of("พนักตำเเหน่งหน้าเคาน์เตอร์", "พนักงานร้านอาหาร", "พนักงานรับออร์เดอร์").forEach(name -> {
				Position position = new Position(); 
				position.setName(name); 
				positionRepository.save(position); 
			});
			
			

			Stream.of("โสด", "สมรส").forEach(name -> {
				Marital_Status marital_Status = new Marital_Status(); 
				marital_Status.setName(name); 
				marital_StatusRepository.save(marital_Status); 
			});

			Stream.of("เนื้อสัตว์", "นม", "ไข่", "ผัก", "ผลไม้", "เนื้อปลา", "ธัญพืช").forEach(catename -> {
        		MenuCate cn = new MenuCate();
        		cn.setCname(catename);
        		CateRep.save(cn);
      		});

      		Stream.of("เนื้อสัตว์", "เนื้อไก่", "เนื้อหมู", "มังสวิรัติ", "อาหารทะเล").forEach(ingrename -> {
        		MenuIngre in = new MenuIngre();
        		in.setIname(ingrename);
        		IngreRep.save(in);
      		});

      		Stream.of("อาหารคาว","อาหารหวาน","อาหารเรียกน้ำย่อย","อาหารว่าง").forEach(typename -> {
        		MenuType tn = new MenuType();
        		tn.setTname(typename);
        		TypeRep.save(tn);
     		 });
			
		};
	}
	@Bean
  	public FilterRegistrationBean simpleCorsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // *** URL below needs to match the Vue client URL and port ***
    config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
    config.setAllowedMethods(Collections.singletonList("*"));
    config.setAllowedHeaders(Collections.singletonList("*"));
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return bean;
  }


}
