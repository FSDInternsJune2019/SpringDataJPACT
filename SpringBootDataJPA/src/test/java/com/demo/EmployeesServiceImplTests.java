package com.demo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.demo.dto.EmployeesDTO;
import com.demo.entities.Employees;
import com.demo.entities.Projects;
import com.demo.enums.TYPE;
import com.demo.exception.EmployeesException;
import com.demo.repositories.EmployeesRepository;
import com.demo.service.EmployeesServiceImpl;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class EmployeesServiceImplTests {
	
	@Mock
	private EmployeesRepository mockEmployeesRepository;
	@Mock
	private ModelMapper mockModelMapper;
	@InjectMocks
	private EmployeesServiceImpl employeesServiceImpl;
	
	private Employees billableEmployee;
	private Employees nonBillableEmployee;
	private Projects nonBillableProject;
	private Projects billableProject;
	
	@BeforeEach
	void setUp() {
		billableProject=new Projects();
		billableProject.setProjectCode("P001");
		billableProject.setProjectName("Test Project Billable");
		billableProject.setProjectType(TYPE.BILLABLE);
		
		billableEmployee=new Employees();
		billableEmployee.setEmpSalary(45000);
		billableEmployee.setEmpName("Sabbir");
		billableEmployee.setEmpDesignation("Trainer");
		billableEmployee.setProjects(billableProject);
		
	     nonBillableProject=new Projects();
		nonBillableProject.setProjectCode("P002");
		nonBillableProject.setProjectName("Test Project Non Billable");
		nonBillableProject.setProjectType(TYPE.NONBILLABLE);
		
		nonBillableEmployee=new Employees();
		nonBillableEmployee.setEmpSalary(75000);
		nonBillableEmployee.setEmpName("Amit");
		nonBillableEmployee.setEmpDesignation("Developer");
		nonBillableEmployee.setProjects(nonBillableProject);
		
	}
	@AfterEach
	void tearDown() {
	billableEmployee=null;
    nonBillableEmployee=null;
    nonBillableProject=null;
    billableProject=null;
	}

	@Test
	void test_retrieveEmployees_positive() {
		List<Employees> dummyEmployeesList=Arrays.asList(billableEmployee,nonBillableEmployee);
		when(mockEmployeesRepository.findAll()).thenReturn(dummyEmployeesList);
	
		when(mockModelMapper.map(any(), eq(EmployeesDTO.class)))
	    .thenReturn(new EmployeesDTO());
		
		try {
			List<EmployeesDTO> actual=employeesServiceImpl.retrieveEmployees();
			assertNotNull(actual);
			assertTrue(actual.size()>0);
			assertTrue(actual.size()==2);
			verify(mockEmployeesRepository,times(1)).findAll();
		} catch (EmployeesException e) {
			assertTrue(false);
		}
		
	}
	@Test
	void test_retrieveEmployees_negative() {
		List<Employees> dummyEmployeesList=Arrays.asList();
		when(mockEmployeesRepository.findAll()).thenReturn(dummyEmployeesList);
		try {
			List<EmployeesDTO> actual=employeesServiceImpl.retrieveEmployees();
		} catch (EmployeesException e) {
			assertTrue(true);
			assertEquals("No Employees Found",e.getMessage());
		}
	}
	@Test
	void test_retireveEmployees_SalaryIncrement() {
		List<Employees> dummyEmployeesList=new ArrayList<>();
		dummyEmployeesList.add(billableEmployee);
		dummyEmployeesList.add(nonBillableEmployee);
		when(mockEmployeesRepository.findAll()).thenReturn(dummyEmployeesList);
		
		try {
			employeesServiceImpl.retrieveEmployees();
			assertEquals(95000,dummyEmployeesList.get(0).getEmpSalary());
			assertEquals(75000,dummyEmployeesList.get(1).getEmpSalary());
		} catch (EmployeesException e) {
			assertTrue(false);
		}
	}

	@Test
	@Disabled
	void test_retrieveEmployeeById_positive() {
		billableEmployee.setEmpId(1001);
		Optional<Employees> optionalOfEmployees=Optional.of(billableEmployee);
		when(mockEmployeesRepository.findById(anyInt())).thenReturn(optionalOfEmployees);
		
		EmployeesDTO employeesDTO=new EmployeesDTO();
		
		when(mockModelMapper.map(any(), eq(EmployeesDTO.class)))
	    .thenReturn(employeesDTO);
		
		try {
			EmployeesDTO actual=employeesServiceImpl.retrieveEmployee(1001);
			assertNotNull(actual);
		} catch (EmployeesException e) {
			assertTrue(false);
		}
		
	}
	
	@Test
	void test_retrieveEmployeeById_negative() {
		
		Optional<Employees> optionalOfEmployees=Optional.ofNullable(null);
		when(mockEmployeesRepository.findById(anyInt())).thenReturn(optionalOfEmployees);
		
		try {
		   employeesServiceImpl.retrieveEmployee(1001);
		} catch (EmployeesException e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	
	void test_storeEmployee_positive() {
		when(mockModelMapper.map(any(), eq(Employees.class))).thenReturn(billableEmployee);
		when(mockEmployeesRepository.save(any())).thenReturn(billableEmployee);
		EmployeesDTO employee = mockModelMapper.map(billableEmployee, EmployeesDTO.class);
		
		String actual = employeesServiceImpl.storeEmployee(employee);
		assertEquals("success", actual);
	}
	
	@Test
	void test_storeEmployee_negative() {
		Employees employee = null;
		when(mockEmployeesRepository.save(any())).thenReturn(employee);
		EmployeesDTO employeeDTO = mockModelMapper.map(billableEmployee, EmployeesDTO.class);
		
		String actual = employeesServiceImpl.storeEmployee(employeeDTO);
		assertEquals("fail", actual);
	}
}
