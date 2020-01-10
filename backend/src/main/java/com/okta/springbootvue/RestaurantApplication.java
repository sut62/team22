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
	MenuCateRepository CateRep, MenuIngreRepository IngreRep, MenuTypeRepository TypeRep,
	TablesRepository tablesRepository , ServicesRepository servicesRepository , OrderTypeRepository ordertypeRepository,MembershipRepository membershipRepository

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

			Stream.of("ทานที่นี้", "ห่อกลับบ้าน").forEach(type -> {
				OrderType ordertype = new OrderType();
				ordertype.setType(type);
				ordertypeRepository.save(ordertype);
			});

			Stream.of("โสด", "สมรส").forEach(name -> {
				Marital_Status marital_Status = new Marital_Status();
				marital_Status.setName(name);
				marital_StatusRepository.save(marital_Status);
			});

			Stream.of("อาหารจานหลัก","อาหารเรียกน้ำย่อย","อาหารทานเล่น","ของหวาน","เครื่องดื่ม").forEach(catename -> {
				MenuCate cn = new MenuCate();
				cn.setCname(catename);
				CateRep.save(cn);
			});

			Stream.of("เนื้อไก่","เนื้อหมู","เนื้อวัว","เนื้อเป็ด","เนื้อปลา","กุ้ง","ปลาหมึก","ไข่","มังสวิรัติ","แป้ง","แอลกอฮอ","นม").forEach(ingrename -> {
				MenuIngre in = new MenuIngre();
				in.setIname(ingrename);
				IngreRep.save(in);
			});

			Stream.of("เมนูผัด","เมนูปิ้งย่าง","เมนูยำ","เมนูนึ่ง","เมนูทอด","เมนูเส้น","เมนูหมู","เมนูไก่","เมนูปลา","เมนูกุ้ง","เมนูเป็ด","เมนูเนื้อวัว","เมนูอาหารทะเล").forEach(typename -> {
				MenuType tn = new MenuType();
				tn.setTname(typename);
				TypeRep.save(tn);
			});

			Stream.of("เป็นสมาชิก","ไม่เป็นมาชิก").forEach(MBSame -> {
				Membership membership = new Membership();
				membership.setNamembs(MBSame);
				membershipRepository.save(membership);
			});
			tables tb = new tables();
			tb.setSeats(4);
			tables tb1 = new tables();
			tb1.setSeats(2);
			tables tb2 = new tables();
			tb2.setSeats(6);
			tables tb3 = new tables();
			tb3.setSeats(1);

			tablesRepository.save(tb);
			tablesRepository.save(tb2);
			tablesRepository.save(tb3);
			tablesRepository.save(tb1);
			Stream.of("Regular","VIP").forEach(typename -> {
				services tn = new services();
				tn.setServiceName(typename);
				servicesRepository.save(tn);
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
