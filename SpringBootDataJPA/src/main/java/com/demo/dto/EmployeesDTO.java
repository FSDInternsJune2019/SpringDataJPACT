package com.demo.dto;

import com.demo.validation.ValidateDesignation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeesDTO {
	
	//private int empId;
	@Schema(defaultValue = "Name should contain only alphabets,apostrope or hypen")
	@NotBlank(message = "{com.demo.dto.EmployeesDTO.empName.blank}")
	@Pattern(regexp = "^[\\p{L}'-]+$",message= "{com.demo.dto.EmployeesDTO.empName.error}")
	private String empName;
	@Schema(defaultValue = "Salary should be positive")
	@Positive(message="{com.demo.dto.EmployeesDTO.empSalary.error}")
	private double empSalary;
	@Schema(defaultValue = "Employee designation could be Intern,Programmer Analyst Trainee,Programmer Analyst")
	@NotBlank(message = "{com.demo.dto.EmployeesDTO.empDesignation.blank}")
	@ValidateDesignation(message="{com.demo.dto.EmployeesDTO.empDesignation.error}")
	private String empDesignation;
	@Valid
	private ProjectsDTO projects;
}
