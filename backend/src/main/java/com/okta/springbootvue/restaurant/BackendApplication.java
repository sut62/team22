package com.cpe.backend;

import com.cpe.backend.entity.Member;
import com.cpe.backend.entity.MemType;
import com.cpe.backend.entity.Prefix;
import com.cpe.backend.entity.Gender;
import com.cpe.backend.entity.Employee;
import com.cpe.backend.repository.MemTypeRepository;
import com.cpe.backend.repository.PrefixRepository;
import com.cpe.backend.repository.GenderRepository;
import com.cpe.backend.repository.EmployeeRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Collections;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;

import java.util.stream.Stream;
import java.util.List;




@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	ApplicationRunner init(PrefixRepository prefixRepository, GenderRepository genderRepository, MemTypeRepository memtypeRepository, EmployeeRepository employeeRepository) {
		return args -> {
			Stream.of("บัตรนักเรียน/นักศึกษา", "ทั่วไป", "VIP").forEach(name -> {
				MemType memtype = new MemType(); // สร้าง Object MemType
				memtype.setName(name); // set ชื่อ (type) ให้ Object ชื่อ Foodtype
				memtypeRepository.save(memtype); // บันทึก Objcet ชื่อ Foodtype
			});

			Employee p1 = new  Employee();
			p1.setName("Tanjiro JoJo");
			employeeRepository.save(p1);

			Employee p2 = new  Employee();
			p2.setName("Okamoto Zero");
			employeeRepository.save(p2);

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
			memtypeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity บน Terminal
			employeeRepository.findAll().forEach(System.out::println);
			prefixRepository.findAll().forEach(System.out::println);
			genderRepository.findAll().forEach(System.out::println);
			
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
