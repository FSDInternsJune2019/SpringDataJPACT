package com.demo.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy=DesignationValidator.class )
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateDesignation {
	String message() default "Employee Designation not valid";
	Class<?>[]groups() default{};
	Class<? extends Payload>[]payload() default{};
}
