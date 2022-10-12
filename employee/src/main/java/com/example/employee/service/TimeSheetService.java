package com.example.employee.service;

import java.util.List;

import com.example.employee.dto.TimeSheet;

public interface TimeSheetService {

	public boolean addTimeSheet(TimeSheet sheet);
	
	public boolean updateTimeSheet(TimeSheet sheet,long id);
	
	public TimeSheet getTimeSheet(long id);
	
	public List<TimeSheet> getAllTimeSheetOfSpecificEmployee(long id);
	
}
