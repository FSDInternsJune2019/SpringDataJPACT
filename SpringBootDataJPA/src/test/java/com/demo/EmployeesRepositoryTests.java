package com.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import com.demo.entities.*;
import com.demo.repositories.*;
import com.demo.enums.*;

@DataJpaTest
class EmployeesRepositoryTests {
	@Autowired
	private EmployeesRepository employeesRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	private Employees employee;
	private Projects project;
	
	
	@BeforeEach
	void setUp() {
		employee=new Employees();
		employee.setEmpName("Sabbir");
		employee.setEmpSalary(45000);
		employee.setEmpDesignation("Trainer");
		
	    project=new Projects();
		project.setProjectCode("P001");
		project.setProjectName("Test");
		project.setProjectType(TYPE.BILLABLE);
	}
	
	@AfterEach
	void tearDown() {
		employee=null;
		project=null;
	}

	@Test
	void testFindAll_positive() {
		entityManager.persist(project);
		Projects projectFound=(Projects)entityManager.find(Projects.class,"P001");
		employee.setProjects(projectFound);
		entityManager.persist(employee);
		List<Employees> iterable=(List<Employees>)employeesRepository.findAll();
		assertTrue(iterable.size()>0);
		
	}
	@Test
	@Disabled
	void testFindAll_negative(){
		entityManager.clear();
		List<Employees> employeeList=(List<Employees>)employeesRepository.findAll();
		assertTrue(employeeList.isEmpty());
	}
	@Test
	void testFindById_positive() {
		entityManager.persist(project);
		Projects projectFound=(Projects)entityManager.find(Projects.class,"P001");
		employee.setProjects(projectFound);
		Employees employeePersisted=entityManager.persist(employee);
		Optional<Employees> employeeFound=employeesRepository.findById(employeePersisted.getEmpId());
		assertTrue(employeeFound.isPresent());
		
	}
	@Test
	void testFindById_negative() {
		entityManager.clear();
		int empId=1001;
		Optional<Employees> employeeFound=employeesRepository.findById(empId);
		assertFalse(employeeFound.isPresent());
		
	}
	@Test
	public void testSave_positive() {
		employee.setProjects(project);
		Employees employeeSaved=employeesRepository.save(employee);
		Optional<Employees> employeeFound=employeesRepository.findById(employeeSaved.getEmpId());
		assertTrue(employeeFound.isPresent());
	}
	
	@Test
	public void testSave_negative() {
		employee.setProjects(project);
		employee=null;
		try {
		employeesRepository.save(employee);
		assertTrue(false);
		}catch(InvalidDataAccessApiUsageException e) {
			assertTrue(true);
		}
		
		
	}
	@Test
	void testUpdateEmployeeSalaryByEmpId_positive() {
		
		employee.setProjects(project);
		entityManager.persist(employee);
		int rows=employeesRepository.update(employee.getEmpId(), 32000);
		assertTrue(rows>0);
		entityManager.clear();
		Employees employeeFound=entityManager.find(Employees.class,employee.getEmpId());
		assertEquals(32000,employeeFound.getEmpSalary());
	}

	@Test
	void testUpdateEmployeeSalaryByEmpId_negative() {
		employee.setProjects(project);
		entityManager.persist(employee);
		int rows=employeesRepository.update(1002, 32000);
		assertTrue(rows==0);

	}

}
