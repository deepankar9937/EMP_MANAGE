package com.example.employee.authenticate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResponse {

	private String name;
	
	private String email;
	
	private String address;
	
	private long phone;
}
