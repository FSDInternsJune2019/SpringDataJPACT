package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.demo.dto.EmployeesDTO;
import com.demo.exception.EmployeesException;
import com.demo.service.EmployeesService;


import jakarta.validation.Valid;
/**
 * RestFul Controller for Resource of Type Employee
 * @RestController=@Controller+@ResponseBody
 */
@RestController
@RequestMapping("api")
@EnableAspectJAutoProxy
public class EmployeesControllerImpl implements EmployeesController {

	private EmployeesService employeesService;
	@Autowired
	public EmployeesControllerImpl(EmployeesService employeesService) {
		this.employeesService=employeesService;
	}
	
	
	/**
	 * Retrieves all resources of type Employee
	 * if resource not found throws EmployeesException
	 */
	@GetMapping("employees")
	@Override
	public ResponseEntity<List<EmployeesDTO>> get() throws EmployeesException {
		List<EmployeesDTO> employeesDTO=employeesService.retrieveEmployees();
		
		return new ResponseEntity<List<EmployeesDTO>>(employeesDTO,HttpStatus.OK);
	}

	@GetMapping("employees/{empId}")
	@ResponseStatus(value = HttpStatus.FOUND)
	@Override
	public EmployeesDTO getByEmpId(@PathVariable("empId") int empId) throws EmployeesException {
		return employeesService.retrieveEmployee(empId);
	}

	@GetMapping("v1/employees")
	public EmployeesDTO getByEmpIdRequestParam(@RequestParam("empId")int empId) throws EmployeesException {
		return employeesService.retrieveEmployee(empId);
	}
	
	@PostMapping("v1/employees")
	@Override
	public ResponseEntity<String> post(@Valid @RequestBody EmployeesDTO employeesDTO) {
	
	 String result=employeesService.storeEmployee(employeesDTO);
	 if(result.equals("success"))
		 return new ResponseEntity<String>("Employee Resource Created",HttpStatus.CREATED);
	 else
		 return new ResponseEntity<String>("Employee Resource Creation Failed",HttpStatus.BAD_REQUEST);
	 
	}

	@PutMapping("v2/employees/{empId}")
	@Override
	public ResponseEntity<String> put(@PathVariable("empId") int empId,@Valid @RequestBody EmployeesDTO employeesDTO) throws EmployeesException {
		EmployeesDTO employeesDTOUpdated=employeesService.update(empId, employeesDTO);
		if(employeesDTOUpdated!=null) {
			return new ResponseEntity<String>("Employee Resource updated",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Employee Resource updation failed",HttpStatus.NOT_MODIFIED);
		}
	}

	@PatchMapping("v2/employees/{empId}/{empSalary}")
	@Override
	public ResponseEntity<String> patch(@PathVariable("empId") int empId,@PathVariable("empSalary") double newSalary) {
		String result=employeesService.updateEmployee(empId, newSalary);
		if(result.equals("success"))
			return new ResponseEntity<String>("Employee Salary updated",HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<String>("Employee Salary updation failed",HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("v2/employees/{empId}")
	@Override
	public ResponseEntity<String> delete(@PathVariable("empId")int empId) {
		String result=employeesService.deleteEmployee(empId);
		if(result.equals("success")) {
			return ResponseEntity.ok("Resource Deleted");
		}else {
			return new ResponseEntity<String>("Resource not deleted",HttpStatus.NOT_FOUND);
		}
		
	}
	

}
