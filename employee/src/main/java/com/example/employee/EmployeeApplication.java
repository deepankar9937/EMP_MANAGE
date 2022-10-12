package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import com.example.employee.dto.Employee;
import com.example.employee.service.EmployeeService;

@SpringBootApplication
public class EmployeeApplication {
	
	
	@Autowired
	private EmployeeService service;
	
	 @Autowired
	 private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		String email = environment.getProperty("email");
	    Employee employee = service.getEmployee(email);
	    if (employee != null) {
			System.out.println("Admin Already Exist");
		} else {
			Employee employee2 = new Employee();
			employee2.setEmail(email);
			employee2.setPassword(environment.getProperty("password"));
			service.addAdmin(employee2);
			System.out.println("Admin Added successfully");
		}
		
	}

}
