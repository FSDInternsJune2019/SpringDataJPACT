package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Employees;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

	//@Transactional
	@Modifying
	@Query(value = "update Employees o set o.empSalary=:empSalary where o.empId=:empId")
	public int update(@Param("empId")int empId,@Param("empSalary")double empSalary);
	
}
