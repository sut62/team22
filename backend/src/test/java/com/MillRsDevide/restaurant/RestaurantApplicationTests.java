package com.MillRsDevide.restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@SpringBootTest
class RestaurantApplicationTests {
	private Validator validator;

	@BeforeEach
	public void setup(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		
		
	}
	
	@Test
	void contextLoads() {
	}

}
