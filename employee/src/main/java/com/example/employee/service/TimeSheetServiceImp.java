package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.dao.TimeSheetDao;
import com.example.employee.dto.TimeSheet;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeSheetServiceImp implements TimeSheetService{
	
	private final TimeSheetDao dao;

	@Override
	public boolean addTimeSheet(TimeSheet sheet) {
		TimeSheet timeSheet = dao.save(sheet);
		if (timeSheet != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTimeSheet(TimeSheet sheet, long id) {
		TimeSheet timeSheet = dao.findBytimeSheetId(id);
		if (timeSheet != null) {
			timeSheet.setDate(sheet.getDate());
			timeSheet.setLoginTime(sheet.getLoginTime());
			timeSheet.setLogoutTime(sheet.getLoginTime());
			timeSheet.setDescription(sheet.getDescription());
			dao.save(timeSheet);
			return true;
		}
		return false;
	}

	@Override
	public List<TimeSheet> getAllTimeSheetOfSpecificEmployee(long id) {
		return dao.findByEmployee_EmployeeId(id);
	}

	@Override
	public TimeSheet getTimeSheet(long id) {
		TimeSheet timeSheet = dao.findBytimeSheetId(id);
		System.out.println(timeSheet.getDate());
		return timeSheet;
	}

}
