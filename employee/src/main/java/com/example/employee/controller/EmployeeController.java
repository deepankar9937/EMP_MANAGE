package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.authenticate.AuthenticateRequest;
import com.example.employee.authenticate.AuthenticationResponse;
import com.example.employee.authenticate.EmployeeResponse;
import com.example.employee.authenticate.ResponseMessage;
import com.example.employee.authenticate.ResponseObject;
import com.example.employee.dto.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class EmployeeController {

	private final EmployeeService service;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticateRequest authenticateRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(),
					authenticateRequest.getPassword()));
		} catch (AuthenticationException e) {
			return ResponseEntity.ok(new AuthenticationResponse(null, null, 0));
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticateRequest.getEmail());
		String token = jwtUtil.generateToken(userDetails);
		Employee employee = service.getEmployee(userDetails.getUsername());

		return ResponseEntity.ok(new AuthenticationResponse(token, employee.getRole(), employee.getEmployeeId()));
	}

	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		Employee addEmployee = service.addEmployee(employee);
		if (addEmployee != null) {
			return ResponseEntity.ok(new ResponseMessage(false, HttpStatus.OK, "Employee is Added Successfully"));
		}
		return ResponseEntity
				.ok(new ResponseMessage(true, HttpStatus.INTERNAL_SERVER_ERROR, "Employee Is Not Added Successfully"));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
		Employee addEmployee = service.updateEmployee(employee, id);
		if (addEmployee != null) {
			return ResponseEntity.ok(new ResponseMessage(false, HttpStatus.OK, "Employee is Updated Successfully"));
		}
		return ResponseEntity.ok(
				new ResponseMessage(true, HttpStatus.INTERNAL_SERVER_ERROR, "Employee Is Not Updated Successfully"));
	}

	@PostMapping("/addAdmin")
	public ResponseEntity<?> addAdmin(@RequestBody Employee employee) {
		Employee addAdmin = service.addAdmin(employee);
		if (addAdmin != null) {
			return ResponseEntity.ok(new ResponseMessage(false, HttpStatus.OK, "Employee is Added Successfully"));
		}
		return ResponseEntity
				.ok(new ResponseMessage(true, HttpStatus.INTERNAL_SERVER_ERROR, "Employee Is Not Added Successfully"));
	}
	@GetMapping("/getEmp/{id}")
	public ResponseEntity<?> getEmpId(@PathVariable long id){
		Employee empId = service.getEmpId(id);
		if(empId !=null) {
			EmployeeResponse employeeResponse = new EmployeeResponse();
			employeeResponse.setName(empId.getEmployeeName());
			employeeResponse.setEmail(empId.getEmail());
			employeeResponse.setAddress(empId.getEmployeeAddress());
			employeeResponse.setPhone(empId.getPhoneNo());
			return ResponseEntity.ok(new ResponseObject(false,HttpStatus.OK, employeeResponse));
		}
		return ResponseEntity.ok(new ResponseObject(true, HttpStatus.INTERNAL_SERVER_ERROR, "Employee not found"));
	}

}
