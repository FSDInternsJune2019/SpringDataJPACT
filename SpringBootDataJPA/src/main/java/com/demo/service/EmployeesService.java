package com.demo.service;

import java.util.List;

import com.demo.dto.EmployeesDTO;
import com.demo.exception.EmployeesException;

public interface EmployeesService {
	
	List<EmployeesDTO> retrieveEmployees()  throws EmployeesException;
	EmployeesDTO retrieveEmployee(int empId) throws EmployeesException;
	String storeEmployee(EmployeesDTO employeesDTO);
	EmployeesDTO update(int empId,EmployeesDTO employeesDTO)throws EmployeesException;
	String updateEmployee(int empId,double newSalary);
	String deleteEmployee(int empId);

}
