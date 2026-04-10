package com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dto.EmployeesDTO;
import com.demo.exception.EmployeesException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="EmployeeRestAPI",description = "Employee Rest API V1")
public interface EmployeesController {
	@Operation(description = "Endpoint for getting resource of type Employees")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Resource is successfully fetched"),
	@ApiResponse(responseCode = "404",description = "Resources were not found")		
	})
	ResponseEntity<List<EmployeesDTO>> get() throws EmployeesException;
	EmployeesDTO getByEmpId(int empId) throws EmployeesException;
	EmployeesDTO getByEmpIdRequestParam(int empId) throws EmployeesException;
	@Operation(description = "Endpoint for creating resource of type Employees")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Resource is successfully created"),
	@ApiResponse(responseCode = "400",description = "Resources could not be created")		
	})
	ResponseEntity<String> post(EmployeesDTO employeesDTO);
	
	ResponseEntity<String> put(int empId,EmployeesDTO employeesDTO)throws EmployeesException;
	ResponseEntity<String> patch(int empId,double newSalary);
	ResponseEntity<String> delete(int empId);

}
