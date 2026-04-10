package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demo.dto.EmployeesDTO;
import com.demo.entities.Employees;
import com.demo.entities.Projects;
import com.demo.enums.TYPE;
import com.demo.exception.EmployeesException;
import com.demo.repositories.EmployeesRepository;
import com.demo.repositories.ProjectsRepository;
@Service
@CacheConfig(cacheNames = "employees") 
public class EmployeesServiceImpl implements EmployeesService{
	private EmployeesRepository repository;
	private ProjectsRepository projectRepository;
	private ModelMapper mapper;
	
	@Autowired
	/**
	 * @Autowired was removed as was mentioned as unnecessary by SonarQube
	 * @param repository
	 * @param mapper
	 */
	public EmployeesServiceImpl(EmployeesRepository repository,ProjectsRepository projectRepository,ModelMapper mapper) {
		this.projectRepository=projectRepository;
		this.repository=repository;
		this.mapper=mapper;
	}

	@Cacheable
	@Override
	public List<EmployeesDTO> retrieveEmployees() throws EmployeesException {
		Iterable<Employees> employees=repository.findAll();
		if(!employees.iterator().hasNext()) {
			throw new EmployeesException("No Employees Found");
		}
		List<EmployeesDTO> employeesDTOList=new ArrayList<>();
		for(Employees employee:employees) {
			if(employee.getProjects().getProjectType()==TYPE.BILLABLE) {
				double incentive=50000;
				employee.setEmpSalary(employee.getEmpSalary()+incentive);
			}
			employeesDTOList.add(mapper.map(employee, EmployeesDTO.class));
		}
		
		return employeesDTOList;
	}
	@Cacheable(key = "#empId")
	@Override
	public EmployeesDTO retrieveEmployee(int empId) throws EmployeesException {
		Optional<Employees> employee=repository.findById(empId);
		if(!employee.isPresent()) {
			throw new EmployeesException("Employee with Id:"+empId+" not found");
		}
		
		return mapper.map(employee.get(),EmployeesDTO.class);
	}
	@CacheEvict(allEntries = true)
	@Override
	public String storeEmployee(EmployeesDTO employeesDTO) {
		Employees employee = mapper.map(employeesDTO, Employees.class);
		Employees employeesSaved=repository.save(employee);
		if(employeesSaved!=null) {
			return "success";
		}
		return "fail";
	}

	@CachePut(key = "#empId")
	@CacheEvict(allEntries = true)
	@Transactional()
	@Override
	public String updateEmployee(int empId, double newSalary) {
		int result=repository.update(empId, newSalary);
		if(result>0)
			return "success";
		else
			return "fail";
	}
	 @CacheEvict(key = "#empId", allEntries = false)
	@Override
	public String deleteEmployee(int empId) {
		Optional<Employees> employeeFound=repository.findById(empId);
		if(employeeFound.isPresent()) {
			repository.deleteById(empId);
			return "success";
		}else {
			return "fail";
		}
		
		}
		
	

	@Transactional()
	@Override
	public EmployeesDTO update(int empId, EmployeesDTO employeesDTO) {
		Employees employeeUpdate=null;
		Optional<Employees> employeeFound=repository.findById(empId);
		if(employeeFound.isPresent()) {
			Employees employeeExisting=employeeFound.get();
			employeeExisting.setEmpName(employeesDTO.getEmpName());
			employeeExisting.setEmpSalary(employeesDTO.getEmpSalary());
			employeeExisting.setEmpDesignation(employeesDTO.getEmpDesignation());
			Projects project=employeeExisting.getProjects();
			employeeExisting.setProjects(project);
			employeeUpdate=repository.save(employeeExisting);
		
		}else {
			/*Employees employeeInsert=mapper.map(employeesDTO, Employees.class);
		    if (employeesDTO.getProjects() != null) {
		        Optional<Projects> managedProject = projectRepository.findById(employeesDTO.getProjects().getProjectCode());
		        if(managedProject.isPresent()) {
		        	employeeInsert.setProjects(managedProject.get());
		        }
		        
		    }
		        Employees saved = repository.saveAndFlush(employeeInsert);
		        return mapper.map(saved, EmployeesDTO.class);*/
		    
		}
		return mapper.map(employeeUpdate, EmployeesDTO.class);
	}

}
