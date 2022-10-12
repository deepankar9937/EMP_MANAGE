package com.example.employee.dto;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
@Entity
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long timeSheetId;
	
	@JsonFormat(pattern = "dd-MM-yyyy",shape = Shape.STRING)
	private Date date;
	
	@JsonFormat(pattern = "HH:mm:ss",shape = Shape.STRING)
	private Time loginTime;
	
	@JsonFormat(pattern = "HH:mm:ss",shape = Shape.STRING)
	private Time logoutTime;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "employeeId")
	@JsonBackReference
	private Employee employee;
	
	
}