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
	Marital_StatusRepository marital_StatusRepository,PositionRepository positionRepository,
	MenuCateRepository CateRep, MenuIngreRepository IngreRep, MenuTypeRepository TypeRep,
	TablesRepository tablesRepository , ServicesRepository servicesRepository , OrderTypeRepository ordertypeRepository,
	OrderStatusRepository orderstatusRepository

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

			Stream.of("พนักตำเเหน่งหน้าเคาน์เตอร์", "พนักงานร้านอาหาร", "พนักงานรับออร์เดอร์").forEach(name -> {
				Position position = new Position();
				position.setName(name);
				positionRepository.save(position);
			});

			Stream.of("ทานที่นี่", "ห่อกลับบ้าน").forEach(type -> {
				OrderType ordertype = new OrderType();
				ordertype.setType(type);
				ordertypeRepository.save(ordertype);
			});

			Stream.of("กำลังจัดเตรียมอาหาร", "เสิร์ฟอาหารเรียบร้อย").forEach(status -> {
				OrderStatus orderstatus = new OrderStatus();
				orderstatus.setStatus(status);
				orderstatusRepository.save(orderstatus);
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

			Stream.of("เนื้อไก่","เนื้อหมู","เนื้อวัว","เนื้อเป็ด","เนื้อปลา","อาหารทะเล","ไข่","มังสวิรัติ","แป้ง","แอลกอฮอ","นม","อื่นๆ").forEach(ingrename -> {
				MenuIngre in = new MenuIngre();
				in.setIname(ingrename);
				IngreRep.save(in);
			});

			Stream.of("เมนูผัด","เมนูปิ้งย่าง","เมนูยำ","เมนูต้มยำ","เมนูนึ่ง","เมนูทอด","อื่นๆ").forEach(typename -> {
				MenuType tn = new MenuType();
				tn.setTname(typename);
				TypeRep.save(tn);
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
