package com.example.employee.authenticate;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseListOfObject {

	private boolean error;
	
	private HttpStatus status;
	
	private List<?> listObj;
}
