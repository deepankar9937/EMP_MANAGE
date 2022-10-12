package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employee.dao.EmployeeDao;
import com.example.employee.dto.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeDao dao;
	private final BCryptPasswordEncoder encoder;

	@Override
	public Employee addEmployee(Employee details) {
		details.setRole("ROLE_EMPLOYEE");
		details.setPassword(encoder.encode(details.getPassword()));
		details.setRole("ROLE_EMPLOYEE");
		return dao.save(details);
	}
	
	@Override
	public Employee addAdmin(Employee details) {
		details.setPassword(encoder.encode(details.getPassword()));
		details.setEmployeeName("Admin");
		details.setRole("ROLE_ADMIN");
		return dao.save(details);
	}

	@Override
	public Employee updateEmployee(Employee details, long empId) {
		Employee employee = dao.findByEmployeeId(empId);
		if (employee != null) {
			employee.setEmployeeName(details.getEmployeeName());
			employee.setEmployeeAddress(details.getEmployeeAddress());
			employee.setPhoneNo(details.getPhoneNo());
			dao.save(employee);
			return employee;
		}
		return null;
		
	}

	@Override
	public List<Employee> fetchEmployeeDetails() {
		return (List<Employee>)dao.findAll();
	}

	@Override
	public Employee getEmployee(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public Employee getEmpId(long id) {
		return dao.findByEmployeeId(id);
	}
	
	 
}
