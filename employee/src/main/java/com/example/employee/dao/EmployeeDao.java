package com.example.employee.dao;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.example.employee.dto.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

	public Employee findByEmployeeId(long id);
	
	public Employee findByEmail(String email); 
}
