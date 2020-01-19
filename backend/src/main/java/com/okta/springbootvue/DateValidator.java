package com.okta.springbootvue;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateValidator {

	public boolean isThisDateValid(String dateToValidate){

		if(dateToValidate == null){
			return false;
		}


		try {
			LocalDateTime date = LocalDateTime.parse(dateToValidate);

		} catch (DateTimeParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

}
