package com.MillRsDevide.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
	@Bean
	ApplicationRunner init(AgeRepository ageRepository, GenderRepository genderRepository, Marital_StatusRepository marital_StatusRepository,PositionRepository positionRepository) {
		return args -> {
			Stream.of(15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60).forEach(name -> {
				Age age = new Age(); 
				age.setAge(name); 
				ageRepository.save(age); 
			});

			Stream.of("ชาย", "หญิง", "อื่นๆ").forEach(name -> {
				Gender gender = new Gender(); 
				gender.setName(name);
				genderRepository.save(gender); 
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

			 

		};
	}

}
