package com.demo.exception;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> constraintViolation(MethodArgumentNotValidException e){
		List<ObjectError> list=e.getBindingResult().getAllErrors();
		Map<String,String> errorMap=new HashMap<>();
		for(ObjectError error:list) {
			FieldError fieldError=(FieldError)error;
			errorMap.put(fieldError.getField(), error.getDefaultMessage());	
		}
        return errorMap;
	}
	
	@ExceptionHandler(EmployeesException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String localExceptionHandler(EmployeesException e) {
		return e.getMessage();
	}
}