package com.example.employee.authenticate;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class ResponseMessage {
	
	private final boolean error;
	
	private final HttpStatus status;
	
	private final String msg;

}
