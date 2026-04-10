package com.demo.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DesignationValidator implements ConstraintValidator<ValidateDesignation,
String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		List<String> designations=Arrays.asList("Intern","Programmer Analyst Trainee","Programmer Analyst");
		if(designations.contains(value)) {
			return true;
		}
		return false;
	}

}
