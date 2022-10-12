package com.example.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.employee.dto.TimeSheet;

public interface TimeSheetDao extends CrudRepository<TimeSheet, Long>{

	public TimeSheet findBytimeSheetId(long id);
	
	public List<TimeSheet> findByEmployee_EmployeeId(long id);
}
