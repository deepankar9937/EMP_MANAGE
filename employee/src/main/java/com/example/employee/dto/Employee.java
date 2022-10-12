package com.example.employee.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	
	private String employeeName;
	
	private String email;
	
	private String password;
	
	private String role; 
	
	private String employeeAddress;
	
	private Long phoneNo;
	
	@OneToMany(mappedBy = "employee")
	@JsonManagedReference
	private List<TimeSheet> timeSheet;
}
