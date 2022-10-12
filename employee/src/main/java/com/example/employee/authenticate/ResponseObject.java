package com.example.employee.authenticate;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {

	private boolean error;
	
	private HttpStatus status;
	
	private Object obj;
}
