package com.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.controller.EmployeesControllerImpl;
import com.demo.dto.EmployeesDTO;
import com.demo.dto.ProjectsDTO;
import com.demo.exception.EmployeesException;
import com.demo.service.EmployeesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
@WebMvcTest(EmployeesControllerImpl.class)
class EmployeesControllerTests {
	
	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EmployeesService employeesService;
	    

	    @Autowired
	    private ObjectMapper objectMapper;

	    private EmployeesDTO employeesDTO;

	    private ProjectsDTO projectsDTO;
	    
	    private static Validator validator;
	    
	    @BeforeEach
	    void setUp() {
	    	employeesDTO = new EmployeesDTO();
	    	employeesDTO.setEmpSalary(45000);
	    	employeesDTO.setEmpDesignation("Intern");
	    	employeesDTO.setEmpName("Sabbir");
	    	projectsDTO=new ProjectsDTO();
	    	projectsDTO.setProjectCode("P-001");
	    	projectsDTO.setProjectName("Test");
	    	employeesDTO.setProjects(projectsDTO);
	    	
	
	    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
	    }
	    @AfterEach
	    void tearDown() {
	    	employeesDTO=null;
	    	projectsDTO=null;
	    }
	    @Test
	    void testGetEmployees_positive() throws Exception {
	        List<EmployeesDTO> list = Arrays.asList(employeesDTO);
	        when(employeesService.retrieveEmployees()).thenReturn(list);

	        mockMvc.perform(get("/api/employees"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].empName").value("Sabbir"));
	    }
	    
	    @Test
	    void testGetEmployees_negative() throws Exception {
	        when(employeesService.retrieveEmployees()).thenThrow(EmployeesException.class);

	        mockMvc.perform(get("/api/employees"))
	                .andExpect(status().isNotFound());
	               
	    }
	    
	    
	    @Test
	    void testGetByEmpId_positive() throws Exception {
	        when(employeesService.retrieveEmployee(1001)).thenReturn(employeesDTO);

	        mockMvc.perform(get("/api/employees/1001"))
	                .andExpect(status().isFound())
	                .andExpect(jsonPath("empName").value("Sabbir"));
	    }
	    
	    @Test
	    void testStoreEmployee_positiveWhenDataIsValid() throws Exception {
	    	when(employeesService.storeEmployee(employeesDTO)).thenReturn("success");
	    	String json = objectMapper.writeValueAsString(employeesDTO);
	        mockMvc.perform(post("/api/v1/employees")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isCreated());
	    }

	    @Test
	    void testStoreEmployee_positiveWhenDataIsInValid() throws Exception {
	    	when(employeesService.storeEmployee(employeesDTO)).thenReturn("success");
	    	employeesDTO.setEmpName("Test111");
	    	String json = objectMapper.writeValueAsString(employeesDTO);
	        mockMvc.perform(post("/api/v1/employees")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isBadRequest());
	    }
	    

	    @Test
	    void testEmployeesDTO_negativeWhenDataIsValid() throws Exception {
	    	employeesDTO.setEmpName("Test");
	        Set<ConstraintViolation<EmployeesDTO>> violations = validator.validate(employeesDTO);
	        assertTrue(violations.isEmpty());
	        
	    }
	    
	    @Test
	    void testEmployeesDTO_negativeWhenEmpNameIsNotValid() throws Exception {
	    	employeesDTO.setEmpName("Test111");
	        Set<ConstraintViolation<EmployeesDTO>> violations = validator.validate(employeesDTO);
	        assertTrue(!violations.isEmpty());
	        
	    }
	    
	    @Test
	    void testEmployeesDTO_negativeWhenEmpSalaryIsNotValid() throws Exception {
	    	employeesDTO.setEmpSalary(-45000);
	        Set<ConstraintViolation<EmployeesDTO>> violations = validator.validate(employeesDTO);
	        assertTrue(!violations.isEmpty());
	        
	    }
	    


}
