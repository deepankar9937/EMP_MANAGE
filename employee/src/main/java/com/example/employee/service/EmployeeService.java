package com.example.employee.service;

import java.util.List;

import com.example.employee.dto.Employee;

public interface EmployeeService {

	public Employee getEmployee(String email);
	
	public Employee addEmployee(Employee details);
	
	public Employee addAdmin(Employee details);
	
	public Employee updateEmployee(Employee details, long id);
	
	public List<Employee> fetchEmployeeDetails();
	
	public Employee getEmpId(long id);

}
