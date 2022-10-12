package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.employee.dao.EmployeeDao;
import com.example.employee.dto.Employee;
import com.example.employee.dto.MyAdminDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Authencation implements UserDetailsService{
	
	private final EmployeeDao dao;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = dao.findByEmail(email);
		if (employee != null) {
			return new MyAdminDetails(employee);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}
}
